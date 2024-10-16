package innovaBackend.InnovaTransit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(String destinatario, String assunto, String mensagem) {
    	
    	try {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("thalysriquelmy8@gmail.com");
	        message.setTo(destinatario);
	        message.setSubject(assunto);
	        message.setText(mensagem);
	        javaMailSender.send(message);
	        return "Email enviado";
    	}catch(Exception e) {
    		return "Erro ao tentar enviar email" + e.getLocalizedMessage();
    	}
    }
}
