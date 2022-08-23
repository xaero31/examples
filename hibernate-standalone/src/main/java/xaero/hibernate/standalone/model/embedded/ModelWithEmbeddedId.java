package xaero.hibernate.standalone.model.embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class ModelWithEmbeddedId {

    @EmbeddedId
    private EmbeddedPK id;
}
