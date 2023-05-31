package nl.dsh.api.repositories;

import nl.dsh.api.models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findFirstByEmail(String email);
    Mono<User> findByEmailAndPassword(String email, String password);
}
