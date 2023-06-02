package nl.dsh.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.Property;
import nl.dsh.api.interfaces.PropertiesApi;
import nl.dsh.api.repositories.PropertyFilter;
import nl.dsh.api.services.PropertyService;
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
@RequiredArgsConstructor
public class PropertiesController implements PropertiesApi {

    private final PropertyService svc;

    @Override
    public Mono<ResponseEntity<Flux<Property>>> getProperties(Optional<Integer> page,
                                                              Optional<Integer> size,
                                                              Optional<String> sort,
                                                              Optional<List<String>> city,
                                                              Optional<Float> lat,
                                                              Optional<Float> _long,
                                                              Optional<Float> distance,
                                                              Optional<Float> min,
                                                              Optional<Float> max,
                                                              final ServerWebExchange exchange,
                                                              final Pageable pageable) {
        log.info("GET /properties");
        log.info(city.toString());
        Flux<Property> properties = svc.listProperties(new PropertyFilter(city, min, max, lat, _long, distance), pageable);
        return properties.hasElements().map(it -> {
            if(it) return ResponseEntity.ok(properties);
            else return ResponseEntity.status(404).body(properties);
        } );
    }
}
