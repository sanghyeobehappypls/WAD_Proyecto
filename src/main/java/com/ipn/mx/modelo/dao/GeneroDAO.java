package com.ipn.mx.modelo.dao;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import com.ipn.mx.modelo.dto.GeneroDTO;
import com.ipn.mx.modelo.entidades.Genero;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import org.hibernate.engine.spi.SessionImplementor;

public class GeneroDAO {
    
    public void create(GeneroDTO dto)
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
    
    public void update(GeneroDTO dto)
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
    
    public void delete(GeneroDTO dto)
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
    
    public GeneroDTO read(GeneroDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdGenero()));
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
            Query q = session.createQuery("from Genero g order by g.idGenero");
            for(Genero g: (List<Genero>) q.list())
            {
                GeneroDTO dto = new GeneroDTO();
                dto.setEntidad(g);
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
