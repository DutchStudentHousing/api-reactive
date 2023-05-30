package nl.dsh.api.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("property_details")
public class PropertyDetails {

    @Column("property_id")
    private String propertyId;
    @Id
    @Column("property_details_id")
    private int id;
    @Column("description")
    private String description;
    @Column("description_translated")
    private String descriptionTranslated;
    @Column("roommates")
    private String roomMates;
    public enum GenderRoomMatesEnum {
        Female,
        Male,
        Mixed,
        Unknown
    }
    @Column("gender_roommates")
    private GenderRoomMatesEnum genderRoomMates;
    public enum FurnishedEnum{
        Furnished,
        Unfurnished,
        Uncarpeted
    }
    @Column("furnished")
    private FurnishedEnum furnished;
    public enum EnergyLabelEnum{
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        Unknown
    }
    @Column("energy_label")
    private EnergyLabelEnum energyLabel;

    @Column("internet")
    private boolean internet;
    @Column("pets_allowed")
    private boolean petsAllowed;
    @Column("own_toilet")
    private boolean ownToilet;
    @Column("own_bathroom")
    private boolean ownBathroom;
    @Column("own_kitchen")
    private boolean ownKitchen;
    @Column("smoking_allowed")
    private boolean smokingAllowed;
    @Column("last_seen_at")
    private LocalDateTime lastSeenAt;
}
