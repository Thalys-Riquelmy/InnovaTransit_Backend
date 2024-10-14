package innovaBackend.InnovaTransit.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MOTORISTA")
public class Motorista extends Usuario {

	@Column(length = 30)
    private int matricula;
}
