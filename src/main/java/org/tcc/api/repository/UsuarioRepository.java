package org.tcc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tcc.api.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("select us from Usuario us " +
            "where us.login = :userName")
    Optional<Usuario> findUsuarioByUserName(String userName);

    @Query("select case when count (us)>0 then true else false end " +
            "from Usuario us " +
            "where us.login = :login ")
    boolean existsUsuario(String login);
}
