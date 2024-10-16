package innovaBackend.InnovaTransit.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import innovaBackend.InnovaTransit.model.Gerente;
import innovaBackend.InnovaTransit.model.Motorista;
import innovaBackend.InnovaTransit.repository.GerenteRepository;
import innovaBackend.InnovaTransit.repository.MotoristaRepository;
import innovaBackend.InnovaTransit.repository.UsuarioRepository;
import innovaBackend.InnovaTransit.service.AuthService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private MotoristaRepository motoristaRepository;
    
    @Autowired
    private GerenteRepository gerenteRepository;

    @PersistenceContext
    EntityManager entityManager;

    private Motorista motorista;
    private Gerente gerente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criar um motorista com senha criptografada
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = passwordEncoder.encode("senha123"); // Criptografa a senha
        
//        gerente = new Gerente();
//        gerente.setNome("Caio");
//        gerente.setEmail("caio@gmail.com");
//        gerente.setSenha(senhaCriptografada);
//        gerenteRepository.save(gerente);

        // Cria o motorista e salva no banco de dados
//        motorista = new Motorista();
//        motorista.setNome("Miguel");
//        motorista.setEmail("miguel@gmail.com");
//        motorista.setMatricula(12345);
//        motorista.setSenha(senhaCriptografada);
//        motoristaRepository.save(motorista);
    }

    @Test
    @Order(1)
    public void testAuthenticateSuccess() {
        // Autentica com email e senha corretos
        String token = authService.authenticate("miguel@gmail.com", "senha123");
        assertNotNull(token);
    }

    @Test
    @Order(2)
    public void testAuthenticateFailure() {
        // Tenta autenticar com a senha incorreta
        assertThrows(RuntimeException.class, () -> {
            authService.authenticate("miguel@gmail.com", "senhaIncorreta");
        });
    }

    @Test
    @Order(3)
    public void testLoadUserByUsername() {
        // Carrega o usuário pelo email
        Motorista userDetails = (Motorista) authService.loadUserByUsername("miguel@gmail.com");
        assertNotNull(userDetails);
        assertNotNull(userDetails.getEmail());
    }
}
