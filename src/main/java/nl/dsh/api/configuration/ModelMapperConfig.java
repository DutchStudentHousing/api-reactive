package nl.dsh.api.configuration;

import nl.dsh.api.dao.Property;
import nl.dsh.api.dao.PropertyDetails;
import nl.dsh.api.models.PropertyMatch;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper mapper(){
        ModelMapper mapper = new ModelMapper();
        // mappings for properties as it struggles with those
        final Converter<LocalDateTime, String> dateConverter = ctx ->
                ctx.getSource().toString();
        final Converter<nl.dsh.api.models.Property.TypeEnum, Property.TypeEnum> typeEnumConverter = ctx ->
                Property.TypeEnum.fromValue((ctx.getSource() != null) ?
                        ctx.getSource().name()
                        : "Other");
        final Converter<nl.dsh.api.models.Property.TypeEnum, PropertyDetails.PropertyTypeEnum>
                propertyTypeEnumConverter = ctx ->
                PropertyDetails.PropertyTypeEnum.fromValue((ctx.getSource() != null) ?
                        ctx.getSource().name()
                        : "Other");
        final Converter<nl.dsh.api.models.PropertyDetails.GenderRoomMatesType,
                PropertyDetails.PropertyDetailsGenderRoommatesEnum> detailsGenderRoommatesEnumConverter = ctx ->
                    PropertyDetails.PropertyDetailsGenderRoommatesEnum.fromValue((ctx.getSource() != null) ?
                            ctx.getSource().name() : "Unknown");
        final Converter<nl.dsh.api.models.PropertyDetails.FurnishedType, PropertyDetails.PropertyDetailsFurnishedEnum>
                furnishedEnumConverter = ctx ->
                    PropertyDetails.PropertyDetailsFurnishedEnum.fromValue(ctx.getSource() != null ?
                            ctx.getSource().name() : "Unfurnished");
        final Converter<nl.dsh.api.models.PropertyDetails.EnergyLabelType, PropertyDetails.PropertyDetailsEnergyLabelEnum>
                energyLabelEnumConverter = ctx ->
                    PropertyDetails.PropertyDetailsEnergyLabelEnum.fromValue(ctx.getSource() != null ?
                            ctx.getSource().name() : "Unknown");
        final Converter<PropertyMatch.GenderType, PropertyDetails.PropertyMatchGenderEnum> matchGenderEnumConverter =
                ctx -> PropertyDetails.PropertyMatchGenderEnum.fromValue(ctx.getSource() != null ?
                        ctx.getSource().name() : "NotImportant");
        final Converter<PropertyMatch.MatchStatusType[], List<PropertyDetails.PropertyMatchMatchStatusEnum>>
                matchStatusConv = ctx ->
                Arrays.stream(ctx.getSource())
                    .map(matchStatusType ->
                        PropertyDetails.PropertyMatchMatchStatusEnum.fromValue(matchStatusType.name()))
                    .collect(Collectors.toList());
        mapper.typeMap(nl.dsh.api.models.Property.class, nl.dsh.api.dao.Property.class).addMappings(m -> {
            m.map(nl.dsh.api.models.Property::get_long, nl.dsh.api.dao.Property::setLong);
            m.using(dateConverter).map(nl.dsh.api.models.Property::getDatePublished,
                    nl.dsh.api.dao.Property::setDatePublished);
            m.using(typeEnumConverter).map(nl.dsh.api.models.Property::getType, Property::setType);
        });
        mapper.typeMap(nl.dsh.api.models.DetailedProperty.class, nl.dsh.api.dao.PropertyDetails.class)
                .addMappings(m -> {
            m.using(dateConverter).map(d -> d.getProperty().getDatePublished(),
                    nl.dsh.api.dao.PropertyDetails::setPropertyDatePublished);
            m.map(d -> d.getProperty().get_long(), PropertyDetails::setPropertyLong);
            m.using(propertyTypeEnumConverter).map(d -> d.getProperty().getType(), PropertyDetails::setPropertyType);
            m.using(detailsGenderRoommatesEnumConverter).map(d -> d.getDetails().getGenderRoomMates(),
                    PropertyDetails::setPropertyDetailsGenderRoommates);
            m.using(furnishedEnumConverter).map(d -> d.getDetails().getFurnished(),
                    PropertyDetails::setPropertyDetailsFurnished);
            m.using(energyLabelEnumConverter).map(d -> d.getDetails().getEnergyLabel(),
                    PropertyDetails::setPropertyDetailsEnergyLabel);
            m.using(dateConverter).map(d -> d.getDetails().getLastSeenAt(),
                    PropertyDetails::setPropertyDetailsLastSeenAt);
            m.using(matchGenderEnumConverter).map(d -> d.getMatch().getGender(),
                    PropertyDetails::setPropertyMatchGender);
            m.using(matchStatusConv).map(d -> d.getMatch().getMatchStatus(),
                    PropertyDetails::setPropertyMatchMatchStatus);
        });
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.validate();
        return mapper;
    }
}
