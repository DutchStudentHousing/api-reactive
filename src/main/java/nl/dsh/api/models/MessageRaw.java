package nl.dsh.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("message")
public class MessageRaw {
    Long senderId;
    Long receiverId;

    String propertyId;
    String content;
}
