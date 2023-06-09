package nl.dsh.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.Property;
import nl.dsh.api.dao.PropertyDetails;
import nl.dsh.api.repositories.PropertyFilter;
import nl.dsh.api.repositories.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository repo;
    private final ModelMapper mapper;

    public Flux<Property> listProperties(PropertyFilter filter, Pageable pageable) {
        return repo.findAllBy(filter, pageable)
                .map(p -> mapper.map(p, Property.class));
    }

    public Mono<PropertyDetails> getPropertyById(String id) {
        return repo.getPropertyById(id)
                .map(p -> mapper.map(p, PropertyDetails.class));
    }
}
