package innovaBackend.InnovaTransit.controller;

import innovaBackend.InnovaTransit.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        String token = authService.authenticate(email, senha);
        return ResponseEntity.ok(token);
    }
}
