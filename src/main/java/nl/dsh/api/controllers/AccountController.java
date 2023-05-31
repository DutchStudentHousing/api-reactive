package nl.dsh.api.controllers;

import nl.dsh.api.dao.Account;
import nl.dsh.api.dao.Login200Response;
import nl.dsh.api.dao.LoginRequest;
import nl.dsh.api.interfaces.AccountApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class AccountController implements AccountApi {
    @Override
    public Mono<ResponseEntity<Account>> addAccount(Mono<Account> account, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Login200Response>> login(Mono<LoginRequest> loginRequest, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> logout(ServerWebExchange exchange) {
        exchange.getPrincipal().map(Principal::getName).log();
        return Mono.just(ResponseEntity.status(204).body(null));
    }
}
