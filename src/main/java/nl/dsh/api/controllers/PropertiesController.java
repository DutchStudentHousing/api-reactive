package nl.dsh.api.controllers;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.Property;
import nl.dsh.api.interfaces.PropertiesApi;
import nl.dsh.api.repositories.PropertyFilter;
import nl.dsh.api.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public Mono<ResponseEntity<Flux<Property>>> getProperties(Optional<String> city,
                                                              Optional<Float> lat,
                                                              Optional<Float> _long,
                                                              Optional<Float> distance,
                                                              Optional<String> orderBy,
                                                              Optional<Float> min,
                                                              Optional<Float> max,
                                                              final ServerWebExchange exchange,
                                                              final Pageable pageable) {
        log.info("GET /properties");
//        log.info(String.format("max = %s", max));
        return Mono.just(ResponseEntity.ok(svc.listProperties(
                new PropertyFilter(city, min, max, lat, _long, distance), pageable)));
    }
}
