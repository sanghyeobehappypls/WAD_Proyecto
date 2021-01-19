package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.entidades.Grafica;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

public class GraficaDAO {
    private static final String SQL_LIBROSXGENERO = "select g.nombre as nombre, count(*) as cantidad from Libro l, Genero g where l.idGenero = g.idGenero group by g.idGenero";
    private static final String SQL_LIBROSXAUTOR = "select a.nombre || ' ' || a.paterno || ' ' || a.materno as nombre, count(*) as cantidad from Libro l, Autor a where l.idAutor = a.idAutor group by a.idAutor";
    private static final String SQL_LIBROSXEDITORIAL = "select e.nombre as nombre, count(*) as cantidad from Libro l, Editorial e where l.idEditorial = e.idEditorial group by e.idEditorial";
    
    public List listaLXG()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList<GraficaDTO>();
        
        try{
            transaction.begin();
            //select * from Usuario u order by u.idUsuario
            Query q = session.createQuery(SQL_LIBROSXGENERO).setResultTransformer(Transformers.aliasToBean(Grafica.class));
            
            for(Grafica g : (List<Grafica>) q.list())
            {
                GraficaDTO dto = new GraficaDTO();
                dto.setEntidad(g);
                lista.add(dto);
            }
            
            if(lista.isEmpty())
                lista = null;
            
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return lista;
    }
    
    public List listaLXE()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList<GraficaDTO>();
        
        try{
            transaction.begin();
            //select * from Usuario u order by u.idUsuario
            Query q = session.createQuery(SQL_LIBROSXEDITORIAL).setResultTransformer(Transformers.aliasToBean(Grafica.class));
            
            for(Grafica g : (List<Grafica>) q.list())
            {
                GraficaDTO dto = new GraficaDTO();
                dto.setEntidad(g);
                lista.add(dto);
            }
            
            if(lista.isEmpty())
                lista = null;
            
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        
        return lista;
    }
    
    public List listaLXA()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList<GraficaDTO>();
        
        try{
            transaction.begin();
            //select * from Usuario u order by u.idUsuario
            Query q = session.createQuery(SQL_LIBROSXAUTOR).setResultTransformer(Transformers.aliasToBean(Grafica.class));
            
            for(Grafica g : (List<Grafica>) q.list())
            {
                GraficaDTO dto = new GraficaDTO();
                dto.setEntidad(g);
                lista.add(dto);
            }
            
            if(lista.isEmpty())
                lista = null;
            
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return lista;
    }
}













