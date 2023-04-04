/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Aaron
 */
public class UtilsAnimales {
    
    private Firestore firestore;
    
    public UtilsAnimales(){
    }
    
    public UtilsAnimales(Firestore firestore) {
        this.firestore = firestore;
    }
    
    public void introducirBovino(String nIdent, String fechaNac, String sexo, String raza, String momRepro, boolean vendido){
        Map<String, Object> datos = new HashMap<>();
        datos.put("Numero identificacion", nIdent);
        datos.put("Fecha nacimiento", fechaNac);
        datos.put("Sexo", sexo);
        datos.put("Raza", raza);
        datos.put("Momento reproductivo", momRepro);
        datos.put("Vendido", vendido);
        firestore.collection("animales").document("bovino").collection("bovinos").add(datos);
    }
    
    public void introducirPorcino(String nIdent, String fechaNac, String sexo, String raza, boolean vendido){
        Map<String, Object> datos = new HashMap<>();
        datos.put("Numero identificacion", nIdent);
        datos.put("Fecha nacimiento", fechaNac);
        datos.put("Sexo", sexo);
        datos.put("Raza", raza);
        datos.put("Vendido", vendido);
        firestore.collection("animales").document("porcino").collection("porcinos").add(datos);
    }
    
    public void introducirAves(int nLote, int cant, String fecha, boolean vendido){
        Map<String, Object> datos = new HashMap<>();
        datos.put("nLote", nLote);
        datos.put("cantidad", cant);
        datos.put("fecha", fecha);
        datos.put("vendido", vendido);
        firestore.collection("animales").document("ave").collection("aves").add(datos);
    }
    
    public boolean comprobarFecha(String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(fecha);
            return true;
        } catch (java.text.ParseException ex) {
            return false;
        }
    }
    
    public boolean comprobarFormato(String texto) {
        String expresionRegular = "^ES\\d{12}$";
        Pattern patron = Pattern.compile(expresionRegular);
        return patron.matcher(texto).matches();
    }
    
    public List<QueryDocumentSnapshot> getBovinos(){
        List<QueryDocumentSnapshot> documentosBovinos = null;
        try {
            CollectionReference animalesRef = firestore.collection("animales");
            Query bovinosQuery = animalesRef.document("bovino").collection("bovinos");
            ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentosBovinos = queryResult.getDocuments();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentosBovinos;
    }
    
    public List<QueryDocumentSnapshot> getPorcinos(){
        List<QueryDocumentSnapshot> documentosPorcinos = null;
        try {
            CollectionReference animalesRef = firestore.collection("animales");
            Query bovinosQuery = animalesRef.document("porcino").collection("porcinos");
            ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentosPorcinos = queryResult.getDocuments();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentosPorcinos;
    }
    
    public List<QueryDocumentSnapshot> getAves(){
        List<QueryDocumentSnapshot> documentosAves = null;
        try {
            CollectionReference animalesRef = firestore.collection("animales");
            Query bovinosQuery = animalesRef.document("ave").collection("aves");
            ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentosAves = queryResult.getDocuments();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentosAves;
    }
    
    public String getSHA256(String input){

	String toReturn = null;
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    digest.reset();
	    digest.update(input.getBytes("utf8"));
	    toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return toReturn;
    }
    
    public String[] getAdmin(){
        String[] credenciales = new String[2];
        try {
            DocumentReference administradorRef = firestore.collection("empresa").document("administrador");
            ApiFuture<DocumentSnapshot> administrador = administradorRef.get();
            String nombre = administrador.get().getString("ganadero");
            credenciales[0] = nombre;
            String contra = administrador.get().getString("contraseña");
            credenciales[1] = contra;
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return credenciales;
    }
    
}
