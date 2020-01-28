package app.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UZERS")
public class User {
    @Id
    private String username;
    private String password;
}