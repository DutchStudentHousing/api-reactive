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
@Table(name = "users")
public class Account {
    @Id
    @Column("uid")
    private int id;
    @Column("name")
    private String name;
    @Column("email")
    private String email;
    @Column("hashed_pw") //How to model a password with spring sec..
    private String pw;
    @Column("created")
    private LocalDateTime createdAt;
    @Column("last_active")
    private LocalDateTime lastActiveAt;
}
