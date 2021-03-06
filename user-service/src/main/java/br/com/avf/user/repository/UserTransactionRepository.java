package br.com.avf.user.repository;

import br.com.avf.user.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Long> {

    Flux<UserTransaction> findByUserId(Long userId);
}
