package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Tarefa;
import innovaBackend.InnovaTransit.model.Veiculo;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;
import innovaBackend.InnovaTransit.repository.TarefaRepository;
import innovaBackend.InnovaTransit.repository.VeiculoRepository;
import innovaBackend.InnovaTransit.responseDTO.FolhaServicoDTO;
import innovaBackend.InnovaTransit.responseDTO.MotoristaDTO;
import innovaBackend.InnovaTransit.responseDTO.TarefaDTO;

import java.time.LocalDate;
import java.util.Comparator;
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
    
    public List<FolhaServicoDTO> listarFolhasPorData(LocalDate data) {
        List<FolhaServico> folhasServico = folhaServicoRepository.findByDataServico(data);
        
        // Converte cada FolhaServico em FolhaServicoDTO
        return folhasServico.stream()
            .map(folhaServico -> {
                // Mapeia a entidade para DTO, incluindo o Motorista
                List<TarefaDTO> tarefasDTO = folhaServico.getTarefas().stream()
                    .sorted(Comparator.comparing(Tarefa::getHorarioInicio))
                    .map(TarefaDTO::new) // Converte cada Tarefa em TarefaDTO
                    .collect(Collectors.toList());

                // Converte Motorista para MotoristaDTO
                MotoristaDTO motoristaDTO = new MotoristaDTO(folhaServico.getMotorista());
                
                return new FolhaServicoDTO(
                    folhaServico.getId(),
                    folhaServico.getObservacao(),
                    folhaServico.getDataServico(),
                    folhaServico.getHoraInicial(),
                    folhaServico.getHoraFinal(),
                    folhaServico.getHorarioInicial(),
                    folhaServico.getHorarioFinal(),
                    folhaServico.isFinalizada(),
                    folhaServico.getVeiculo(), // Passando Veiculo diretamente
                    motoristaDTO, // Passando MotoristaDTO
                    tarefasDTO // Adiciona a lista de TarefaDTOs
                );
            })
            .collect(Collectors.toList());
    }
    
    public FolhaServicoDTO obterFolhaServicoPorDataEMatricula(LocalDate dataServico, Integer matricula) {
        FolhaServico folhaServico = folhaServicoRepository.findByDataServicoAndMotorista_Matricula(dataServico, matricula);
        
        if (folhaServico != null) {
            // Mapeia a entidade para DTO
            List<TarefaDTO> tarefasDTO = folhaServico.getTarefas().stream()
                .sorted(Comparator.comparing(Tarefa::getHorarioInicio)) 
                .map(TarefaDTO::new) // Converte cada Tarefa em TarefaDTO
                .collect(Collectors.toList());

            // Converte Motorista para MotoristaDTO
            MotoristaDTO motoristaDTO = new MotoristaDTO(folhaServico.getMotorista());
            
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
                motoristaDTO, // Passando MotoristaDTO
                tarefasDTO // Adiciona a lista de TarefaDTOs
            );
        }
        return null; // Retorna nulo se não encontrar
    }

    
    public void iniciarFolhaDeServico(Long id, LocalTime horaInicial) {
        Optional<FolhaServico> folhaOptional = folhaServicoRepository.findById(id);

        if (folhaOptional.isPresent()) {
            FolhaServico folhaServico = folhaOptional.get();

            // Atualizar a hora inicial
            folhaServico.setHoraInicial(horaInicial);

            // Salvar as alterações
            folhaServicoRepository.save(folhaServico);
            System.out.println("Hora inicial salva com sucesso: " + horaInicial);
        } else {
            // Se a folha de serviço não for encontrada, lançar uma exceção
            throw new RuntimeException("Folha de serviço com ID " + id + " não encontrada.");
        }
    }
    
    public void iniciarTarefa(Long idTarefa, LocalTime horaInicial) {
        // Buscar a tarefa pelo ID
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(idTarefa);

        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();

            // Buscar a folha de serviço associada à tarefa
            FolhaServico folhaServico = tarefa.getFolhaServico();
            if (folhaServico == null) {
                throw new RuntimeException("Nenhuma folha de serviço associada à tarefa.");
            }

            // Buscar o veículo associado à folha de serviço
            Veiculo veiculo = folhaServico.getVeiculo();
            if (veiculo == null) {
                throw new RuntimeException("Nenhum veículo associado à folha de serviço.");
            }

            // Pegar os valores de hodômetro e catraca do veículo
            int hodometroVeiculo = veiculo.getHodometro();
            int catracaVeiculo = veiculo.getCatraca();

            // Definir a hora inicial da tarefa
            tarefa.setHoraInicio(horaInicial);

            // Definir hodômetro e catraca inicial da tarefa com os valores do veículo
            tarefa.setHodometroInicial(hodometroVeiculo);
            tarefa.setCatracaInicial(catracaVeiculo);

            // Salvar as alterações da tarefa
            tarefaRepository.save(tarefa); // Salvar a tarefa, agora atualizada
            System.out.println("Tarefa iniciada com sucesso na hora: " + tarefa.getHoraInicio());
        } else {
            throw new RuntimeException("Tarefa com ID " + idTarefa + " não encontrada.");
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
    
    public void cancelarTarefa(Long id, LocalTime horaFim, String motivoCancelamento) {
    	Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
    	
    	if (tarefaOptional.isPresent()) {
    		Tarefa tarefa = tarefaOptional.get();
    		
    		//Atualiza o motivo cancelamento e marca cancelado como true e grava a hora do cancelamento
    		tarefa.setMotivoCancelamento(motivoCancelamento);
    		tarefa.setHoraFim(horaFim);
    		tarefa.setCancelado(true);
    		
    		//Salvar as alterações feitas da tarefa
    		tarefaRepository.save(tarefa);
    		
    	} else {
    		throw new RuntimeException("Tarefa com o Id" + id + "não encontrada.");
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
