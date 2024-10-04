package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private int numeroVeiculo;

	@Column(nullable = false, length = 30)
	private String placa;
	
	@Column(nullable = false, length = 30)
	private String modelo;
	
	@Column(nullable = false, length = 30)
	private String marca;
	
	@Column(nullable = false, length = 30)
	private int anoFabricacao;
	
	@Column(nullable = false, length = 30)
	private int numPassageiro;
	
	@Column(nullable = false, length = 30)
	private String chassi;
	
	@Column(nullable = false, length = 30)
	private String cor;
	
	@Column(nullable = false, length = 30)
	private String tipoCobustivel;
	
	@Column(nullable = false, length = 30)
	private LocalDate dataAquisicao;
	
	@Column(nullable = false, length = 30)
	private String situacao;
	
	@Column(nullable = false, length = 30)
	private String renavam;
	
	@Column(nullable = false, length = 30)
	private int kilometragem;
	   
	@Column(nullable = false, length = 30)
	private LocalDate ultimaManutencao;
	
	@Column(nullable = false, length = 30)
	private boolean veiculoEspecial;
}
