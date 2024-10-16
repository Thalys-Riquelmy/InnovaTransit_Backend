package innovaBackend.InnovaTransit.configSecurity;

import java.security.SecureRandom;
import java.util.Base64;

public class SenhaUtil {

    private static final SecureRandom random = new SecureRandom();

    public static String gerarSenha() {
        byte[] senhaBytes = new byte[24]; // Tamanho da senha em bytes
        random.nextBytes(senhaBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(senhaBytes); // Gera uma senha codificada
    }
}
