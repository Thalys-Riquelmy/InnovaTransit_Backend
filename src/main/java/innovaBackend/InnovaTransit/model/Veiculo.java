package innovaBackend.InnovaTransit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "veiculo")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30, name = "numero_veiculo")
	private int numeroVeiculo;
	
	@Column(nullable = false, length = 30, name = "hodometro")
	private int hodometro;
	
	@Column(nullable = false, length = 30, name = "catraca")
	private int catraca;

}
