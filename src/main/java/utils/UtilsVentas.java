/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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
    
    public void introducirVenta(String animal, String codigo, String fechaVenta, int iva, float precio, float total, int kilogramos){
        Map<String, Object> datos = new HashMap<>();
        datos.put("animal", animal);
        datos.put("fechaVenta", fechaVenta);
        datos.put("codigo", codigo);
        datos.put("iva", iva);
        datos.put("precio", precio);
        datos.put("total", total);
        datos.put("kilogramos", kilogramos);
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
        ArrayList<QueryDocumentSnapshot> documentos = new ArrayList<QueryDocumentSnapshot>();
        try {
            CollectionReference animalesRef = firestore.collection("ventas");
            Query ventasQuery = animalesRef.whereEqualTo("animal", "Bovino");
            ApiFuture<QuerySnapshot> querySnapshot = ventasQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentos.addAll(queryResult.getDocuments());
            
            ventasQuery = animalesRef.whereEqualTo("animal", "Porcino");
            querySnapshot = ventasQuery.get();
            queryResult = querySnapshot.get();
            documentos.addAll(queryResult.getDocuments());
            
            ventasQuery = animalesRef.whereEqualTo("animal", "Aves");
            querySnapshot = ventasQuery.get();
            queryResult = querySnapshot.get();
            documentos.addAll(queryResult.getDocuments());
            
            documentosVentas = documentos;
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentosVentas;
    }
    
    public void venderAnimal(String animal, String codigo){
        UtilsAnimales util = new UtilsAnimales(firestore);
        if(animal.equals("Bovino")){
            List<QueryDocumentSnapshot> documentosBovino = util.getBovinos();
            for (QueryDocumentSnapshot documento : documentosBovino) {
                String numIde = documento.getString("Numero identificacion");
                String idDocu = documento.getId();
                if(numIde.equals(codigo)){
                    DocumentReference docRef = this.firestore.collection("animales").document("bovino").collection("bovinos").document(idDocu);
                    ApiFuture<WriteResult> future = docRef.update("Vendido", true);
                    try {
                        future.get();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }else if(animal.equals("Porcino")){
            List<QueryDocumentSnapshot> documentosPorcino = util.getPorcinos();
            for (QueryDocumentSnapshot documento : documentosPorcino) {
                String numIde = documento.getString("Numero identificacion");
                String idDocu = documento.getId();
                System.out.println(idDocu);
                if(numIde.equals(codigo)){
                    DocumentReference docRef = this.firestore.collection("animales").document("porcino").collection("porcinos").document(idDocu);
                    ApiFuture<WriteResult> future = docRef.update("Vendido", true);
                    try {
                        future.get();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }else{
            List<QueryDocumentSnapshot> documentosAves = util.getAves();
            for (QueryDocumentSnapshot documento : documentosAves) {
                String numIde = Long.toString(documento.getLong("nLote"));
                String idDocu = documento.getId();
                System.out.println("Num ide: " + numIde);
                if(numIde.equals(codigo)){
                    DocumentReference docRef = this.firestore.collection("animales").document("ave").collection("aves").document(idDocu);
                    ApiFuture<WriteResult> future = docRef.update("vendido", true);
                    try {
                        future.get();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public void borrarVenta(String codigo){
        try {
            CollectionReference coleccion = firestore.collection("ventas");
            Query query = coleccion.whereEqualTo("codigo", codigo);
            
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                document.getReference().delete();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void introducirGasto(String categoria, float importe, String observaciones, float kilos){
        Map<String, Object> datos = new HashMap<>();
        datos.put("fecha", obtenerFechaHoraActual());
        datos.put("categoria", categoria);
        datos.put("importe", importe);
        datos.put("observaciones", observaciones);
        if(kilos != 0){
            datos.put("kilos", kilos);
        }
        firestore.collection("gastos").add(datos);
    }
    
    public ArrayList<QueryDocumentSnapshot> getGastos(){
        ArrayList<QueryDocumentSnapshot> documentos = new ArrayList<QueryDocumentSnapshot>();
        try {
            CollectionReference animalesRef = firestore.collection("gastos");
            Query ventasQuery = animalesRef.orderBy("fecha", Direction.DESCENDING);
            
            ApiFuture<QuerySnapshot> querySnapshot = ventasQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentos.addAll(queryResult.getDocuments());
            
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentos;
    }
    
    public void borrarGasto(String fecha){
        try {
            CollectionReference coleccion = firestore.collection("gastos");
            Query query = coleccion.whereEqualTo("fecha", fecha);
            
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                document.getReference().delete();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
