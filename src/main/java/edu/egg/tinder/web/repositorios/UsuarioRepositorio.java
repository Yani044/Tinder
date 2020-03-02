
package edu.egg.tinder.web.repositorios;

import edu.egg.tinder.web.entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("usuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
   
    @Query("SELECT c FROM Usuario c WHERE c.mail = :mail")
    public Usuario buscarPorMail(@Param("mail") String mail);
}
