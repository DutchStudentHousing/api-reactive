package nl.dsh.api.repositories;

import nl.dsh.api.models.KnownValues;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface KnownValuesRepository extends ReactiveCrudRepository<KnownValues, Void> {
    @Query(value="SELECT MIN(p.rent) AS minRent, "+
            "MAX(p.rent) AS maxRent, "+
            "ARRAY(SELECT DISTINCT p.city FROM property p) AS cities, "+
            "MIN(p.sqm) AS minSqm, "+
            "MAX(p.sqm) AS maxSqm, "+
            "COUNT(p.property_id) AS propertyCount, " +
            "ARRAY(SELECT column_name::TEXT FROM information_schema.columns WHERE table_name='property') AS sortableColumns " +
            "FROM property p LIMIT 1")
    Mono<KnownValues> getKnownValues();
}
