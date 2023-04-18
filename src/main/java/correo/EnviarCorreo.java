/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package correo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

/**
 *
 * @author Aaron
 */
public class EnviarCorreo {
    
    private String emailFrom = "aapecargame@gmail.com";
    private String passwordFrom = "foxijoltgjipzttn";
    private String emailTo;
    private String subject;
    private String content;
    
    private Properties properties;
    private Session mSession;
    private MimeMessage mCorreo;
    private String puerto;
    private String servidorSmtp;
    
    public EnviarCorreo(String emailTo, String subject, String content, String puerto, String servidorSmtp, String emailFrom, String password) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.content = content;
        this.puerto = puerto;
        this.servidorSmtp = servidorSmtp;
        this.emailFrom = emailFrom;
        this.passwordFrom = password;
        properties = new Properties();
    }

    public EnviarCorreo() {
    }
    
    //smtp.gmail.com servidor de google puerto 587 smtp-relay.gmail.com mas seguro
    public void crearEmail(){
        properties.put("mail.smtp.host", servidorSmtp);
        properties.put("mail.smtp.ssl.trust", servidorSmtp);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", puerto);
        properties.put("mail.smtp.port", emailFrom);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(properties);
        
        
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");
            
            
            
        } catch (AddressException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearEmailConFichero(File archivo){
        properties.put("mail.smtp.host", servidorSmtp);
        properties.put("mail.smtp.ssl.trust", servidorSmtp);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", puerto);
        properties.put("mail.smtp.port", emailFrom);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(properties);
        
        try {
            BodyPart texto = new MimeBodyPart();
                texto.setContent(content, "text/html");
            BodyPart adjunto = null;
            if(archivo != null){
                adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo.getPath())));
                    adjunto.setFileName(archivo.getName());
            }
            MimeMultipart mimePart = new MimeMultipart();
                mimePart.addBodyPart(texto);
                if(adjunto != null){
                    mimePart.addBodyPart(adjunto);
                }
                
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(mimePart);
            
        } catch (AddressException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarEmail(){
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Email> leerCorreo() throws NoSuchProviderException, MessagingException, IOException {
        
        ArrayList<Email> emails = new ArrayList();
        Properties prop = new Properties();
        prop.put("mail.store.protocol", "imaps");
        
        Store store = Session.getInstance(prop).getStore();
        store.connect("imap.gmail.com", emailFrom, passwordFrom);
        
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date fechaHaceUnaSemana = calendar.getTime();
        
        SearchTerm searchTerm = new ReceivedDateTerm(ComparisonTerm.GE, fechaHaceUnaSemana);
        Message[] mensajes = inbox.search(searchTerm);
        
        for(int i = mensajes.length-1; i >= 0; i--){
            Object messageContent = mensajes[i].getContent();
            Email mail = new Email();
            mail.setRemitentePrincipal(mensajes[i].getFrom()[0].toString());
            mail.setRemitentes(mensajes[i].getFrom());
            mail.setFechaRecibido(mensajes[i].getReceivedDate().toString());
            mail.setAsunto(mensajes[i].getSubject());
            mail.setCuerpo(mensajes[i].getContent().toString());
            emails.add(mail);
        }
        
        inbox.close();
        store.close();
        return emails;
    }
}
