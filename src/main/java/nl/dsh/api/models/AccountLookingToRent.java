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
@Table("user_looking_to_rent")
public class AccountLookingToRent {
    @Id //FK id tho
    @Column("uid")
    private int id;
    @Column("age")
    private int age;
    @Column("gender") //Enum?
    private String gender;
    //TODO: Implement status in table?
    /*
    public enum StatusEnum{
        Student,
        WorkingStudent,
        Working,
        LookingForAJob
    }
    @Column("status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    */
}
