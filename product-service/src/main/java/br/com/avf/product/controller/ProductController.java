package br.com.avf.product.controller;

import br.com.avf.product.protocol.ProductRequest;
import br.com.avf.product.protocol.ProductResponse;
import br.com.avf.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public Flux<ProductResponse> getAll() {
        return this.productService.all();
    }

    @GetMapping("/price-range")
    public Flux<ProductResponse> getByPriceRange(@RequestParam("min") BigDecimal min, @RequestParam("max")BigDecimal max) {
        return this.productService.productByPriceRange(min, max);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> getById(@PathVariable String id) {
        return this.productService.productById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductResponse> create(@RequestBody Mono<ProductRequest> request) {
        return this.productService.create(request);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> update(@PathVariable String id, @RequestBody Mono<ProductRequest> request) {
        return this.productService.update(id, request).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.productService.delete(id);
    }
}
