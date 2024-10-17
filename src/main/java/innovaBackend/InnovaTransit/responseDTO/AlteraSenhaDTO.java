package innovaBackend.InnovaTransit.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlteraSenhaDTO {
	
	private String email;
    private String senha;

}
