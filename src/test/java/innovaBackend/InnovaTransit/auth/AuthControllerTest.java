package innovaBackend.InnovaTransit.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import innovaBackend.InnovaTransit.service.AuthService;

@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private AuthService authService;

    private String token;

    @BeforeEach
    public void setUp() {
        // Obtém o token chamando o AuthService
        token = authService.authenticate("miguel@gmail.com", "senha123");
    }

    @Test
    public void testGetMotoristaEndpoint() {
        // Endpoint protegido que você deseja testar
        String protectedUrl = "http://localhost:8080/api/motorista"; // URL do endpoint

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Adiciona o token ao cabeçalho

        HttpEntity<String> request = new HttpEntity<>(headers);

        // Faz a chamada ao endpoint protegido
        ResponseEntity<String> response = new RestTemplate().exchange(
                protectedUrl,
                HttpMethod.GET,
                request,
                String.class
        );

        // Verifica se a resposta foi bem-sucedida
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody()); // Verifica se o corpo da resposta não é nulo
    }
}
