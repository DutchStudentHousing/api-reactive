package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "property")
public class Property {
    @Id
    @Column("property_id")
    private String id;
    @Column("name")
    private String name;
    @Column("city")
    private String city;
    @Column("lat")
    private Float lat;
    @Column("long")
    private Float _long;
    @Column("cover_image_url")
    private String img;
    @Column("date_published")
    private LocalDateTime datePublished;
    @Column("rent")
    private Float rent;
    @Column("rent_incl")
    private Boolean rentIncl;
    @Column("sqm")
    private Integer sqm;
    @Column("postal_code")
    private String postalCode;
    public enum TypeEnum {
        Room,
        Apartment,
        Studio,
        AntiSquat,
        StudentResidence,
        Other
    }

    @Column("type")
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
}
