package innovaBackend.InnovaTransit.integracao.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) 
public class FolhaServicoResponse {

	private String observacao;
		
	private LocalDate dataServico;
	
	private LocalTime horaInicial;
	
	private LocalTime horaFinal;
	
	private LocalTime horarioInicial;
		
	private LocalTime horarioFinal;
	
	private MotoristaResponse motorista;
	
	private VeiculoResponse veiculo;
	
	private List<TarefaResponse> tarefas;

}
