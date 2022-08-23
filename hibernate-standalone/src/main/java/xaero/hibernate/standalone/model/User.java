package xaero.hibernate.standalone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "hibernate")
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(
            name = "user_id_generator",
            sequenceName = "users_id_seq",
            schema = "hibernate"
    )
    private int id;
    @Column(name = "username", nullable = false, updatable = false)
    private String username;
    @Column(name = "description")
    private String description;
}
