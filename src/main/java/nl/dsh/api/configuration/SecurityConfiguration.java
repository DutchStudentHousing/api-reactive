package nl.dsh.api.configuration;

//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;

public class SecurityConfiguration {
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
}
