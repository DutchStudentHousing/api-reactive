package nl.dsh.api.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.codec.EnumCodec;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.models.Property;
import nl.dsh.api.models.PropertyDetails;
import nl.dsh.api.models.PropertyMatch;
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
@Slf4j
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {
    @SneakyThrows
    @Override
    @Bean
    public PostgresqlConnectionFactory connectionFactory() {
        Optional<String> passwdPath = Optional.ofNullable(System.getenv("DSH_PASS_FILE")).filter(not(String::isEmpty));
        String passwd = (passwdPath.isPresent())
                ? Files.readString(Path.of(passwdPath.get()))
                : System.getenv("DSH_PASS");
        String db = Optional.ofNullable(System.getenv("DSH_DB")).filter(not(String::isEmpty)).orElse("dsh");
        String usr = Optional.ofNullable(System.getenv("DSH_USER")).filter(not(String::isEmpty)).orElse("dsh");
        int port = Integer.parseInt(Optional.ofNullable(System.getenv("DSH_PORT")).filter(not(String::isEmpty)).filter(s -> s.matches("[0-9]+")).orElse("5432"));
        String host = Optional.ofNullable(System.getenv("DSH_HOST")).filter(not(String::isEmpty)).orElse("localhost");
        log.info(String.format("conn: %s@%s:%d/%s", usr, host, port, db));
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .codecRegistrar(EnumCodec.builder()
                        .withEnum("prop_type", Property.TypeEnum.class)
                        .withEnum("gender_roommates_type", PropertyDetails.GenderRoomMatesType.class)
                        .withEnum("furnished_type", PropertyDetails.FurnishedType.class)
                        .withEnum("energy_label_type", PropertyDetails.EnergyLabelType.class)
                        .withEnum("gender_match_type", PropertyMatch.GenderType.class)
                        .withEnum("match_status_type", PropertyMatch.MatchStatusType.class)
                        .build())
                .host(host)
                .port(port)
                .username(usr)
                .password(passwd)
                .database(db)
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
