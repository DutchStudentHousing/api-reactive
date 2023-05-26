package nl.dsh.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
//import org.springframework.data.annotation.

//import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity

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
    private float lat;
    @Column("long")
    private float _long;
    @Column("cover_image_url")
    private String img;
    @Column("date_published")
    private LocalDateTime datePublished;
    @Column("rent")
    private float rent;
    @Column("rent_incl")
    private boolean rentIncl;
    @Column("sqm")
    private int sqm;
    @Column("postal_code")
    private String postalCode;
    private enum TypeEnum {
        Room,
        Apartment,
        Studio,
        AntiSquat,
        StudentResidence,
        Other
    }

    @Column("type")
//    @Enumerated(EnumType.STRING)
    private String type;
}
