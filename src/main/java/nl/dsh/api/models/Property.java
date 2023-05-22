package nl.dsh.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
    private float lat;
    @Column(name = "long")
    private float _long;
    @Column(name = "cover_img_url")
    private String img;
    @Column(name = "date_published")
    private LocalDateTime datePublished;
    @Column(name = "rent")
    private float rent;
    @Column(name = "rent_incl")
    private boolean rentIncl;
    @Column(name = "sqm")
    private int sqm;
    @Column(name = "postal_code")
    private String postalCode;
    private enum TypeEnum {
        Room,
        Apartment,
        Studio,
        Anti_squat,
        Student_residence,
        Other
    }

//    public enum TypeEnum {
//        ROOM("Room"),
//
//        APARTMENT("Apartment"),
//
//        STUDIO("Studio"),
//
//        ANTI_SQUAT("Anti-squat"),
//
//        STUDENT_RESIDENCE("Student residence"),
//
//        OTHER("Other");
//
//        private String value;
//
//        TypeEnum(String value) {
//            this.value = value;
//        }
//
//        @JsonValue
//        public String getValue() {
//            return value;
//        }
//
//        @Override
//        public String toString() {
//            return String.valueOf(value);
//        }
//
//        @JsonCreator
//        public static TypeEnum fromValue(String value) {
//            for (TypeEnum b : TypeEnum.values()) {
//                if (b.value.equals(value)) {
//                    return b;
//                }
//            }
//            throw new IllegalArgumentException("Unexpected value '" + value + "'");
//        }
//    }


    @Column(name = "type")
    private String type;
}
