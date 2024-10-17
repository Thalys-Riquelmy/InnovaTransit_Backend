package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import innovaBackend.InnovaTransit.responseDTO.AlteraSenhaDTO;
import innovaBackend.InnovaTransit.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;

    //Endpoint para buscar usuário por e-mail e enviar nova senha
    @PostMapping("/primeiro-acesso")
    public ResponseEntity<String> buscaPorEmail(@RequestParam String email) {
        try {
            usuarioService.buscaPorEmail(email);
            return ResponseEntity.ok("Email cadastrado, nova senha enviada.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar a solicitação: " + e.getMessage());
        }
    }
    
    //Endpoint para alteração de senha por email
    @PostMapping("/altera-senha")
    public ResponseEntity<String> alteraSenha(@RequestBody AlteraSenhaDTO alteraSenhaDTO) {
        usuarioService.alteraSenha(alteraSenhaDTO);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}
