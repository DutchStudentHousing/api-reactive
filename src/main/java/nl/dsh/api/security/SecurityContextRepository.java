package nl.dsh.api.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {
    private final AuthenticationManager authMgr;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHdr -> authHdr.startsWith("Bearer "))
                .flatMap(authHdr -> {
                    log.info(authHdr);
                    String authToken = authHdr.substring(7);
                    Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
                    return this.authMgr.authenticate(auth).map(SecurityContextImpl::new);
                });
    }
}
