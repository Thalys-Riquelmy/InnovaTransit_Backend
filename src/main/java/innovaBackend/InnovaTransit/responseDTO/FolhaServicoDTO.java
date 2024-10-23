package innovaBackend.InnovaTransit.responseDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import innovaBackend.InnovaTransit.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaServicoDTO {

	private Long id;
    private String observacao;
    private LocalDate dataServico;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;
    private boolean finalizada;
    private Veiculo veiculo;
    private List<TarefaDTO> tarefas;
    
}
