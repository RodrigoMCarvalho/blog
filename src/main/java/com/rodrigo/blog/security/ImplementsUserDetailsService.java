package com.rodrigo.blog.security;

import com.rodrigo.blog.model.Usuario;
import com.rodrigo.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }
        return new User(usuario.getUsername(), usuario.getPassword(),
                true, true, true, true, usuario.getAuthorities());
    }
}
