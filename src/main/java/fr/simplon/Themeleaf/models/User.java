package fr.simplon.Themeleaf.models;


import fr.simplon.Themeleaf.controller.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private String password;

    private Role role = Role.Normie;

    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
