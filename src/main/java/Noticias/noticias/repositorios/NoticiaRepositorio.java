/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Noticias.noticias.repositorios;

import Noticias.noticias.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nacho
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{
    
    @Query("Select n from Noticia n WHERE n.titulo LIKE CONCAT('%',:titulo,'%') ")
    public List<Noticia> buscarTitulo(@Param ("titulo") String titulo);
   
    @Query("Select n from Noticia n ORDER BY FUNCTION('RAND')")
    public List<Noticia> getRandomNews(int limit);
}
