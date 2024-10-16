package innovaBackend.InnovaTransit.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO {

    private String destinatario;
    private String assunto;
    private String mensagem;
}
