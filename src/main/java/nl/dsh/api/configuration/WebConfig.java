package nl.dsh.api.configuration;

import nl.dsh.api.dao.Message;
import nl.dsh.api.dao.Property;
import nl.dsh.api.util.CsvEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    private final CsvEncoder<Property> propertyCsvEncoder;
    private final CsvEncoder<Message> messageCsvEncoder;

    public WebConfig(CsvEncoder<Property> propertyCsvEncoder, CsvEncoder<Message> messageCsvEncoder) {
        this.propertyCsvEncoder = propertyCsvEncoder;
        this.messageCsvEncoder = messageCsvEncoder;
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.customCodecs().register(propertyCsvEncoder);
        configurer.customCodecs().register(messageCsvEncoder);
    }
}
