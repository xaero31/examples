package xaero.spring.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spring_message", schema = "hibernate")
public class SpringMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spring_message_id_gen")
    @SequenceGenerator(
            name = "spring_message_id_gen",
            sequenceName = "spring_message_id_seq",
            schema = "hibernate"
    )
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private long id;
    @Column(name = "message", nullable = false)
    private String message;
    @Version
    private long version;
}
