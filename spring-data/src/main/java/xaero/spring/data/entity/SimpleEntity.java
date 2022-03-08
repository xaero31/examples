package xaero.spring.data.entity;

import javax.persistence.*;

@Table(name = "simple_entity")
@Entity
public class SimpleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "simple_value")
    private String simpleValue;
}
