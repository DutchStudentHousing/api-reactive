package nl.dsh.api.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Configuration
@EnableR2dbcRepositories
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {
    @SneakyThrows
    @Override
    @Bean
    public PostgresqlConnectionFactory connectionFactory() {
        Optional<String> passwdPath = Optional.ofNullable(System.getenv("DSH_PASS_FILE")).filter(not(String::isEmpty));
        String passwd = (passwdPath.isPresent())
                ? Files.readString(Path.of(passwdPath.get()))
                : System.getenv("DSH_PASS");
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(System.getenv("DSH_HOST"))
                .port(Integer.parseInt(System.getenv("DSH_PORT")))
                .username(System.getenv("DSH_USER"))
                .password(passwd)
                .database(System.getenv("DSH_DB"))
                .build());
    }

    @Bean
    public R2dbcMappingContext r2dbcMappingContext(Optional<NamingStrategy> namingStrategy,
                                                   R2dbcCustomConversions r2dbcCustomConversions) {
//        assert namingStrategy != null;
        R2dbcMappingContext context = new R2dbcMappingContext(namingStrategy
                .orElse(NamingStrategy.INSTANCE));
        context.setSimpleTypeHolder(r2dbcCustomConversions.getSimpleTypeHolder());
        context.setForceQuote(true);

        return context;
    }
}
