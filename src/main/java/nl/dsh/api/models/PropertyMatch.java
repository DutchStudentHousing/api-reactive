package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("property_match")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyMatch {
    @Column("property_match_id")
    @Id Long id;
    public enum GenderType {
        Female,
        Male,
        Mixed,
        NotImportant
    }
    @Column("gender")
    GenderType gender;
    public enum MatchStatusType {
        Student,
        WorkingStudent,
        Working,
        LookingForAJob,
        NotImportant
    }
    @Column("match_status")
    MatchStatusType[] matchStatus;

    @Column("age_min")
    Long minAge;

    @Column("age_max")
    Long maxAge;
}
