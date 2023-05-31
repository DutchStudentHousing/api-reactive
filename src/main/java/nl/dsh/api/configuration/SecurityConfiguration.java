package nl.dsh.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import java.security.interfaces.RSAPublicKey;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
    @Value("${spring.security.oauth2.resourceserver.jwt.key-value}")
    RSAPublicKey key;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http.authorizeHttpRequests(customizer -> customizer
////                .requestMatchers("/account/login").permitAll()
////                .requestMatchers("/properties").permitAll()
////                .requestMatchers("**").authenticated()
////                .anyRequest().denyAll()).exceptionHandling(customizer -> customizer
////                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))).build();
//        return http.authorizeHttpRequests((authorize) -> authorize
//                .anyRequest().permitAll()).build();
//    }
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//                .authorizeExchange(spec -> {
////                    spec.pathMatchers(HttpMethod.GET, "/propert**").permitAll();
////                    spec.pathMatchers(HttpMethod.GET, "/knownvalues").permitAll();
////                    spec.pathMatchers(HttpMethod.GET, "/stats**").permitAll();
////                    spec.pathMatchers(HttpMethod.PUT, "/propert**").authenticated();
////                    spec.pathMatchers(HttpMethod.DELETE, "/propert**").authenticated();
////                    spec.pathMatchers(HttpMethod.HEAD, "/**").permitAll();
//                    spec.pathMatchers(HttpMethod.GET, "/**").permitAll();
//                })
                .authorizeExchange(exchanges -> exchanges
//                        .anyExchange().authenticated()
                        .anyExchange().permitAll()
                )
                .oauth2ResourceServer(OAuth2ResourceServerSpec::jwt);
        return http.build();
    }
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withPublicKey(key).build();
    }
}
