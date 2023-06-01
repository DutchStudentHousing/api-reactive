package nl.dsh.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.interfaces.MessageApi;
import nl.dsh.api.dao.Message;
import nl.dsh.api.dao.PostMessageRequest;
import nl.dsh.api.interfaces.MessagesApi;
import nl.dsh.api.models.User;
import nl.dsh.api.security.JWT;
import nl.dsh.api.services.MessageService;
import nl.dsh.api.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController implements MessageApi, MessagesApi {

    private final MessageService messageService;
    private final UserService userService;
    private final JWT jwt;

    @Override
    public Mono<ResponseEntity<Message>> postMessage(Mono<PostMessageRequest> postMessageRequest, ServerWebExchange exchange) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public Mono<ResponseEntity<Flux<Message>>> getMessages(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        Mono<Flux<Message>> msgs = userService.findByUsername(jwt.getClaims(token.substring(7)).getAudience()).map(usr -> messageService.getMessagesForUser((User)usr));
        return msgs.map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
