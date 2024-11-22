package innovaBackend.InnovaTransit.responseDTO;

import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.model.Motorista;
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

	private int id;
    private String nome;
    private String email;
    private int matricula;
    private Empresa empresa;
	
    public MotoristaDTO(Motorista motorista) {
        this.id = motorista.getId();
        this.nome = motorista.getNome();
        this.email = motorista.getEmail();
        this.matricula = motorista.getMatricula();
        this.empresa = motorista.getEmpresa();
        
    }
}
