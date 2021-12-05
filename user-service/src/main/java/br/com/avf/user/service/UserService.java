package br.com.avf.user.service;

import br.com.avf.user.codec.Codec;
import br.com.avf.user.protocol.UserRequest;
import br.com.avf.user.protocol.UserResponse;
import br.com.avf.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Mono<Boolean> updateAmount(final Long userId, final BigDecimal amount) {
        return repository.update(userId, amount);
    }

    public Flux<UserResponse> getAll() {
        return this.repository.findAll().map(Codec::toResponse);
    }

    public Mono<UserResponse> getById(final Long id) {
        return this.repository.findById(id).map(Codec::toResponse);
    }

    public Mono<UserResponse> create(final Mono<UserRequest> requestMono) {
        return requestMono.map(Codec::toEntity)
                .flatMap(this.repository::save)
                .map(Codec::toResponse);
    }

    public Mono<UserResponse> update(final Long id, final Mono<UserRequest> requestMono) {
        return this.repository.findById(id)
                .flatMap(user -> requestMono.map(Codec::toEntity).doOnNext(e -> e.setId(id)))
                .flatMap(this.repository::save)
                .map(Codec::toResponse);
    }

    public Mono<Void> delete(final Long id) {
        return this.repository.deleteById(id);
    }
}
