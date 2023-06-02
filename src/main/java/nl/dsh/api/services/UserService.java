package nl.dsh.api.services;

import lombok.RequiredArgsConstructor;
import nl.dsh.api.dao.Login200Response;
import nl.dsh.api.dao.LoginRequest;
import nl.dsh.api.models.User;
import nl.dsh.api.repositories.UserRepository;
import nl.dsh.api.security.JWT;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {
    private final UserRepository repo;
    private final JWT jwt;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repo.findFirstByEmail(username).cast(UserDetails.class);
    }

    public Mono<Login200Response> getLoginResponse(LoginRequest req) {
        return repo.findByEmailAndPassword(req.getEmail(), req.getPassword())
                .map(jwt::genToken)
                .map(token -> new Login200Response().token(token));
    }
}
