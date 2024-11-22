package innovaBackend.InnovaTransit.controller;

import innovaBackend.InnovaTransit.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://192.168.1.110:8101")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
//        try {
//            String token = authService.authenticate(email, senha);
//            return ResponseEntity.ok(token);
//        } catch (UsernameNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou senha incorretos.");
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Erro ao processar o login: " + e.getMessage());
//        }
//    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        System.out.println("Tentando autenticar o usuário com o email: " + email);

        try {
            // Chama o serviço de autenticação
            String token = authService.authenticate(email, senha);
            
            // Caso a autenticação seja bem-sucedida
            System.out.println("Autenticação bem-sucedida para o email: " + email);
            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException e) {
            // Caso o usuário não seja encontrado
            String message = "Usuário não encontrado para o email: " + email;
            System.err.println(message + " - Detalhes: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } catch (BadCredentialsException e) {
            // Caso a senha esteja incorreta
            String message = "Senha incorreta fornecida para o email: " + email;
            System.err.println(message + " - Detalhes: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } catch (Exception e) {
            // Caso ocorra qualquer outro erro
            String message = "Erro desconhecido ao processar o login. Detalhes: " + e.getMessage();
            System.err.println(message);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }



}
