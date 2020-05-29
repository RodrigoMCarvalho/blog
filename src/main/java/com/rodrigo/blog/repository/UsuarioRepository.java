package com.rodrigo.blog.repository;

import com.rodrigo.blog.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findByLogin(String login);
}
