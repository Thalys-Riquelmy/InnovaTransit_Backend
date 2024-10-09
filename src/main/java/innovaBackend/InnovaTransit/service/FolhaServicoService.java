package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Tarefa;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FolhaServicoService {

    @Autowired
    private FolhaServicoRepository folhaServicoRepository;

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
    
    public void iniciarFolhaDeServico(FolhaServico folhaServico) {
    	
    	
    }
    
    public Tarefa inicarTarefa(Tarefa tarefa) {
    	//TODO
    	//para primeira tarefa chamar o iniciar folha de servico
    	//persistir/atualizar repositorio
    	return tarefa;
    }
    
    public void finalizarTarefa(Tarefa tarefa) {
    	//TODO
    	//ao finalizar ultima tarefa finalizar folha de seviço
    }
    
    /*public FolhaServico finalizarFolhaServico(FolhaServico folhaServico) {
    	
    }
    
    
    public FolhaServico obterFolhaServicoDoMotorista(String matricula) {
    	return this.folhaServicoRepository.o
    }*/
    
}
