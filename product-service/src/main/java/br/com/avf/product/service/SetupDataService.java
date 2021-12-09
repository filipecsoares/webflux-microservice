package br.com.avf.product.service;

import br.com.avf.product.protocol.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SetupDataService implements CommandLineRunner {
    
    private final ProductService productService;
    
    @Override
    public void run(String... args) throws Exception {
        ProductRequest r1 = new ProductRequest("Guitar Gibson", BigDecimal.valueOf(7000));
        ProductRequest r2 = new ProductRequest("Book Clean Architecture", BigDecimal.valueOf(119.9));
        ProductRequest r3 = new ProductRequest("HeadPhone Noise Canceling", BigDecimal.valueOf(299));

        Flux.just(r1, r2, r3).concatWith(newProducts()).flatMap(p -> this.productService.create(Mono.just(p))).subscribe(System.out::println);
    }

    private Flux<ProductRequest> newProducts() {
        return Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> new ProductRequest("Product-"+i, BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(10, 100))));
    }
}
