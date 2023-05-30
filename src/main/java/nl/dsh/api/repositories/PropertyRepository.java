package nl.dsh.api.repositories;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.models.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@Slf4j
public class PropertyRepository {

    private final R2dbcEntityOperations entityOperations;

    public PropertyRepository(R2dbcEntityOperations entityOperations) {
        this.entityOperations = entityOperations;
    }

    public Flux<Property> findAllBy(PropertyFilter filter, Pageable pageable) {
        float distance = filter.distance.orElse(11.11f)/111.1f; // default to 100m accuracy?

        Criteria search = Criteria.empty();
        if(filter.maxRent.isPresent() || filter.minRent.isPresent())
            search = search.and(Criteria.where("rent").between(
                    filter.minRent.orElse(Float.NEGATIVE_INFINITY),
                    filter.maxRent.orElse(Float.POSITIVE_INFINITY)));
        if(filter.city.isPresent())
            search = search.and(Criteria.where("city").like(filter.city.get()));
        if(filter.lat.isPresent() || filter._long.isPresent())
            search = search.and(Criteria.where("lat").between(
                    filter.lat.orElse(Float.NEGATIVE_INFINITY)-distance,
                    filter.lat.orElse(Float.POSITIVE_INFINITY)+distance
            )).and(Criteria.where("long").between(
                    filter._long.orElse(Float.NEGATIVE_INFINITY)-distance,
                    filter._long.orElse(Float.POSITIVE_INFINITY)+distance
            ));
        log.info(search.toString());
        return entityOperations.select(Property.class)
                .matching(Query.query(search
                        ).sort(pageable.getSort())
                        .with(pageable)
                ).all();
    }

//    public Mono<KnownValues> findValues() {
//        Query q = Query.empty();
//        q.columns(SqlIdentifier.unquoted("MIN(rent) AS minRent"));
//        q.columns(SqlIdentifier.unquoted("MAX(rent) AS maxRent"));
//
//        return entityOperations.selectOne(q, KnownValues.class);
//    }
}