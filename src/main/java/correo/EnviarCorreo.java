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
import javax.mail.Flags;
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
import javax.mail.search.MessageNumberTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

/**
 *
 * @author Aaron
 */
public class EnviarCorreo {
    
    private String emailFrom;
    private String passwordFrom;
    private String emailTo;
    private String subject;
    private String content;
    private Message[] mensajes;
    
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
        this.mensajes = mensajes;
        
        for(int i = mensajes.length-1; i >= 0; i--){
            Object messageContent = mensajes[i].getContent();
            Email mail = new Email();
            mail.setRemitentePrincipal(mensajes[i].getFrom()[0].toString());
            mail.setRemitentes(mensajes[i].getFrom());
            mail.setFechaRecibido(mensajes[i].getReceivedDate().toString());
            mail.setAsunto(mensajes[i].getSubject());
            
            if (mensajes[i].isMimeType("multipart/*")) {
                MimeMultipart multipart = (MimeMultipart) mensajes[i].getContent();
                for (int e = 0; e < multipart.getCount(); e++) {
                    BodyPart bodyPart = multipart.getBodyPart(e);
                    if (bodyPart.isMimeType("text/html")) {
                        mail.setCuerpo(bodyPart.getContent().toString());
                    }
                }
            }else{
                mail.setCuerpo(mensajes[i].getContent().toString());
            }
            emails.add(mail);
        }
        
        inbox.close();
        store.close();
        return emails;
    }

    public Message[] getMensajes() {
        return mensajes;
    }
    
    public void eliminarCorreo(int correo){
        try {
            Properties prop = new Properties();
            prop.put("mail.store.protocol", "imaps");
            
            Store store = Session.getInstance(prop).getStore();
            store.connect("imap.gmail.com", emailFrom, passwordFrom);
            
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            
            int totalMessages = inbox.getMessageCount();
            Message[] messages = inbox.search(new MessageNumberTerm(totalMessages-correo));

            messages[0].setFlag(Flags.Flag.DELETED, true);
            
            inbox.close();
            store.close();
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Email> correosUltimaHora(ArrayList<Email> emailsEntrantes) throws MessagingException, IOException{
        ArrayList<Email> emails = new ArrayList();
        Properties prop = new Properties();
        prop.put("mail.store.protocol", "imaps");
        
        Store store = Session.getInstance(prop).getStore();
        store.connect("imap.gmail.com", emailFrom, passwordFrom);
        
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date fechaHaceUnaHora = calendar.getTime();
        
        SearchTerm searchTerm = new ReceivedDateTerm(ComparisonTerm.GE, fechaHaceUnaHora);
        Message[] mensajes = inbox.search(searchTerm);
        this.mensajes = mensajes;
        
        for(int i = mensajes.length-1; i >= 0; i--){
            Object messageContent = mensajes[i].getContent();
            Email mail = new Email();
            mail.setRemitentePrincipal(mensajes[i].getFrom()[0].toString());
            mail.setRemitentes(mensajes[i].getFrom());
            mail.setFechaRecibido(mensajes[i].getReceivedDate().toString());
            mail.setAsunto(mensajes[i].getSubject());
            
            if (mensajes[i].isMimeType("multipart/*")) {
                MimeMultipart multipart = (MimeMultipart) mensajes[i].getContent();
                for (int e = 0; e < multipart.getCount(); e++) {
                    BodyPart bodyPart = multipart.getBodyPart(e);
                    if (bodyPart.isMimeType("text/html")) {
                        mail.setCuerpo(bodyPart.getContent().toString());
                    }
                }
            }else{
                mail.setCuerpo(mensajes[i].getContent().toString());
            }
            emails.add(mail);
        }
        
        for(int e = 0; e < emailsEntrantes.size(); e++){
            for(int i = emails.size()-1; i >= 0; i--){
                if(emailsEntrantes.get(e).getAsunto().equals(emails.get(i).getAsunto())){
                    emails.remove(i);
                    break;
                }
            }
        }
        
        inbox.close();
        store.close();
        return emails;
    }
}
