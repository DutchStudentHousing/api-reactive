package nl.dsh.api.repositories;

import nl.dsh.api.models.MessageRaw;
import nl.dsh.api.models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<MessageRaw, User> {
    Flux<MessageRaw> getMessagesBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
