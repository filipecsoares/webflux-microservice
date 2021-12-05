package br.com.avf.user.controller;

import br.com.avf.user.protocol.UserRequest;
import br.com.avf.user.protocol.UserResponse;
import br.com.avf.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Flux<UserResponse> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> getById(@PathVariable int id) {
        return this.userService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserResponse> create(@RequestBody Mono<UserRequest> requestMono) {
        return this.userService.create(requestMono);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> update(@PathVariable int id, @RequestBody Mono<UserRequest> requestMono) {
        return this.userService.update(id, requestMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return this.userService.delete(id);
    }
}
