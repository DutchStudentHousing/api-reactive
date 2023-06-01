package nl.dsh.api.services;

import lombok.RequiredArgsConstructor;
import nl.dsh.api.dao.Message;
import nl.dsh.api.models.DetailedProperty;
import nl.dsh.api.models.User;
import nl.dsh.api.repositories.MessageRepository;
import nl.dsh.api.repositories.PropertyRepository;
import nl.dsh.api.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repo;
    private final UserRepository userRepo;
    private final PropertyRepository propRepo;
    private final ModelMapper mapper;

    public Flux<Message> getMessagesForUser(User usr) {
        return repo.getMessagesBySenderIdOrReceiverId(usr.getUid(),usr.getUid())
                .flatMap(msg ->
                    Mono.just(nl.dsh.api.models.Message.builder())
                            .flatMap(bldr -> userRepo.findById(msg.getSenderId())
                                    .map(bldr::sender).thenReturn(bldr))
                            .flatMap(bldr -> userRepo.findById(msg.getReceiverId())
                                    .map(bldr::receiver).thenReturn(bldr))
                            .flatMap(bldr -> propRepo.getPropertyById(msg.getPropertyId())
                                    .map(DetailedProperty::getProperty)
                                    .map(bldr::property).thenReturn(bldr))
                            .map(bldr -> bldr.content(msg.getContent()))
                            .map(nl.dsh.api.models.Message.MessageBuilder::build))
                .map(msg -> mapper.map(msg, Message.class));
    }
}
