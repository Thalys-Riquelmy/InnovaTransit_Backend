package innovaBackend.InnovaTransit.importacao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Motorista;
import innovaBackend.InnovaTransit.model.Veiculo;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;
import innovaBackend.InnovaTransit.repository.MotoristaRepository;
import innovaBackend.InnovaTransit.repository.VeiculoRepository;
import innovaBackend.InnovaTransit.service.ImportacaoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImportacaoServiceTest {

	@Autowired
	private ImportacaoService importacaoService;
	
	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired 
	VeiculoRepository veiculoRepository;
	
	@Autowired
	FolhaServicoRepository folhaServicoRepository;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	 @Test
	 @Order(1)
	 public void testImportacaoMotorista() {
		 this.importacaoService.importarMotoristas();
		 List<Motorista> response = motoristaRepository.findAll();
	     assertNotNull(response);
	 }
	 
	 @Test
	 @Order(2)
	 public void testImportacaoVeiculo() {
		 this.importacaoService.importarVeiculos();
		 List<Veiculo> response = veiculoRepository.findAll();
		 assertNotNull(response);
	 }
	 
	 @Test
	 @Order(3)
	 public void testImportacaoFolhaServico() {
		 this.importacaoService.importarFolhaServico();
		 List<FolhaServico> response = folhaServicoRepository.findAll();
		 assertNotNull (response);
	 }
}
