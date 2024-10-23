package innovaBackend.InnovaTransit.responseDTO;

import java.time.LocalTime;

import innovaBackend.InnovaTransit.model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    private Long id;
    private String evento;
    private LocalTime horarioFim;
    private LocalTime horarioInicio;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String motivoCancelamento;
    private int hodometroInicial;
    private int hodometroFinal;
    private int catracaInicial;
    private int catracaFinal;
    private Boolean cancelado;

    // Construtor que aceita Tarefa
    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.evento = tarefa.getEvento();
        this.horarioFim = tarefa.getHorarioFim();
        this.horarioInicio = tarefa.getHorarioInicio();
        this.horaInicio = tarefa.getHoraInicio();
        this.horaFim = tarefa.getHoraFim();
        this.motivoCancelamento = tarefa.getMotivoCancelamento();
        this.hodometroInicial = tarefa.getHodometroInicial();
        this.hodometroFinal = tarefa.getHodometroFinal();
        this.catracaInicial = tarefa.getCatracaInicial();
        this.catracaFinal = tarefa.getCatracaFinal();
        this.cancelado = tarefa.getCancelado();
    }
}
