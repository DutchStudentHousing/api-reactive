package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class KnownValues {
    @Column("minRent")
    float minRent;
    @Column("maxRent")
    float maxRent;
    @Column("cities")
    List<String> cities;
    @Column("minSqm")
    int minSqm;
    @Column("maxSqm")
    int maxSqm;
    @Column("propertyCount")
    long propertyCount;
}
