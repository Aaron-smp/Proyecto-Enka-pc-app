/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class UtilsVentas {
    
    private Firestore firestore;
    
    public UtilsVentas(){
    }
    
    public UtilsVentas(Firestore firestore) {
        this.firestore = firestore;
    }
    
    public ArrayList<String> getCodigos(String animal){
        ArrayList<String> listaCodigos = new ArrayList();
        if(animal.equals("Bovino")){
            List<QueryDocumentSnapshot> documentosBovinos = null;
            try {
                CollectionReference animalesRef = firestore.collection("animales");
                Query bovinosQuery = animalesRef.document("bovino").collection("bovinos");
                ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
                QuerySnapshot queryResult = querySnapshot.get();
                documentosBovinos = queryResult.getDocuments();
                for (QueryDocumentSnapshot documento : documentosBovinos) {
                    String codigo = documento.getString("Numero identificacion");
                    boolean vendido = documento.getBoolean("Vendido");
                    if(!vendido){
                        listaCodigos.add(codigo);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(animal.equals("Porcino")){
            List<QueryDocumentSnapshot> documentosPorcinos = null;
            try {
                CollectionReference animalesRef = firestore.collection("animales");
                Query bovinosQuery = animalesRef.document("porcino").collection("porcinos");
                ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
                QuerySnapshot queryResult = querySnapshot.get();
                documentosPorcinos = queryResult.getDocuments();
                for (QueryDocumentSnapshot documento : documentosPorcinos) {
                    String codigo = documento.getString("Numero identificacion");
                    boolean vendido = documento.getBoolean("Vendido");
                    if(!vendido){
                        listaCodigos.add(codigo);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            List<QueryDocumentSnapshot> documentosAves = null;
            try {
                CollectionReference animalesRef = firestore.collection("animales");
                Query bovinosQuery = animalesRef.document("ave").collection("aves");
                ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
                QuerySnapshot queryResult = querySnapshot.get();
                documentosAves = queryResult.getDocuments();
                for (QueryDocumentSnapshot documento : documentosAves) {
                    String codigo = Long.toString(documento.getLong("nLote"));
                    boolean vendido = documento.getBoolean("vendido");
                    if(!vendido){
                        listaCodigos.add(codigo);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaCodigos;
    }
    
    public void introducirVenta(String animal, String codigo, String fechaVenta, int iva, float precio, float total){
        Map<String, Object> datos = new HashMap<>();
        datos.put("animal", animal);
        datos.put("fechaVenta", fechaVenta);
        datos.put("codigo", codigo);
        datos.put("iva", iva);
        datos.put("precio", precio);
        datos.put("total", total);
        firestore.collection("ventas").add(datos);
    }
    
    public float getPrecioConIVA(float precio, int iva) {
        float precioConIva = precio * (1 + (iva / 100.0f));
        return precioConIva;
    }
    
    public String obtenerFechaHoraActual() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaHoraActual.format(formato);
    }
    
    public List<QueryDocumentSnapshot> getVentas(){
        List<QueryDocumentSnapshot> documentosVentas = null;
        try {
            CollectionReference animalesRef = firestore.collection("ventas");
            Query ventasQuery = animalesRef;
            ApiFuture<QuerySnapshot> querySnapshot = ventasQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentosVentas = queryResult.getDocuments();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentosVentas;
    }
    
    public void venderAnimal(String animal, String codigo){
        if(animal.equals("Bovino")){
            
        }else if(animal.equals("Porcino")){
            
        }else{
            
        }
    }
}
