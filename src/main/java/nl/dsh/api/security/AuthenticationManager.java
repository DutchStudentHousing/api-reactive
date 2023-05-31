package nl.dsh.api.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JWT jwt;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String uname = jwt.getClaims(token).getAudience();

        return Mono.just(jwt.validateToken(token))
                .filter(valid -> valid)
                .map(valid ->{
                    Claims claims = jwt.getClaims(token);
                    List<String> roles = claims.get("role", List.class);
                    return new UsernamePasswordAuthenticationToken(uname, null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                });
    }
}
