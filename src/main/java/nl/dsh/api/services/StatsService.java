package nl.dsh.api.services;

import lombok.AllArgsConstructor;
import nl.dsh.api.dao.Stats;
import nl.dsh.api.repositories.StatsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StatsService {
    private final StatsRepository repo;
    private final ModelMapper mapper;

    @SuppressWarnings("unused")
    public Flux<Stats> getAllStats() {
        return repo.getStats().map(it -> mapper.map(it, Stats.class));
    }

    @PreAuthorize("hasRole('admin')")
    public Mono<Stats> getStatsByCity(String city) {
       return repo.getStatsByCity(city).map(it -> mapper.map(it, Stats.class));
    }

    @SuppressWarnings("unused")
    public Mono<Stats> getOverallStats() {
        return repo.getOverallStats().map(it -> mapper.map(it, Stats.class));
    }
}
