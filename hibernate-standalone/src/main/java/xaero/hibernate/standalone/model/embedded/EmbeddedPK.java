package xaero.hibernate.standalone.model.embedded;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * should be serializable
 */
@Data
@Embeddable
public class EmbeddedPK implements Serializable {

    private int id;
    private int secondId;
}
