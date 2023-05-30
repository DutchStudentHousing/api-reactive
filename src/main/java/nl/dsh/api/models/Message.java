package nl.dsh.api.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("message")
public class Message {
    @Id
    @Column("message_id")
    private int messageId;
    //Relation one to one?
    @Column("sender_id")
    private int senderId;
    //Relation one to one?
    @Column("receiver_id")
    private int receiverId;
    @Column("property_id") //
    private String propertyId;
    @Column("content")
    private String content;
    @Column("timestamp")
    private LocalDateTime timeStamp;
}
