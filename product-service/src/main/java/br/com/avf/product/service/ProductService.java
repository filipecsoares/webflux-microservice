package br.com.avf.product.service;

import br.com.avf.product.codec.Codec;
import br.com.avf.product.protocol.ProductRequest;
import br.com.avf.product.protocol.ProductResponse;
import br.com.avf.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final Sinks.Many<ProductResponse> sink;

    public Flux<ProductResponse> all() {
        return this.repository.findAll().map(Codec::toResponse);
    }

    public Flux<ProductResponse> productByPriceRange(BigDecimal min, BigDecimal max) {
        return this.repository.findByPriceBetween(Range.closed(min, max)).map(Codec::toResponse);
    }

    public Mono<ProductResponse> productById(String id) {
        return this.repository.findById(id).map(Codec::toResponse);
    }

    public Mono<ProductResponse> create(Mono<ProductRequest> requestMono) {
        return requestMono.map(Codec::toEntity).flatMap(this.repository::save).map(Codec::toResponse).doOnNext(this.sink::tryEmitNext);
    }

    public Mono<ProductResponse> update(String id, Mono<ProductRequest> requestMono) {
        return this.repository.findById(id).flatMap(p -> requestMono.map(Codec::toEntity).doOnNext(e -> e.setId(id))).map(Codec::toResponse);
    }

    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }
}
