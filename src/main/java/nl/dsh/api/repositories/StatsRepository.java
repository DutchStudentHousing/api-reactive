package nl.dsh.api.repositories;

import nl.dsh.api.models.Stats;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StatsRepository extends ReactiveCrudRepository<Stats, String> {
    @Query("SELECT p.city AS city, "+
            "AVG(p.rent) AS mean, "+
            "PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER BY p.rent) AS median, "+
            "STDDEV(p.rent) AS stddev "+
            "FROM property p " +
            "GROUP BY p.city")
    Flux<Stats> getStats();

    @Query("SELECT 'all' AS city, "+
            "AVG(p.rent) AS mean, "+
            "PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER BY p.rent) AS median, "+
            "STDDEV(p.rent) AS stddev "+
            "FROM property p ")
    Mono<Stats> getOverallStats();

    @Query("SELECT :city AS city, "+
            "AVG(p.rent) AS mean, "+
            "PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER BY p.rent) AS median, "+
            "STDDEV(p.rent) AS stddev "+
            "FROM property p " +
            "WHERE p.city = :city " +
            "GROUP BY p.city")
    Mono<Stats> getStatsByCity(@Param("city") String city);
}
