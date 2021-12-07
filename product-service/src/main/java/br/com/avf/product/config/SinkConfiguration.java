package br.com.avf.product.config;

import br.com.avf.product.protocol.ProductResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfiguration {

    @Bean
    public Sinks.Many<ProductResponse> sink() {
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<ProductResponse> broadcast(Sinks.Many<ProductResponse> sink) {
        return sink.asFlux();
    }
}
