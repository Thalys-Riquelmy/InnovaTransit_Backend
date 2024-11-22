package innovaBackend.InnovaTransit.integracao.response;

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

	private String data;
	
	private String dataServico; 
	
	private String observacao;
	
	private String horaInicial;
	
	private String horaFinal;
	
	private String horarioInicial;
		
	private String horarioFinal;
	
	private Integer motorista;
	
	private Integer veiculo;
	
	private List<TarefaResponse> tarefas;

}
