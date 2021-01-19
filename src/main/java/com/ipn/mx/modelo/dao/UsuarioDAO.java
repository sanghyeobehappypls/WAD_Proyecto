package com.ipn.mx.modelo.dao;

import java.io.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import org.hibernate.engine.spi.SessionImplementor;

public class UsuarioDAO{
    
    public void create(UsuarioDTO dto)
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
    
    public void update(UsuarioDTO dto)
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
    
    public void delete(UsuarioDTO dto)
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
    
    public UsuarioDTO read(UsuarioDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdUsuario()));
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
            Query q = session.createQuery("from Usuario u order by u.idUsuario");
            for(Usuario u: (List<Usuario>) q.list())
            {
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(u);
                lista.add(dto);
            }
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return lista;
    }
    
    public UsuarioDTO login(UsuarioDTO dto)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            Query q = session.createQuery("from Usuario u where u.email = ?1 and u.claveUsuario = ?2");
            q.setParameter(1, dto.getEntidad().getEmail());
            q.setParameter(2, dto.getEntidad().getClaveUsuario());
            
            List lista = q.list();
            
            if(!lista.isEmpty())
                dto.setEntidad((Usuario) lista.get(0));
            else
                dto = null;
            
            transaction.commit();
        }catch(HibernateException he){
            if(transaction != null && transaction.isActive())
                transaction.rollback();
        }
        return dto;
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

