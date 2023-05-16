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

    private String name;
    private String city;
    private float latitude;
    private float longitude;
    private String coverImgUrl;
    private LocalDateTime published;
    private float rent;
    private String rentIncl;
    private int sqm;
    private String postalCode;
//    private enum Type {
//        ROOM,
//        APARTMENT,
//        STUDIO,
//        OTHER
//    }
    private String type;
}
