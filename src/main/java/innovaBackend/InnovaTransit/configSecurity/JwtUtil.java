package innovaBackend.InnovaTransit.configSecurity;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET_KEY = "your_secret_key"; // Troque pela sua chave secreta
//    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora
//    
//    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities, boolean trocarSenha) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())); // Adiciona as permissões
//        claims.put("trocarSenha", trocarSenha); // Adiciona o campo trocarSenha
//        return createToken(claims, email);
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    public boolean validateToken(String token, String email) {
//        final String username = extractUsername(token);
//        return (username.equals(email) && !isTokenExpired(token));
//    }
//
//    String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractAllClaims(token).getExpiration().before(new Date());
//    }
//}

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import innovaBackend.InnovaTransit.model.Usuario;
import innovaBackend.InnovaTransit.repository.UsuarioRepository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    private final String SECRET_KEY = "sua_chave_secreta"; // Troque pela sua chave secreta
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 8; // 8 horas

//    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities, boolean changePassword) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//        claims.put("changePassword", changePassword);
//        return createToken(claims, email);
//    }

 // Método em JwtUtil
    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities, boolean changePassword) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            boolean trocarSenha = usuario.isTrocarSenha();

            Map<String, Object> claims = new HashMap<>();
            claims.put("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            claims.put("trocarSenha", trocarSenha); // Ou utilize changePassword conforme necessário

            return createToken(claims, email);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, String email) {
        final String username = extractUser(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    public String extractUser(String token) {
        return extractAllClaims(token).getSubject();
    }

//    private Claims extractAllClaims(String token) {
//        try {
//            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            throw new RuntimeException("Token inválido ou expirado");
//        }
//    }
    
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expirado. Por favor, faça login novamente.");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Token não suportado. Verifique o formato do token.");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token malformado. O token não possui o formato correto.");
        } catch (SignatureException e) {
            throw new RuntimeException("Assinatura do token inválida. Verifique se a chave secreta está correta.");
        } catch (Exception e) {
            throw new RuntimeException("Token inválido. Erro: " + e.getMessage());
        }
    }


    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}

