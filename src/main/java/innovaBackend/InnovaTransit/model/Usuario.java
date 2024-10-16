package innovaBackend.InnovaTransit.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario implements UserDetails { // Tornando a classe abstract

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(length = 80)
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + getTipoUsuario())); // Usa o método getTipoUsuario
    }

    // Método para obter o tipo de usuário
    public String getTipoUsuario() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implementar conforme necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implementar conforme necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implementar conforme necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Implementar conforme necessário
    }
}
