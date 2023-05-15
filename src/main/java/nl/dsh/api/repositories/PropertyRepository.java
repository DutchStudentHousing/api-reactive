package nl.dsh.api.repositories;

import nl.dsh.api.models.Property;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends R2dbcRepository<Property,String> {
}
