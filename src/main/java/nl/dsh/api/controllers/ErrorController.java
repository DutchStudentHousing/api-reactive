package nl.dsh.api.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class ErrorController {
    @GetMapping("/error")
    public Mono<ResponseEntity<ErrorResponse>> handleError(ServerWebExchange exchange) {
        return Mono.just(
                ResponseEntity.status(exchange.getResponse().getStatusCode())
                        .body(new ErrorResponse(exchange.getResponse().getRawStatusCode(), "An error occurred!")));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ErrorResponse {
        private int code;
        private String message;
    }
}
