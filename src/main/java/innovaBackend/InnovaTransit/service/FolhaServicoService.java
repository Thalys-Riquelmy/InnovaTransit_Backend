package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Tarefa;
import innovaBackend.InnovaTransit.model.Veiculo;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;
import innovaBackend.InnovaTransit.repository.TarefaRepository;
import innovaBackend.InnovaTransit.repository.VeiculoRepository;
import innovaBackend.InnovaTransit.responseDTO.FolhaServicoDTO;
import innovaBackend.InnovaTransit.responseDTO.TarefaDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class FolhaServicoService {

    @Autowired
    private FolhaServicoRepository folhaServicoRepository;
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<FolhaServico> findAll() {
        return folhaServicoRepository.findAll();
    }

    public Optional<FolhaServico> findById(Long id) {
        return folhaServicoRepository.findById(id);
    }

    public FolhaServico save(FolhaServico folhaServico) {
        return folhaServicoRepository.save(folhaServico);
    }

    public void deleteById(Long id) {
        folhaServicoRepository.deleteById(id);
    }
    
    public FolhaServico obterFolhaServicoDoMotorista(Integer matricula) {
    	
    	return this.folhaServicoRepository.findByDataServicoAndMotorista_Matricula(LocalDate.now(), matricula);
    }
    
    public FolhaServicoDTO obterFolhaServicoPorDataEMatricula(LocalDate dataServico, Integer matricula) {
        FolhaServico folhaServico = folhaServicoRepository.findByDataServicoAndMotorista_Matricula(dataServico, matricula);
        
        if (folhaServico != null) {
            // Mapeia a entidade para DTO
            List<TarefaDTO> tarefasDTO = folhaServico.getTarefas().stream()
                .map(TarefaDTO::new) // Converte cada Tarefa em TarefaDTO
                .collect(Collectors.toList());

            return new FolhaServicoDTO(
                folhaServico.getId(),
                folhaServico.getObservacao(),
                folhaServico.getDataServico(),
                folhaServico.getHoraInicial(),
                folhaServico.getHoraFinal(),
                folhaServico.getHorarioInicial(),
                folhaServico.getHorarioFinal(),
                folhaServico.isFinalizada(),
                folhaServico.getVeiculo(),
                tarefasDTO // Adiciona a lista de TarefaDTOs
            );
        }
        return null; // Retorna nulo se não encontrar
    }
    
    public void iniciarFolhaDeServico(Long id, LocalTime horaInical) {
        Optional<FolhaServico> folhaOptional = folhaServicoRepository.findById(id);
        
        if (folhaOptional.isPresent()) {
            FolhaServico folhaServico = folhaOptional.get();
            
            // Definir o formato da data esperado
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate dataFormatada = LocalDate.parse(data, formatter);
            
            // Definir a hora inicial com a hora atual do dispositivo
            folhaServico.setHoraInicial(horaInical);
//            folhaServico.setDataServico(dataFormatada);
            
            // Salvar as alterações
            folhaServicoRepository.save(folhaServico);
            System.out.println("Hora inicial salva com sucesso: " + horaInical);
        } else {
            // Se a folha de serviço não for encontrada, lançar uma exceção
            throw new RuntimeException("Folha de serviço com ID " + id + " não encontrada.");
        }
    }
    
    public void iniciarTarefa(Long id, LocalTime horaInicial) {
        // Buscar a folha de serviço pelo ID da tarefa
        Optional<FolhaServico> folhaOptional = folhaServicoRepository.findById(id);

        if (folhaOptional.isPresent()) {
            FolhaServico folhaServico = folhaOptional.get();

//            // Se a folha de serviço ainda não tiver sido iniciada, iniciar
//            if (folhaServico.getHoraInicial() == null) {
//                this.iniciarFolhaDeServico(folhaServico.getId(), horaInicial, folhaServico.getData());
//            }

            // Buscar o veículo associado à folha de serviço
            Veiculo veiculo = folhaServico.getVeiculo();
            if (veiculo == null) {
                throw new RuntimeException("Nenhum veículo associado à folha de serviço.");
            }

            // Pegar os valores de hodômetro e catraca do veículo
            int hodometroVeiculo = veiculo.getHodometro();
            int catracaVeiculo = veiculo.getCatraca();

            // Percorrer as tarefas da folha de serviço para iniciar a primeira não iniciada
            folhaServico.getTarefas().stream()
                .filter(tarefa -> tarefa.getHoraInicio() == null) // Verificar tarefas que ainda não foram iniciadas
                .findFirst() // Pegar a primeira tarefa não iniciada
                .ifPresent(tarefa -> {
                    // Definir a hora inicial da tarefa
                    tarefa.setHoraInicio(horaInicial);

                    // Definir hodômetro e catraca inicial da tarefa com os valores do veículo
                    tarefa.setHodometroInicial(hodometroVeiculo);
                    tarefa.setCatracaInicial(catracaVeiculo);

                    // Salvar as alterações da tarefa e da folha de serviço
                    folhaServicoRepository.save(folhaServico);
                    System.out.println("Tarefa iniciada com sucesso na hora: " + tarefa.getHoraInicio());
                });
        } else {
            throw new RuntimeException("Folha de serviço com ID " + id + " não encontrada.");
        }
    }
    
    public void finalizarTarefa(Long id, LocalTime horaFim, Integer hodometroFinal, Integer catracaFinal) {
        // Buscar a tarefa pelo ID
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        
        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();

            // Atualizar a hora final, hodômetro final e catraca final da tarefa
            tarefa.setHoraFim(horaFim);
            tarefa.setHodometroFinal(hodometroFinal);
            tarefa.setCatracaFinal(catracaFinal);
            
            // Buscar a folha de serviço associada à tarefa
            FolhaServico folhaServico = tarefa.getFolhaServico();
            if (folhaServico != null) {
                // Buscar o veículo associado à folha de serviço
                Veiculo veiculo = folhaServico.getVeiculo();
                if (veiculo != null) {
                    // Atualizar o hodômetro e a catraca do veículo
                    veiculo.setHodometro(hodometroFinal);
                    veiculo.setCatraca(catracaFinal);
                    
                    // Salvar as alterações no veículo
                    veiculoRepository.save(veiculo);
                } else {
                    throw new RuntimeException("Veículo associado à folha de serviço não encontrado.");
                }
            } else {
                throw new RuntimeException("Folha de serviço associada à tarefa não encontrada.");
            }

            // Salvar as alterações da tarefa
            tarefaRepository.save(tarefa);
            
            System.out.println("Tarefa e veículo finalizados com sucesso.");
        } else {
            // Se a tarefa não for encontrada, lançar uma exceção
            throw new RuntimeException("Tarefa com ID " + id + " não encontrada.");
        }
    }


    public void finalizarFolhaServico(Long id, LocalTime horaFinal) {
        // Buscar a folha de serviço pelo ID
        Optional<FolhaServico> folhaOptional = folhaServicoRepository.findById(id);

        if (folhaOptional.isPresent()) {
            FolhaServico folhaServico = folhaOptional.get();

            // Atualizar os campos da folha de serviço com base nos parametros recebidos
            folhaServico.setHoraFinal(horaFinal);
            folhaServico.setFinalizada(true); // Marcar a folha de serviço como finalizada

            // Salvar as alterações da folha de serviço
            folhaServicoRepository.save(folhaServico);

            System.out.println("Folha de serviço finalizada com sucesso.");
        } else {
            throw new RuntimeException("Folha de serviço com ID " + id + " não encontrada.");
        }
    }
}
