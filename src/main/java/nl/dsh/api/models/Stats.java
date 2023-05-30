package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Stats {
    @Id
    @Column("city")
    String city;
    @Column("mean")
    float mean;
    @Column("median")
    float median;
    @Column("stddev")
    float stddev;
}
