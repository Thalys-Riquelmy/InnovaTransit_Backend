package innovaBackend.InnovaTransit.auth;

import innovaBackend.InnovaTransit.controller.AuthController;
import innovaBackend.InnovaTransit.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        // Arrange
        String email = "miguel@gmail.com";
        String senha = "senha123";
        String expectedToken = "tokenGerado"; // Token fictício para o teste

        // Simula o comportamento do serviço
        when(authService.authenticate(email, senha)).thenReturn(expectedToken);

        // Act
        ResponseEntity<String> response = authController.login(email, senha);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, response.getBody());
    }

    @Test
    public void testLoginFailure() {
        // Arrange
        String email = "miguel@gmail.com";
        String senha = "senhaIncorreta";

        // Simula uma exceção lançada pelo serviço
        when(authService.authenticate(email, senha)).thenThrow(new RuntimeException("Senha inválida"));

        // Act
        ResponseEntity<String> response = authController.login(email, senha);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Senha inválida", response.getBody());
    }
}
