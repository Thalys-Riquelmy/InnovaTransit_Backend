package innovaBackend.InnovaTransit.responseDTO;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import innovaBackend.InnovaTransit.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private int id;
    private String nome;
    private String email;
    private Empresa empresa;
}
