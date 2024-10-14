package innovaBackend.InnovaTransit.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
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
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public class Usuario implements UserDetails {

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
        // Retorne uma lista de autoridades do usuário, se aplicável
        return null; // Pode ser implementado conforme necessário
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
