package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property")
public class Property {
    @Id
    @Column(name = "property_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "lat")
    private float latitude;
    @Column(name = "long")
    private float longitude;
    @Column(name = "cover_img_url")
    private String coverImgUrl;
    @Column(name = "date_published")
    private LocalDateTime published;
    @Column(name = "rent")
    private float rent;
    @Column(name = "rent_incl")
    private boolean rentIncl;
    @Column(name = "sqm")
    private int sqm;
    @Column(name = "postal_code")
    private String postalCode;
//    private enum Type {
//        ROOM,
//        APARTMENT,
//        STUDIO,
//        OTHER
//    }
    @Column(name = "type")
    private String type;
}
