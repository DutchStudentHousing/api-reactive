package nl.dsh.api.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class DefaultContentTypeWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MediaType contentType = request.getHeaders().getContentType();
        if (contentType == null || MediaType.TEXT_HTML.equals(contentType)) {
            exchange = exchange.mutate()
                    .request(request
                            .mutate()
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build())
                    .build();
        }

        return chain.filter(exchange);
    }
}