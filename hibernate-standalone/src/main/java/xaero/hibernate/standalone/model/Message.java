package xaero.hibernate.standalone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages", schema = "hibernate")
public class Message {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    /*
      if set strategy to auto - then hibernate with postgres will search for hibernate_sequence in public schema
      if table doesn't support sequences - then generation type 'sequence' switches to table generation
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_generator")
    // by this way we can use separated sequence for each table in postgres. especially it handles an 'serial' type id
    @SequenceGenerator(
            name = "message_id_generator",
            sequenceName = "messages_id_seq",
            schema = "hibernate"
            /*
              allocationSize by default is 50, it means that hibernate will query sequence for next id from sequence one
              time in 50 ids. this way hibernate reserves an amount of 50 ids for it's requirements.
              notice that increment in sequence could be 50 too for correct behaviour. otherwise we can use
              allocationSize = 1, but in this way hibernate will query sequence every time when it needs an id, what can
              affect the application performance in high load situations.
              so, one more moment is when app will restart it will cause a jump forward to 50 ids, which will make a
              gap in ids. it is sufficient to understand.
             */
    )
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    private User author;
    @Column(name = "message", nullable = false)
    private String message;
    @OneToMany
    @JoinTable(
            name = "likes",
            schema = "hibernate",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likedUserList;
}
