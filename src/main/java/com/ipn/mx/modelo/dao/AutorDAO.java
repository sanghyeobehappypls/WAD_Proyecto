package com.ipn.mx.modelo.dao;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import com.ipn.mx.modelo.dto.AutorDTO;
import com.ipn.mx.modelo.entidades.Autor;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import org.hibernate.engine.spi.SessionImplementor;

public class AutorDAO {
    
    public void create(AutorDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            session.save(dto.getEntidad());
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }
    
    public void update(AutorDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            session.update(dto.getEntidad());
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }
    
    public void delete(AutorDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            session.delete(dto.getEntidad());
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
    }
    
    public AutorDTO read(AutorDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdAutor()));
            dto.setEntidad(dto.getEntidad());
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return dto;
    }
    
    public List readAll()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList<>();
        
        try{
            transaction.begin();
            //select * from Usuario u order by u.idUsuario
            Query q = session.createQuery("from Autor a order by a.idAutor");
            for(Autor a: (List<Autor>) q.list())
            {
                AutorDTO dto = new AutorDTO();
                dto.setEntidad(a);
                lista.add(dto);
            }
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return lista;
    }
    
    public Connection conexion()
    {
        Connection con = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            SessionImplementor sessionImp = (SessionImplementor) session;
            con = sessionImp.connection();
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return con;
    }
}
