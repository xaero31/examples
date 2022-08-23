package xaero.hibernate.standalone.model.mapsid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Data
@Entity
@NoArgsConstructor
public class Profile {

    @Id
    private int id;

    /**
     * id in the profile could be the same as the id in the user because of @MapsId
     */
    @OneToOne
    @MapsId
    private User user;
}
