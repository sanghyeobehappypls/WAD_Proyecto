package com.ipn.mx.modelo.dao;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import com.ipn.mx.modelo.dto.EditorialDTO;
import com.ipn.mx.modelo.entidades.Editorial;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import org.hibernate.engine.spi.SessionImplementor;

public class EditorialDAO {
    
    public void create(EditorialDTO dto)
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
    
    public void update(EditorialDTO dto)
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
    
    public void delete(EditorialDTO dto)
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
    
    public EditorialDTO read(EditorialDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdEditorial()));
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
            Query q = session.createQuery("from Editorial e order by e.idEditorial");
            for(Editorial e: (List<Editorial>) q.list())
            {
                EditorialDTO dto = new EditorialDTO();
                dto.setEntidad(e);
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
