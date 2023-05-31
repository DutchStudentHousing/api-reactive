package nl.dsh.api.services;

import lombok.RequiredArgsConstructor;
import nl.dsh.api.models.User;
import nl.dsh.api.repositories.UserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {
    private final UserRepository repo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repo.findFirstByUsername(username).cast(UserDetails.class);
    }
}
