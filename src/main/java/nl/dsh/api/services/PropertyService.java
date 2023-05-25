package nl.dsh.api.services;

import nl.dsh.api.dao.Property;
import nl.dsh.api.repositories.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private final PropertyRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public PropertyService(PropertyRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Flux<Property> listProperties(Optional<Float> maxRent) {
        Function<nl.dsh.api.models.Property,Property> conv = p ->
                mapper.map(p, Property.class);
        return maxRent
                .map(aFloat ->
                        repo.findAllByRentLessThan(aFloat).map(conv))
                .orElseGet(() ->
                        repo.findAll().map(conv));
    }

    private String capitalize(String in) {
        return in.substring(0,1).toUpperCase() + in.substring(1).toLowerCase();
    }
}
