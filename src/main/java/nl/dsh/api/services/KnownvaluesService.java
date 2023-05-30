package nl.dsh.api.services;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.dao.KnownvaluesGet200Response;
import nl.dsh.api.repositories.KnownvaluesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class KnownvaluesService {
    KnownvaluesRepository repo;
    ModelMapper mapper;

    @Autowired
    public KnownvaluesService(KnownvaluesRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Mono<KnownvaluesGet200Response> getKnownValues() {
        return repo.getKnownValues().log().map(m -> mapper.map(m, KnownvaluesGet200Response.class));
    }
}
