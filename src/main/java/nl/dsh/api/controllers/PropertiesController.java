package nl.dsh.api.controllers;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.Property;
import nl.dsh.api.interfaces.PropertiesApi;
import nl.dsh.api.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class PropertiesController implements PropertiesApi {

    PropertyService svc;

    public PropertiesController(@Autowired PropertyService svc) {
        this.svc = svc;
    }

    @Override
    public Mono<ResponseEntity<Flux<Property>>> getProperties(Optional<String> city, Optional<Float> lat, Optional<Float> _long, Optional<Float> distance,
                                                              Optional<Integer> perPage, Optional<Integer> page, Optional<String> orderBy,
                                                              Optional<Float> min, Optional<Float> max, final ServerWebExchange exchange) {
        log.info("GET /properties");
        return Mono.just(ResponseEntity.ok(svc.listProperties()));
    }
}
