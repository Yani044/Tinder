package edu.egg.tinder.web.repositorios;

import edu.egg.tinder.web.entidades.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("zonaRepositorio")
public interface ZonaRepositorio extends JpaRepository<Zona, String> {
    
}
