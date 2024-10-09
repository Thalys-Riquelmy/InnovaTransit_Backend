package innovaBackend.InnovaTransit.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String tipo;
	
	@Column(nullable = false, length = 30)
	private String evento;
		
	@Column(nullable = false, length = 30)
	private LocalTime horarioFim;

	//hora programada
	@Column(nullable = false, length = 30)
	private LocalTime horarioInicio;

	//hora executada
	@Column(nullable = false, length = 30)
	private LocalTime horaInicio;

	@Column(nullable = false, length = 30)
	private LocalTime horaFim;

	@Column(nullable = false, length = 30)
	private String motivoCancelamento;
	
	@Column(nullable = false, length = 30)
	private String hodometroInicial;
	
	@Column(nullable = false, length = 30)
	private String hodometroFinal;
	
	@Column(nullable = false, length = 30)
	private String catracaInicial;
	
	@Column(nullable = false, length = 30)
	private String catracaFinal;

	private Boolean cancelado;
}
