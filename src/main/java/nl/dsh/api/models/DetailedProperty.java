package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailedProperty {
    Property property;
    PropertyDetails details;
    PropertyMatch match;
}
