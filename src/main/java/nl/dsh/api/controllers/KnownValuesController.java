package nl.dsh.api.controllers;

import nl.dsh.api.dao.GetKnownValues200Response;
import nl.dsh.api.interfaces.KnownvaluesApi;
import nl.dsh.api.services.KnownValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class KnownValuesController implements KnownvaluesApi {
    KnownValuesService svc;

    @Autowired
    public KnownValuesController(KnownValuesService svc) {
        this.svc = svc;
    }

    @Override
    public Mono<ResponseEntity<GetKnownValues200Response>> getKnownValues(ServerWebExchange exchange) {
        return svc.getKnownValues().map(ResponseEntity::ok);
    }
}
