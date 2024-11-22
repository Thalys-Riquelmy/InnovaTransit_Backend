package innovaBackend.InnovaTransit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "empresa")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150)
	private String nome;
	
	@Column(length = 20, unique = true)
	private String cnpj;
	
	@Column(length = 80, name = "url_busca")
	private String urlBusca;
	
	@Column (length = 80, name = "api_key")
	private String apiKey;
	
	@Column (length = 80, name = "cron_expression")
	private String cronExpression;

}
