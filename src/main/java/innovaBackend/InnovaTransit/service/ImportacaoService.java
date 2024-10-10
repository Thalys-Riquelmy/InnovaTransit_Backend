package innovaBackend.InnovaTransit.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.integracao.IntegracaoService;
import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Motorista;
import innovaBackend.InnovaTransit.model.Veiculo;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;
import innovaBackend.InnovaTransit.repository.MotoristaRepository;
import innovaBackend.InnovaTransit.repository.VeiculoRepository;

@Service
public class ImportacaoService {

	@Autowired
	private IntegracaoService integracaoService;
	
	@Autowired
    private MotoristaRepository motoristaRepository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private FolhaServicoRepository folhaServicoRepository;
    
    public void importar() {
    	this.importarMotoristas();
    	this.importarVeiculos();
    	this.importarFolhaServico();
    }
    
	public void importarMotoristas() {
		List<MotoristaResponse> motoristas = integracaoService.getDataMotorista();
		
		for (MotoristaResponse motorista: motoristas) {
			Motorista motoristaExistente = motoristaRepository.findByMatricula(motorista.getMatricula());
			if (motoristaExistente != null) {
              motoristaExistente.setNome(motorista.getNome());
              motoristaRepository.save(motoristaExistente); 
              System.out.println("Motorista atualizado: " + motoristaExistente.getMatricula());
			}else {
              Motorista novoMotorista = new Motorista();
              novoMotorista.setMatricula(motorista.getMatricula());
              novoMotorista.setNome(motorista.getNome()); 
              novoMotorista.setEmail(motorista.getEmail());
              motoristaRepository.save(novoMotorista);
              System.out.println("Novo motorista criado: " + novoMotorista.getMatricula());
			}
		}
	}
	
	public void importarVeiculos() {
		List<VeiculoResponse> veiculos = integracaoService.getDataVeiculo();
		
		for(VeiculoResponse veiculo: veiculos) {
			Veiculo veiculoExistente = veiculoRepository.findByNumeroVeiculo(veiculo.getNumeroVeiculo());
			if(veiculoExistente != null) {
				veiculoExistente.setNumeroVeiculo(veiculo.getNumeroVeiculo());
				veiculoExistente.setHodometroInicial(veiculo.getHodometroInicial());
				veiculoExistente.setCatracaInicial(veiculo.getCatracaInicial());
				veiculoRepository.save(veiculoExistente);
			}else {
				Veiculo novoVeiculo = new Veiculo();
				novoVeiculo.setNumeroVeiculo(novoVeiculo.getNumeroVeiculo());
				novoVeiculo.setCatracaInicial(novoVeiculo.getCatracaInicial());
				novoVeiculo.setHodometroInicial(novoVeiculo.getHodometroInicial());
				veiculoRepository.save(novoVeiculo);  
			}
		}
		
	}
	
	public void importarFolhaServico() {
	    List<FolhaServico> folhasServicosExistentes = folhaServicoRepository.findByDataServico(LocalDate.now());
	    
	    if (folhasServicosExistentes == null || folhasServicosExistentes.isEmpty()) {
	        List<FolhaServicoResponse> folhasServicosExternas = integracaoService.getDataFolhaServico();
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	        for (FolhaServicoResponse folhaServicoResponse : folhasServicosExternas) {
	            FolhaServico novaFolhaServico = new FolhaServico();
	            novaFolhaServico.setMotorista(motoristaRepository.findByMatricula(folhaServicoResponse.getMotorista()));
	            novaFolhaServico.setVeiculo(veiculoRepository.findByNumeroVeiculo(folhaServicoResponse.getVeiculo()));
	            LocalTime horarioInicio = LocalTime.parse(folhaServicoResponse.getHorarioInicial(), formatter);
	            novaFolhaServico.setHorarioInicial(horarioInicio);
	            LocalTime horarioFim = LocalTime.parse(folhaServicoResponse.getHorarioFinal(), formatter);
	            novaFolhaServico.setHorarioFinal(horarioFim);
	            novaFolhaServico.setDataServico(LocalDate.now());
	            novaFolhaServico.montarTarefas(folhaServicoResponse.getTarefas());
	            folhaServicoRepository.save(novaFolhaServico);
	        }
	        
	        System.out.println("Folhas de serviço importadas da API externa e salvas no banco.");
	    } else {
	        System.out.println("Folhas de serviço já existentes para a data " + folhasServicosExistentes);
	    }
	}
		
}
