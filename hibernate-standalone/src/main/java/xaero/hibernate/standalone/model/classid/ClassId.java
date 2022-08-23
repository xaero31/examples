package xaero.hibernate.standalone.model.classid;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClassId implements Serializable {

    private int id;
    private int secondId;
}
