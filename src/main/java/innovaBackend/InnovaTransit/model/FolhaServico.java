package innovaBackend.InnovaTransit.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import innovaBackend.InnovaTransit.integracao.response.TarefaResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "folha_servico")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class FolhaServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column
//	private LocalDate data;
	 
	@Column(length = 150)
	private String observacao;
		
	@Column(length = 30)
	private LocalDate dataServico;
	
	//hora inicial de execução
	@Column(length = 30, name = "hora_inicial")
	private LocalTime horaInicial;
	
	//hora final de execução
	@Column(length = 30, name ="hora_final")
	private LocalTime horaFinal;
	
	//hora inicial programado
	@Column(nullable = false, length = 30, name = "horario_inicial")
	private LocalTime horarioInicial;
		
	//hora final programado
	@Column(nullable = false, length = 30, name = "horario_final")
	private LocalTime horarioFinal;
	
	private boolean finalizada;
	
	@ManyToOne
	private Motorista motorista;
	
	@ManyToOne
	private Veiculo veiculo;
	
	@ManyToOne
	private Empresa empresa;
	
	@OneToMany(mappedBy = "folhaServico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Tarefa> tarefas;
	
	public void montarTarefas(List<TarefaResponse> tarefasResponse) {
		if (this.tarefas == null) {
			this.tarefas = new ArrayList<Tarefa>();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		for(TarefaResponse tarefaResponse : tarefasResponse) {
			Tarefa novaTarefa = new Tarefa();
			novaTarefa.setFolhaServico(this);
			
			LocalTime horarioInicio = LocalTime.parse(tarefaResponse.getHorarioInicial(), formatter);
	        novaTarefa.setHorarioInicio(horarioInicio);
	        LocalTime horarioFim = LocalTime.parse(tarefaResponse.getHorarioFinal(), formatter);
	        novaTarefa.setHorarioFim(horarioFim);
			novaTarefa.setEvento(tarefaResponse.getEvento());
			this.tarefas.add(novaTarefa);
		}
	}

}
