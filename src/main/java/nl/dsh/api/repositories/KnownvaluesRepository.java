package nl.dsh.api.repositories;

import nl.dsh.api.models.KnownValues;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface KnownvaluesRepository extends ReactiveCrudRepository<KnownValues, Object> {
    @Query(value="SELECT MIN(p.rent) AS minRent, "+
            "MAX(p.rent) as maxRent, "+
            "ARRAY(SELECT DISTINCT p.city FROM property p) as cities, "+
            "MIN(p.sqm) as minSqm, "+
            "MAX(p.sqm) as maxSqm "+
            "FROM property p LIMIT 1")
    Mono<KnownValues> getKnownValues();
}