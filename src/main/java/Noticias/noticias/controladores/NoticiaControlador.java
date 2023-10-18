/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Noticias.noticias.controladores;

import Noticias.noticias.entidades.Noticia;
import Noticias.noticias.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nacho
 */
@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
    
    @Autowired
    NoticiaServicio noticiaS;
    
    @GetMapping("/registrar") // /noticia/registrar
    public String registrar(){
        
        
        
        return "noticia_form.html";
        
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String cuerpo, @RequestParam String titulo, ModelMap modelo ){
        
        try {
            
            noticiaS.crearNoticia(titulo, cuerpo);
            
            modelo.put("exito", "La noticia se creó correctamente ");
            return "redirect:/noticia/lista";
        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
            return "noticia_form.html";
        }
        
        
    }
    
    @GetMapping("/lista")
    public String listarNoticia(ModelMap modelo){
        
        List<Noticia> noticias = noticiaS.listarTodos();
        
        modelo.put("noticias", noticias);
        
        return "noticia_lista.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
        try {
            Noticia noticia = noticiaS.getOne(Long.parseLong(id));
            modelo.put("noticia",noticia);
            return "noticia_modificar.html";
        } catch (Exception ex) {
           modelo.put("error",ex.getMessage());
           return "redirect:/noticia/lista";
        }
    }
    
    //agregar postmapping para guardar la noticia modificada
    
}
