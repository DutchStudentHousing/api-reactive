package nl.dsh.api.services;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.Property;
import nl.dsh.api.repositories.PropertyFilter;
import nl.dsh.api.repositories.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class PropertyService {
    private final PropertyRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public PropertyService(PropertyRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public Flux<Property> listProperties(PropertyFilter filter, Pageable pageable) {
        return repo.findAllBy(filter, pageable)
                .map(p -> mapper.map(p, Property.class));
    }
}
