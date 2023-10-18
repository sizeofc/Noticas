/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Noticias.noticias.servicios;

import Noticias.noticias.entidades.Noticia;
import Noticias.noticias.repositorios.NoticiaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacho
 */
@Service
public class NoticiaServicio {
    
    @Autowired
    NoticiaRepositorio noticiaR;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws Exception {
        
        validar(titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setCuerpo(cuerpo);
        noticia.setTitulo(titulo);
        noticia.setFechaAlta(new Date());
        
        noticiaR.save(noticia);
        
    }
    
    public List<Noticia> buscarTitulo(String titulo) {
        
        try {
            return noticiaR.buscarTitulo(titulo);
            
        } catch (Exception e) {
            
            return null;
        }
        
    }
    
    public List<Noticia> listarTodos() {
        
        return noticiaR.findAll();
        
    }
    
    public void modificarNoticia(Long id, String titulo, String cuerpo) throws Exception {
        
        validar(titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        Optional<Noticia> respuesta = noticiaR.findById(id);
        
        if (respuesta.isPresent()) {
            
            noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setFechaAlta(new Date());
            noticiaR.save(noticia);
        }
        
    }
    
    public void bajarNoticia(Long id) {
        
        Noticia noticia = new Noticia();
        
        Optional<Noticia> respuesta = noticiaR.findById(id);
        
        if (respuesta.isPresent()) {
            
            noticia = respuesta.get();
            noticia.setAlta(false);
            
            noticiaR.save(noticia);
        }
    }
    
        public void darAlta(Long id) {
        
        Noticia noticia = new Noticia();
        
        Optional<Noticia> respuesta = noticiaR.findById(id);
        
        if (respuesta.isPresent()) {
            
            noticia = respuesta.get();
            noticia.setAlta(true);
            
            noticiaR.save(noticia);
        }
    }
    
    
    public void validar(String titulo, String cuerpo) throws Exception {
        
        if (titulo.isEmpty() || titulo == null) {
            throw new Exception("El titulo no puede ser nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new Exception("El cuerpo no puede ser nulo");
        }
        
    }
    
    public Noticia getOne(Long id)throws Exception{
        return noticiaR.getById(id);
    }
    
    public List<Noticia> getNoticiasRandom(int limit){
        return noticiaR.getRandomNews(limit);
    }
}
