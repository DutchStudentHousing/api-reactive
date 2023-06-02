package nl.dsh.api.configuration;

import nl.dsh.api.dao.Message;
import nl.dsh.api.dao.Property;
import nl.dsh.api.util.CsvEncoder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
//@EnableWebFlux
@ComponentScan
public class WebConfig extends WebFluxConfigurationSupport {
    private final CsvEncoder<Property> propertyCsvEncoder;
    private final CsvEncoder<Message> messageCsvEncoder;

    public WebConfig(CsvEncoder<Property> propertyCsvEncoder, CsvEncoder<Message> messageCsvEncoder) {
        this.propertyCsvEncoder = propertyCsvEncoder;
        this.messageCsvEncoder = messageCsvEncoder;
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
        super.configureArgumentResolvers(configurer);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.customCodecs().register(propertyCsvEncoder);
        configurer.customCodecs().register(messageCsvEncoder);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }
}
