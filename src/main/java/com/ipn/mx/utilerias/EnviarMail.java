package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarMail {
    
    public void enviarCorreo(String destinatario, String asunto, String mensaje)
    {
        try {
            //Propiedades de la conexión
            //Crear una cuenta de Gmail
            Properties p = new Properties();
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", "practica2mmayeng@gmail.com");
            p.setProperty("mail.smtp.auth", "true");
            
            //https://myaccount.google.com/lesssecureapps
            
            //Crear la sesión
            Session sesion = Session.getDefaultInstance(p);
            
            //Trabajar con el mensaje
            MimeMessage message = new MimeMessage(sesion);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);
            
            Transport t = sesion.getTransport("smtp");
            t.connect(p.getProperty("mail.smtp.user"), "P2marcomayen"); //contraseña del correo creado
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (AddressException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    public static void main(String[] args) {
        EnviarMail email = new EnviarMail();
        String destinatario = "practica2mmayeng@gmail.com";
        String asunto = "Registro satisfactorio";
        String texto = "Su registro fue satisfactorio...";
        
        email.enviarCorreo(destinatario, asunto, texto);
    }
    */
}
