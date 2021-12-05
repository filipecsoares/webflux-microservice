package br.com.avf.user.service;

import br.com.avf.user.codec.Codec;
import br.com.avf.user.entity.UserTransaction;
import br.com.avf.user.protocol.UserTransactionRequest;
import br.com.avf.user.protocol.UserTransactionResponse;
import br.com.avf.user.protocol.UserTransactionStatus;
import br.com.avf.user.repository.UserTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserTransactionService {
    private final UserService userService;
    private final UserTransactionRepository repository;

    public Mono<UserTransactionResponse> save(final UserTransactionRequest request) {
        return this.userService.updateAmount(request.getUserId(), request.getAmount())
                .filter(Boolean::booleanValue)
                .map(item -> Codec.toEntity(request))
                .flatMap(this.repository::save)
                .map(item -> Codec.toResponse(request, UserTransactionStatus.APPROVED))
                .defaultIfEmpty(Codec.toResponse(request, UserTransactionStatus.NOT_APPROVED));
    }

    public Flux<UserTransaction> getByUserId(int userId) {
        return this.repository.findByUserId(userId);
    }
}
