package nl.dsh.api.controllers;

import nl.dsh.api.interfaces.MessageApi;
import nl.dsh.api.dao.Message;
import nl.dsh.api.dao.PostMessageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class MessageController implements MessageApi {
    @Override
    public Mono<ResponseEntity<Message>> postMessage(Mono<PostMessageRequest> postMessageRequest, ServerWebExchange exchange) {
        return null;
    }
}
