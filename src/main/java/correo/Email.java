/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package correo;

import javax.mail.Address;

/**
 *
 * @author Aaron
 */
public class Email {
    
    private String remitentePrincipal;
    private String asunto;
    private String cuerpo;
    private String fechaRecibido;
    private Address[] remitentes;
    
    public Email(String remitentePrincipal, String asunto, String cuerpo, String fechaRecibido, Address[] remitentes) {
        this.remitentePrincipal = remitentePrincipal;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaRecibido = fechaRecibido;
        this.remitentes = remitentes;
    }

    public Email() {
    }
    
    public String getRemitentePrincipal() {
        return remitentePrincipal;
    }

    public void setRemitentePrincipal(String remitente) {
        this.remitentePrincipal = remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Address[] getRemitentes() {
        return remitentes;
    }

    public void setRemitentes(Address[] remitentes) {
        this.remitentes = remitentes;
    }
    
    
    
}
