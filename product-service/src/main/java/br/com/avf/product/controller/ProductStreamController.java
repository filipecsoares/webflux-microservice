package br.com.avf.product.controller;

import br.com.avf.product.protocol.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductStreamController {

    private final Flux<ProductResponse> flux;

    @GetMapping(value = "/stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductResponse> getProductsMaxPrice(@PathVariable double maxPrice) {
        return this.flux.filter(r -> r.getPrice().doubleValue() <= maxPrice);
    }
}
