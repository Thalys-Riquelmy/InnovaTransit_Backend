package innovaBackend.InnovaTransit.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaDTO extends UsuarioDTO{

	private int matricula;
}
