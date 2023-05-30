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
@Table("user_rents_out_property")
public class AccountRentingOut {
    @Id
    @Column("uid")
    private int id;

    @Column("property_id")
            //@Foreign key?
    private String propertyId;
    //TODO relation between AccountRentingOut and property
}
