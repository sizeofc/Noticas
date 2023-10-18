/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Noticias.noticias.controladores;

import Noticias.noticias.entidades.Noticia;
import Noticias.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nacho
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    NoticiaServicio noticiaServicio;
    
    public String index(ModelMap modelo){
        List<Noticia> noticias= noticiaServicio.getNoticiasRandom(5);
        modelo.put("noticias",noticias);
        return "index.html";
        
    }
    
    
}
