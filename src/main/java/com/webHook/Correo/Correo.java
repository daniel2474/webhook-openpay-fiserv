package com.webHook.Correo;



 import java.util.Calendar;
 import java.util.Properties;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import javax.mail.Address;
 import javax.mail.Message;
 import javax.mail.MessagingException;
 import javax.mail.Session;
 import javax.mail.Transport;
 import javax.mail.internet.AddressException;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeMessage;
 
 
 
 public class Correo
 {
   private String correoEnvia;
   private String contrasena;
   private String destinatario;
   private String copiaoculta;
   
   public Correo() {}
  
   public Correo(String correoEnvia, String contrasena, String destinatario, String copiaoculta) {
     this.correoEnvia = correoEnvia;
     this.contrasena = contrasena;
     this.destinatario = destinatario;
     this.copiaoculta = copiaoculta;
   }
   
   public String getCopiaoculta() {
     return this.copiaoculta;
   }
  
   public void setCopiaoculta(String copiaoculta) {
     this.copiaoculta = copiaoculta;
  }
   
   public String getCorreoEnvia() {
     return this.correoEnvia;
   }
   
   public void setCorreoEnvia(String correoEnvia) {
     this.correoEnvia = correoEnvia;
   }
   
   public String getContrasena() {
    return this.contrasena;
   }
   
   public void setContrasena(String contrasena) {
     this.contrasena = contrasena;
   }
  
   public String getDestinatario() {
     return this.destinatario;
   }
  
   public void setDestinatario(String destinatario) {
     this.destinatario = destinatario;
   }
   
   public void enviar_correo_aprobado(String Titular,int idCliente,String noMembresia,String fechaPago, String horaPago,
		   String monto,String noAutorizacion,String oid) {
    
    Properties propiedad = new Properties();
     propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
     propiedad.setProperty("mail.smtp.starttls.enable", "true");
     propiedad.setProperty("mail.smtp.port", "587");
     propiedad.setProperty("mail.smtp.auth", "true");
     
     Session sesion = Session.getDefaultInstance(propiedad);
     String asunto = "Notificacion de pago alpha";
     Html html=new Html();
     html.setHtmlAprobado(Titular, idCliente, noMembresia, fechaPago, horaPago, monto, noAutorizacion, oid);
     String mensaje = html.getHtmlAprobado();
     MimeMessage mail = new MimeMessage(sesion);
     
    try {
      mail.setFrom((Address)new InternetAddress(this.correoEnvia));
       mail.addRecipients(Message.RecipientType.BCC, (Address[])new InternetAddress[] { new InternetAddress(this.destinatario), new InternetAddress(this.copiaoculta) });
       mail.setSubject(asunto);
       mail.setContent(mensaje, "text/html");
       
       Transport transporte = sesion.getTransport("smtp");
       transporte.connect(this.correoEnvia, this.contrasena);
       transporte.sendMessage((Message)mail, mail.getRecipients(Message.RecipientType.BCC));
       transporte.close();
     } catch (AddressException ex) {
       Logger.getLogger(com.webHook.Correo.Correo.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
     } catch (MessagingException ex) {
       Logger.getLogger(com.webHook.Correo.Correo.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
     } 
   }
   public void enviar_correo_domiciliado(String Titular,int idCliente,String noMembresia,String fechaPago, String horaPago,
		   String monto,String noAutorizacion,String oid) {
    
    Properties propiedad = new Properties();
     propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
     propiedad.setProperty("mail.smtp.starttls.enable", "true");
     propiedad.setProperty("mail.smtp.port", "587");
     propiedad.setProperty("mail.smtp.auth", "true");
     
     Session sesion = Session.getDefaultInstance(propiedad);
     String asunto = "Notificacion de pago alpha";
     Html html=new Html();
     html.setHtmlDomiciliado(Titular, idCliente, noMembresia, fechaPago, horaPago, monto, noAutorizacion, oid);
     String mensaje = html.getHtmlDomiciliado();
     MimeMessage mail = new MimeMessage(sesion);
     
    try {
      mail.setFrom((Address)new InternetAddress(this.correoEnvia));
       mail.addRecipients(Message.RecipientType.BCC, (Address[])new InternetAddress[] { new InternetAddress(this.destinatario), new InternetAddress(this.copiaoculta) });
       mail.setSubject(asunto);
       mail.setContent(mensaje, "text/html");
       
       Transport transporte = sesion.getTransport("smtp");
       transporte.connect(this.correoEnvia, this.contrasena);
       transporte.sendMessage((Message)mail, mail.getRecipients(Message.RecipientType.BCC));
       transporte.close();
     } catch (AddressException ex) {
       Logger.getLogger(com.webHook.Correo.Correo.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
     } catch (MessagingException ex) {
       Logger.getLogger(com.webHook.Correo.Correo.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
     } 
   }
   public void enviar_correo_no_aprobado(String titular,String codigoError,String oid) {
    
    Properties propiedad = new Properties();
     propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
     propiedad.setProperty("mail.smtp.starttls.enable", "true");
     propiedad.setProperty("mail.smtp.port", "587");
     propiedad.setProperty("mail.smtp.auth", "true");
     
     Session sesion = Session.getDefaultInstance(propiedad);
     String asunto = "Notificacion de pago alpha";
     Html html=new Html();
     html.setHtmlRechazado(titular, codigoError, oid);
     String mensaje = html.getHtmlRechazado();
     MimeMessage mail = new MimeMessage(sesion);
     
    try {
      mail.setFrom((Address)new InternetAddress(this.correoEnvia));
       mail.addRecipients(Message.RecipientType.BCC, (Address[])new InternetAddress[] { new InternetAddress(this.destinatario), new InternetAddress(this.copiaoculta) });
       mail.setSubject(asunto);
       mail.setContent(mensaje, "text/html");
       
       Transport transporte = sesion.getTransport("smtp");
       transporte.connect(this.correoEnvia, this.contrasena);
       transporte.sendMessage((Message)mail, mail.getRecipients(Message.RecipientType.BCC));
       transporte.close();
     } catch (AddressException ex) {
       Logger.getLogger(com.webHook.Correo.Correo.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
     } catch (MessagingException ex) {
       Logger.getLogger(com.webHook.Correo.Correo.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
     } 
   }
 }