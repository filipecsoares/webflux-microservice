package br.com.avf.user.repository;

import br.com.avf.user.entity.User;
import br.com.avf.user.entity.UserTransaction;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
    @Modifying
    @Query("update users set amount = amount - :amount where id = :userId and >= :amount")
    Mono<Boolean> update(int userId, int amount);
}
