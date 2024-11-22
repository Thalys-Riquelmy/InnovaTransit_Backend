package innovaBackend.InnovaTransit.responseDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Motorista;
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
    private MotoristaDTO motorista; 
    private List<TarefaDTO> tarefas;
    
    // Construtor que aceita um FolhaServico como parâmetro
    public FolhaServicoDTO(FolhaServico folha) {
        this.id = folha.getId();
        this.observacao = folha.getObservacao();
        this.dataServico = folha.getDataServico();
        this.horaInicial = folha.getHoraInicial();
        this.horaFinal = folha.getHoraFinal();
        this.horarioInicial = folha.getHorarioInicial();
        this.horarioFinal = folha.getHorarioFinal();
        this.finalizada = folha.isFinalizada();
        this.motorista = new MotoristaDTO(folha.getMotorista());
        //this.motorista = folha.getMotorista();
        this.tarefas = folha.getTarefas().stream()
            .map(TarefaDTO::new) // Converte cada Tarefa para TarefaDTO
            .collect(Collectors.toList());
    }
    
}
