package nl.dsh.api.repositories;

import lombok.extern.slf4j.Slf4j;
import nl.dsh.api.models.DetailedProperty;
import nl.dsh.api.models.Property;
import nl.dsh.api.models.PropertyDetails;
import nl.dsh.api.models.PropertyMatch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
@Slf4j
public class PropertyRepository {

    private final R2dbcEntityOperations entityOperations;

    public PropertyRepository(R2dbcEntityOperations entityOperations) {
        this.entityOperations = entityOperations;
    }

    public Flux<Property> findAllBy(PropertyFilter filter, Pageable pageable) {
        float distance = filter.distance.orElse(.1111f)/111.1f; // default to 100m accuracy?

        Criteria search = Criteria.empty();
        if(filter.maxRent.isPresent() || filter.minRent.isPresent())
            search = search.and(Criteria.where("rent").between(
                    filter.minRent.orElse(Float.NEGATIVE_INFINITY),
                    filter.maxRent.orElse(Float.POSITIVE_INFINITY)));
        if(filter.city.isPresent())
            search = search.and(Criteria.where("city").like(filter.city.get()));
        if(filter.lat.isPresent() || filter._long.isPresent())
            search = search.and(Criteria.where("lat").between(
                    filter.lat.orElse(Float.NEGATIVE_INFINITY)-distance,
                    filter.lat.orElse(Float.POSITIVE_INFINITY)+distance
            )).and(Criteria.where("long").between(
                    filter._long.orElse(Float.NEGATIVE_INFINITY)-distance,
                    filter._long.orElse(Float.POSITIVE_INFINITY)+distance
            ));
        log.info(search.toString());
        return entityOperations.select(Property.class)
                .matching(Query.query(search
                        ).sort(pageable.getSort())
                        .with(pageable)
                ).all();
    }

    public Mono<DetailedProperty> getPropertyById(String id) {
        DatabaseClient client = entityOperations.getDatabaseClient();
        return client.sql("SELECT * FROM property p "+
                        "INNER JOIN property_details d "+
                        "ON p.property_id = d.property_id "+
                        "INNER JOIN property_match m "+
                        "ON p.property_id = m.property_id " +
                        "WHERE p.property_id = :id")
                .bind("id", id)
                .map(row -> DetailedProperty.builder()
                        .property(Property.builder()
                                .id(row.get("property_id", String.class))
                                .name(row.get("name", String.class))
                                .city(row.get("city", String.class))
                                .lat(row.get("lat", Float.class))
                                ._long(row.get("long", Float.class))
                                .img(row.get("cover_image_url", String.class))
                                .datePublished(row.get("date_published", LocalDateTime.class))
                                .rent(row.get("rent", Float.class))
                                .rentIncl(row.get("rent_incl", Boolean.class))
                                .sqm(row.get("sqm", Integer.class))
                                .postalCode(row.get("postal_code", String.class))
                                .type(row.get("type", Property.TypeEnum.class))
                                .build())
                        .details(PropertyDetails.builder()
                                .id(row.get("property_details_id", Long.class))
                                .description(row.get("description", String.class))
                                .roomMates(row.get("roommates", String.class))
                                .genderRoomMates(row.get("gender_roommates", PropertyDetails.GenderRoomMatesType.class))
                                .furnished(row.get("furnished", PropertyDetails.FurnishedType.class))
                                .energyLabel(row.get("energy_label", PropertyDetails.EnergyLabelType.class))
                                .internet(row.get("internet", Boolean.class))
                                .petsAllowed(row.get("pets_allowed", Boolean.class))
                                .ownToilet(row.get("own_toilet", Boolean.class))
                                .ownBathroom(row.get("own_bathroom", Boolean.class))
                                .ownKitchen(row.get("own_kitchen", Boolean.class))
                                .smokingAllowed(row.get("smoking_allowed", Boolean.class))
                                .lastSeenAt(row.get("last_seen_at", LocalDateTime.class))
                                .build())
                        .match(PropertyMatch.builder()
                                .id(row.get("property_match_id", Long.class))
                                .gender(row.get("gender", PropertyMatch.GenderType.class))
                                .minAge(row.get("age_min", Long.class))
                                .maxAge(row.get("age_max", Long.class))
                                .matchStatus(row.get("match_status", PropertyMatch.MatchStatusType[].class))
                                .build())
                        .build()
                ).first();
    }
}