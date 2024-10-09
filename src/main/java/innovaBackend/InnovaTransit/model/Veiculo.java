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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private int numeroVeiculo;

	@Column(nullable = false, length = 30)
	private String hodometroInicial;
	
	@Column(nullable = false, length = 30)
	private String hodometroFinal;
	
	@Column(nullable = false, length = 30)
	private String catracaInicial;
	
	@Column(nullable = false, length = 30)
	private String catracaFinal;

}
