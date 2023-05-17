package nl.dsh.api.repositories;

import nl.dsh.api.models.Property;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PropertyRepository extends R2dbcRepository<Property,String> {
    @Query("SELECT * FROM property")
    Flux<Property> getAllProperties();
}
