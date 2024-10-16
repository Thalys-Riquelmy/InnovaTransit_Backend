package innovaBackend.InnovaTransit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import innovaBackend.InnovaTransit.service.EmailService;
import innovaBackend.InnovaTransit.responseDTO.EmailRequestDTO;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        // Passando os parâmetros com os nomes corretos
        return emailService.sendEmail(
                emailRequest.getDestinatario(),  // aqui ajusta para "destinatario"
                emailRequest.getAssunto(),      // aqui ajusta para "assunto"
                emailRequest.getMensagem()      // aqui ajusta para "mensagem"
        );
    }
}
