package model;

import java.time.LocalTime;

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
@Table(name = "jornada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Jornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	   
	@Column(nullable = false, length = 30)
	private LocalTime horaInicio;
	
	@Column(nullable = false, length = 30)
	private LocalTime horaFinal;
	
	@Column(nullable = false, length = 30)
	private LocalTime horaAbertura;
	
	@Column(nullable = false, length = 30)
	private LocalTime horaFechamento;
	
	@Column(nullable = false, length = 30)
	private String hodometroInicial;
	
	@Column(nullable = false, length = 30)
	private String hodometroFinal;
	
	@Column(nullable = false, length = 30)
	private String catracaInicial;
	
	@Column(nullable = false, length = 30)
	private String catracaFinal;
}
