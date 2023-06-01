package nl.dsh.api.controllers;

import lombok.RequiredArgsConstructor;
import nl.dsh.api.dao.Property;
import nl.dsh.api.dao.PropertyDetails;
import nl.dsh.api.interfaces.PropertyApi;
import nl.dsh.api.services.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PropertyController implements PropertyApi {
    private final PropertyService svc;

    @Override
    public Mono<ResponseEntity<Property>> addProperty(Mono<Property> property, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<PropertyDetails>> propertyIdGet(String id, ServerWebExchange exchange) {
        return svc.getPropertyById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.status(404).body(null));
    }

    @Override
    public Mono<ResponseEntity<Property>> updateProperty(Mono<Property> property, ServerWebExchange exchange) {
        return null;
    }
}
