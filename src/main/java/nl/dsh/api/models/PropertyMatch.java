package nl.dsh.api.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("property_match")
public class PropertyMatch {
    //Relation one to one
    @Column("property_Id")
    private String propertyId;
    @Id
    @Column("property_match_id")
    private int propertyMatchId;
    @Column("age_min")
    private String ageMin;
    @Column("age_max")
    private String ageMax;
    public enum GenderMatchEnum {
        Female,
        Male,
        Mixed,
        NotImportant
    }
    @Column("gender")
    private GenderMatchEnum genderMatch;
    public enum MatchStatusEnum {
        Student,
        WorkingStudent,
        Working,
        LookingForAJob,
        NotImportant
        }
    @Column("match_status")
    private MatchStatusEnum matchStatus;

}
