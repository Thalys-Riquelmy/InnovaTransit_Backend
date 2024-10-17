package innovaBackend.InnovaTransit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.configSecurity.SenhaUtil;
import innovaBackend.InnovaTransit.model.Usuario;
import innovaBackend.InnovaTransit.repository.UsuarioRepository;
import innovaBackend.InnovaTransit.responseDTO.AlteraSenhaDTO;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public void buscaPorEmail(String email) {
        // Busca o usuário pelo email
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        // Verifica se o usuário está presente
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get(); // Pega o usuário encontrado
            String novaSenha = SenhaUtil.gerarSenha(); // Gera uma nova senha
            String senhaCriptografada = passwordEncoder.encode(novaSenha); // Criptografa a nova senha

            usuario.setSenha(senhaCriptografada); // Atualiza a senha do usuário
            usuarioRepository.save(usuario); // Salva o usuário no banco de dados

            // Envia o email com a nova senha
            emailService.sendEmail(email, "Senha para primeiro acesso", "Sua nova senha é: " + novaSenha);
        } else {
            System.out.println("Email não cadastrado: " + email);
        }
    }
    
    public void alteraSenha(AlteraSenhaDTO alteraSenhaDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(alteraSenhaDTO.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Criptografa a nova senha antes de salvar
            String senhaCriptografada = new BCryptPasswordEncoder().encode(alteraSenhaDTO.getSenha());
            usuario.setSenha(senhaCriptografada);
            usuario.setTrocarSenha(false);

            // Salva o usuário com a nova senha no banco de dados
            usuarioRepository.save(usuario);
        } else {
            // Tratamento caso o usuário não seja encontrado
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }


}
