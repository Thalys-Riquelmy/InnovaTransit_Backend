package innovaBackend.InnovaTransit.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rota")
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
		
	@Column(nullable = false, length = 30, name = "horario_fim")
	private LocalTime horarioFim;

	//hora programada
	@Column(nullable = false, length = 30, name = "horario_inicio")
	private LocalTime horarioInicio;

	//hora executada
	@Column(nullable = false, length = 30, name = "hora_inicio")
	private LocalTime horaInicio;

	@Column(nullable = false, length = 30, name = "hora_fim")
	private LocalTime horaFim;

	@Column(nullable = false, length = 30, name = "motivo_cancelamento")
	private String motivoCancelamento;
	
	@Column(nullable = false, length = 30, name = "hodometro_inicial")
	private String hodometroInicial;
	
	@Column(nullable = false, length = 30, name = "hodometro_final")
	private String hodometroFinal;
	
	@Column(nullable = false, length = 30, name = "catraca_inicial")
	private String catracaInicial;
	
	@Column(nullable = false, length = 30, name = "catraca_final")
	private String catracaFinal;

	private Boolean cancelado;
	
	@ManyToOne
	private FolhaServico folhaServico;
}
