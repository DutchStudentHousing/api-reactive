package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("property_details")
public class PropertyDetails {
    @Column("property_details_id")
    @Id
    Long id;
    @Column("description")
    String description;
    @Column("roommates")
    String roomMates;
    public enum GenderRoomMatesType {
        Male,
        Female,
        Mixed,
        Unknown
    }
    @Column("gender_roommates")
    GenderRoomMatesType genderRoomMates;
    public enum FurnishedType {
        Furnished,
        Unfurnished,
        Uncarpeted
    }
    @Column("furnished")
    FurnishedType furnished;
    public enum EnergyLabelType {
        A,B,C,D,E,F,G,
        Unknown
    }
    @Column("energy_label")
    EnergyLabelType energyLabel;
    @Column("internet")
    Boolean internet;
    @Column("pets_allowed")
    Boolean petsAllowed;
    @Column("own_toilet")
    Boolean ownToilet;
    @Column("own_bathroom")
    Boolean ownBathroom;
    @Column("own_kitchen")
    Boolean ownKitchen;
    @Column("smoking_allowed")
    Boolean smokingAllowed;
    @Column("last_seen_at")
    LocalDateTime lastSeenAt;
}
