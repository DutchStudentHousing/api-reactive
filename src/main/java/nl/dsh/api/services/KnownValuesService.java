package nl.dsh.api.services;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.KnownvaluesGet200Response;
import nl.dsh.api.repositories.KnownValuesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class KnownValuesService {
    KnownValuesRepository repo;
    ModelMapper mapper;

    @Autowired
    public KnownValuesService(KnownValuesRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Mono<KnownvaluesGet200Response> getKnownValues() {
        return repo.getKnownValues()
                .map(it -> mapper.map(it, KnownvaluesGet200Response.class));
    }
}
