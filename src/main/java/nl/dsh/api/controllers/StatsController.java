package nl.dsh.api.controllers;

import lombok.AllArgsConstructor;
import nl.dsh.api.dao.Stats;
import nl.dsh.api.interfaces.StatsApi;
import nl.dsh.api.services.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@AllArgsConstructor
public class StatsController implements StatsApi {

    final StatsService svc;

    @Override
    public Mono<ResponseEntity<Stats>> stats(String city, ServerWebExchange exchange) {
        return svc.getStatsByCity(city)
                .filter(Objects::nonNull)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(404).body(null));
    }
}
