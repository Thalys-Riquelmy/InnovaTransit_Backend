package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Motorista {


@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id; 

   @Column(nullable = false, length = 100)
   private String nome;

   @Column(nullable = false, unique = true, length = 11)
   private String cpf;

   @Column(nullable = false, length = 30)
   private String situacao;

   @Column(nullable = false, length = 30)
   private String telefone;

   @Column(nullable = false, length = 30)
   private String licenca;

   @Column(nullable = false, length = 30)
   private String validadeLicenca;

   @Column(nullable = false, length = 30)
   private String categoriaLicenca;

   @Column(nullable = false, length = 30)
   private String carteiraTrabalho;

   @Column(nullable = false)
   private LocalDate dataAdmissao;

   @Column(nullable = false, length = 150)
   private String endereco;

   @Column(nullable = false, length = 30)
   private String cep;

   @Column(nullable = false, length = 30)
   private String estado;

   @Column(nullable = false, length = 50)
   private String bairro;

   @Column(nullable = false)
   private int matricula; 
}
