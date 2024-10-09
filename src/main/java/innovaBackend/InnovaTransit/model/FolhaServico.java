package innovaBackend.InnovaTransit.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

@Table(name = "folha_servico")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class FolhaServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(nullable = true, length = 150)
	private String observacao;
		
	@Column(nullable = false, length = 30)
	private LocalDate dataServico;
	
	//hora inicial de execução
	private LocalTime horaInicial;
	
	//hora final de execução
	private LocalTime horaFinal;
	
	//hora inicial programado
	private LocalTime horarioInicial;
		
	//hora final programado
	private LocalTime horarioFinal;
	
	private Motorista motorista;
	
	private Veiculo veiculo;
	
	private List<Tarefa> tarefas;

}
