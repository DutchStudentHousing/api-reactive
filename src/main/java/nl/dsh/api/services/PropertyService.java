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
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private final PropertyRepository repo;
    private final ModelMapper mapper;

    //TODO: remove
//    private final Property[] testProperties;

    @Autowired
    public PropertyService(PropertyRepository repo) {
        this.repo = repo;
        this.mapper = new ModelMapper();
//        this.testProperties = new Property[10];
//        for(int i = 0; i < 10; i++) {
//            testProperties[i] = new Property();
//            testProperties[i].setName("Abc " + i);
//            testProperties[i].setId("room-" + 1+i);
//            testProperties[i].setCity("Groningen");
//            testProperties[i].setRent(500f);
//            testProperties[i].setRentIncl(true);
//            testProperties[i].setPostalCode("9716AA");
//            testProperties[i].setDatePublished(OffsetDateTime.now());
//            testProperties[i].setImg("na");
//            testProperties[i].setSqm(20);
//            testProperties[i].setLat(10f);
//            testProperties[i].setLong(10f);
//            testProperties[i].setType(Property.TypeEnum.ROOM);
//        }
    }

    public Flux<Property> listProperties() {
//        return repo.findAll().map(property -> {
//            Property dao = new Property();
//            dao.setType(Property.TypeEnum.fromValue(capitalize(String.valueOf(property.getType()))));
//            dao.setId(property.getId());
//            dao.setCity(property.getCity());
//            dao.setName(property.getName());
//            return dao;
//        });
        return repo.findAll().map(p -> mapper.map(p, Property.class));
//        return Flux.fromArray(testProperties);
    }

    private String capitalize(String in) {
        return in.substring(0,1).toUpperCase() + in.substring(1).toLowerCase();
    }
}
