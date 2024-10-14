package innovaBackend.InnovaTransit.folhaServico;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;
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
import innovaBackend.InnovaTransit.model.Tarefa;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;
import innovaBackend.InnovaTransit.repository.TarefaRepository;
import innovaBackend.InnovaTransit.service.FolhaServicoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FolhaServicoTest {

	@Autowired
	private FolhaServicoService folhaServicoService;
	
	@Autowired
	private FolhaServicoRepository folhaServicoRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	@Order(1)
	public void testObterFolhaServico() {
		List<FolhaServico> response = folhaServicoRepository.findAll();
		folhaServicoService.obterFolhaServicoDoMotorista(1);
		assertNotNull(response);
		List<Tarefa> tarefas = response.get(0).getTarefas();
		assertNotNull(tarefas);
	}
	
	@Test
	@Order(2)
	public void testIniciarFolhaServico() {
		List<FolhaServico> response = folhaServicoRepository.findAll();
		LocalTime horaTeste1 = LocalTime.of(9, 0);
		folhaServicoService.iniciarFolhaDeServico(1L, horaTeste1);
		assertNotNull (response);
		List<Tarefa> tarefas = response.get(0).getTarefas();
		assertNotNull (tarefas);
	}
	
	@Test
	@Order(3)
	public void testIniciarTarefa() {
		List<Tarefa> response = tarefaRepository.findAll();
		LocalTime horaTeste2 = LocalTime.of(10, 30); 
		folhaServicoService.iniciarTarefa(1L, horaTeste2);
		assertNotNull (response);
		
		
	}
	
	@Test
	@Order(4)
	public void testFinalizarTarefa() {
		List<Tarefa> response = tarefaRepository.findAll();
		LocalTime horaTeste3 = LocalTime.of(12, 45); 
		folhaServicoService.finalizarTarefa(1L, horaTeste3, 2000, 2500);
		assertNotNull (response);
	}
	
	@Test
	@Order(5)
	public void testFinalizarFolhaServico() {
		List<FolhaServico> response = folhaServicoRepository.findAll();
		LocalTime testeHora4 = LocalTime.of(14, 10);
		assertNotNull (response);
		folhaServicoService.finalizarFolhaServico(1L, testeHora4);
		List<Tarefa> tarefas = response.get(0).getTarefas();
		assertNotNull (tarefas);
	}
}
