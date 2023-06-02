package nl.dsh.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.Account;
import nl.dsh.api.dao.Login200Response;
import nl.dsh.api.dao.LoginRequest;
import nl.dsh.api.dao.Token;
import nl.dsh.api.interfaces.AccountApi;
import nl.dsh.api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountApi {
    private final UserService svc;
    @Override
    public Mono<ResponseEntity<Account>> addAccount(Mono<Account> account, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Token>> login(Mono<LoginRequest> loginRequest, ServerWebExchange exchange) {
        return loginRequest
                .flatMap(svc::getLoginResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity
                        .status(404)
                        .body(null));
    }

    @Override
    public Mono<ResponseEntity<Void>> logout(ServerWebExchange exchange) {
        exchange.getPrincipal().map(Principal::getName).log();
        return Mono.just(ResponseEntity.status(204).body(null));
    }
}
