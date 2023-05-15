package nl.dsh.api.services;

import nl.dsh.api.dao.Property;
import nl.dsh.api.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    PropertyRepository repo;

    @Autowired
    public PropertyService(PropertyRepository repo) {
        this.repo = repo;
    }

    public Flux<Property> listProperties() {
        return repo.findAll().map(property -> {
            Property dao = new Property();
            dao.setType(Property.TypeEnum.fromValue(capitalize(String.valueOf(property.getType()))));
            dao.setId(property.getId());
            dao.setCity(property.getCity());
            dao.setName(property.getName());
            return dao;
        });
    }

    private String capitalize(String in) {
        return in.substring(0,1).toUpperCase() + in.substring(1).toLowerCase();
    }
}
