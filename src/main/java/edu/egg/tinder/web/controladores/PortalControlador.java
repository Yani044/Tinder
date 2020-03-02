package edu.egg.tinder.web.controladores;

import edu.egg.tinder.web.entidades.Zona;
import edu.egg.tinder.web.errores.ErrorServicio;
import edu.egg.tinder.web.repositorios.ZonaRepositorio;
import edu.egg.tinder.web.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    
    @GetMapping("/registro")
    public String registro(ModelMap modelo){
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        
        return "registro.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, @RequestParam String idZona){
        try {
            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2, idZona);
        } catch (ErrorServicio ex) {
            List<Zona> zonas = zonaRepositorio.findAll();
            modelo.put("zonas", zonas);

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave2);
            
            return "registro.html";
        }
        
        modelo.put("titulo", "Bienvenido a Tinder de Mascotas.");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");
        return "exito.html";
    }
}
