package edu.egg.tinder.web.repositorios;

import edu.egg.tinder.web.entidades.Foto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("fotoRepositorio")
public interface FotoRepositorio extends JpaRepository<Foto, String>{
    
}
