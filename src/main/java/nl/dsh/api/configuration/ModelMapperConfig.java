package nl.dsh.api.configuration;

import nl.dsh.api.dao.Property;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper mapper(){
        ModelMapper mapper = new ModelMapper();
        // mappings for properties as it struggles with those
        final Converter<LocalDateTime, LocalDate> propertyDateConverter = ctx ->
                LocalDate.from(ctx.getSource());
        final Converter<nl.dsh.api.models.Property.TypeEnum, Property.TypeEnum> typeEnumConverter = ctx ->
                Property.TypeEnum.fromValue((ctx.getSource() != null) ?
                        ctx.getSource().name()
                        : "Other");
        mapper.typeMap(nl.dsh.api.models.Property.class, nl.dsh.api.dao.Property.class).addMappings(m -> {
            m.map(nl.dsh.api.models.Property::get_long, nl.dsh.api.dao.Property::setLong);
            m.using(propertyDateConverter).map(nl.dsh.api.models.Property::getDatePublished,
                    nl.dsh.api.dao.Property::setDatePublished);
            m.using(typeEnumConverter).map(nl.dsh.api.models.Property::getType, Property::setType);
        });
        mapper.validate();
        return mapper;
    }
}
