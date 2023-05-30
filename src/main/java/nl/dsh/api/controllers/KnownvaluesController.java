package nl.dsh.api.controllers;

import nl.dsh.api.dao.KnownvaluesGet200Response;
import nl.dsh.api.interfaces.KnownvaluesApi;
import nl.dsh.api.services.KnownvaluesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class KnownvaluesController implements KnownvaluesApi {
    KnownvaluesService svc;

    @Autowired
    public KnownvaluesController(KnownvaluesService svc) {
        this.svc = svc;
    }

    @Override
    public Mono<ResponseEntity<KnownvaluesGet200Response>> knownvaluesGet(ServerWebExchange exchange) {
        return svc.getKnownValues().map(ResponseEntity::ok);
    }
}
