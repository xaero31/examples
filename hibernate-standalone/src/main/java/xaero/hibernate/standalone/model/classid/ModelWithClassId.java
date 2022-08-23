package xaero.hibernate.standalone.model.classid;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * allows not to extract composite id fields to external class and use it directly here
 */
@Data
@Entity
@IdClass(ClassId.class)
public class ModelWithClassId {

    @Id
    private int id;
    @Id
    private int secondId;
}
