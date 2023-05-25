package nl.dsh.api.repositories;

import nl.dsh.api.models.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface PropertyRepository extends ReactiveSortingRepository<Property,String> {
    @Query("SELECT * FROM Property p WHERE " +
    "(:city is null or p.city = :city)" +
    "AND (p.rent >= :minRent)" +
    "AND (p.rent <= :maxRent)" +
    "LIMIT :size OFFSET :page * :size"
    )
    Flux<Property> findAllBy(@Param("city") String city,
                             @Param("minRent") float minRent,
                             @Param("maxRent") float maxRent,
                             @Param("page") int page,
                             @Param("size") int size);
//    Flux<Property> findAllByCity(Optional<String> city, Pageable pageable);
//    Flux<Property> findAllByRentBetween(Optional<Float> min, Optional<Float> max, Pageable pageable);
}
