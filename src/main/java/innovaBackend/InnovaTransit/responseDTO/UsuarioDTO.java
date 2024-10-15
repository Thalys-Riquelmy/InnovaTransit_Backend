package innovaBackend.InnovaTransit.responseDTO;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UsuarioDTO {

	private int id;
    private String nome;
    private String email;
    private String username;
    private String tipoUsuario;
    private List<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
