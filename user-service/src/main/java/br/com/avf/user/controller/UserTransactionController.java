package br.com.avf.user.controller;

import br.com.avf.user.entity.UserTransaction;
import br.com.avf.user.protocol.UserTransactionRequest;
import br.com.avf.user.protocol.UserTransactionResponse;
import br.com.avf.user.service.UserTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users/transactions")
@RequiredArgsConstructor
public class UserTransactionController {

    private final UserTransactionService userTransactionService;

    @PostMapping
    public Mono<UserTransactionResponse> save(@RequestBody Mono<UserTransactionRequest> requestMono) {
        return requestMono.flatMap(this.userTransactionService::save);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") Long userId) {
        return this.userTransactionService.getByUserId(userId);
    }
}
