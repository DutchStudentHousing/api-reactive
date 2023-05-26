package nl.dsh.api.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyFilter {
    Optional<String> city;
    Optional<Float> minRent;
    Optional<Float> maxRent;
    Optional<Float> lat;
    Optional<Float> _long;
    Optional<Float> distance;
}
