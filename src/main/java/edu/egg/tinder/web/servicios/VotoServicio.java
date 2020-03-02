package edu.egg.tinder.web.servicios;

import edu.egg.tinder.web.entidades.Mascota;
import edu.egg.tinder.web.entidades.Voto;
import edu.egg.tinder.web.errores.ErrorServicio;
import edu.egg.tinder.web.repositorios.MascotaRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import edu.egg.tinder.web.repositorios.VotoRepositorio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VotoServicio {

    @Autowired
    private NotificacionServicio notificacionServicio;
    
    @Autowired
    @Qualifier("votoRepositorio")
    private VotoRepositorio votoRepositorio;
 
    @Autowired
    private MascotaRepositorio mascotaRepositorio;
    
    public void votar(String idUsuario, String idMascota1, String idMascota2) throws ErrorServicio{
        Voto voto = new Voto();
        voto.setFecha(new Date());
        
        Optional<Mascota> respuesta = mascotaRepositorio.findById(idMascota1);
        if(respuesta.isPresent()){
            Mascota mascota1 = respuesta.get();
            if(mascota1.getUsuario().getId().equals(idUsuario)){
                voto.setMascota1(mascota1);
            } else{
                throw new ErrorServicio("No tiene permisos para realizar la operación solicitada.");
            }
        } else {
            throw new ErrorServicio("No existe una mascota vinculada a ese identificador.");
        }
        
        Optional<Mascota> respuesta2 = mascotaRepositorio.findById(idMascota2);
        if(respuesta2.isPresent()){
            Mascota mascota2 = respuesta.get();
            voto.setMascota1(mascota2);
        
            notificacionServicio.enviar("Tu mascota ha sido votada.", "Tinder de Mascota", mascota2.getUsuario().getMail());
        
        } else {
            throw new ErrorServicio("No existe una mascota vinculada a ese identificador.");
        }
        
        votoRepositorio.save(voto);
    }
    
    
    public void resonder(String idUsuario, String idVoto) throws ErrorServicio{
        Optional<Voto> respuesta = votoRepositorio.findById(idVoto);
        if(respuesta.isPresent()){
            Voto voto = respuesta.get();
            voto.setRespuesta(new Date());
            
            if(voto.getMascota2().getUsuario().getId().equals(idUsuario)){
                notificacionServicio.enviar("Tu voto fue correspondido.", "Tinder de Mascota", voto.getMascota1().getUsuario().getMail());
                votoRepositorio.save(voto);
            } else {
                throw new ErrorServicio("No tiene permisos para realizar la operación.");
            }
        }
    }
    
    
    
}
