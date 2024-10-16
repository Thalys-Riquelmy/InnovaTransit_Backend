package innovaBackend.InnovaTransit.usuario;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import innovaBackend.InnovaTransit.model.Usuario;
import innovaBackend.InnovaTransit.repository.UsuarioRepository;
import innovaBackend.InnovaTransit.service.UsuarioService;

@SpringBootTest
public class UsuarioTest {

	    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testBuscaPorEmail() {
    	this.usuarioService.buscaPorEmail("eduardo@gmail.com");
    	Optional<Usuario> usuario = usuarioRepository.findByEmail("eduardo@gmail.com");
    	assertNotNull(usuario);
    }

}
