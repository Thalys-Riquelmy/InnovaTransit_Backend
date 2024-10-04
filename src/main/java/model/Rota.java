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
@Table(name = "rota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	   
	@Column(nullable = false, length = 30)
	private int curso;
	
	@Column(nullable = false, length = 30)
	private String tipo;
	
	@Column(nullable = false, length = 30)
	private String evento;
	
	@Column(nullable = false, length = 30)
	private LocalTime horaInicio;
	
	@Column(nullable = false, length = 30)
	private LocalTime horaFim;
	
	@Column(nullable = false, length = 30)
	private String motivoCancelamento;
}
