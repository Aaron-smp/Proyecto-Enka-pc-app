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
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class UtilsInicio {
    
    private Firestore firestore;
    private int ingresos;
    private int gastos;
    
    public UtilsInicio(){
    }
    
    public UtilsInicio(Firestore firestore) {
        this.firestore = firestore;
    }
    
    public int getNumberBovinos(){
        int numeroBovinos = 0;
        try {
            CollectionReference animalesRef = firestore.collection("animales");
            Query bovinosQuery = animalesRef.document("bovino").collection("bovinos");
            ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            numeroBovinos = queryResult.size();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroBovinos;
    }
    
    public int getNumberPorcinos(){
        int numeroPorcinos = 0;
        try {
            CollectionReference animalesRef = firestore.collection("animales");
            Query bovinosQuery = animalesRef.document("porcino").collection("porcinos");
            ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            numeroPorcinos = queryResult.size();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroPorcinos;
    }
    
    public int getNumberAves(){
        List<QueryDocumentSnapshot> documentosAves = null;
        int numeroAves = 0;
        try {
            CollectionReference animalesRef = firestore.collection("animales");
            Query bovinosQuery = animalesRef.document("ave").collection("aves");
            ApiFuture<QuerySnapshot> querySnapshot = bovinosQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentosAves = queryResult.getDocuments();
            for (QueryDocumentSnapshot documento : documentosAves) {
                Long cant = documento.getLong("cantidad");
                numeroAves += cant;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroAves;
    }
    
    public int getBeneficios(){
        int beneficios = ingresos - gastos;
        return beneficios;
    }
    
    public int getKgPienso(){
        UtilsVentas util = new UtilsVentas(firestore);
        List<QueryDocumentSnapshot> kilos = util.getGastos();
        int kilosPienso = 0;
        for(QueryDocumentSnapshot document : kilos){
            if(document.getString("categoria").equals("Pienso")){
                kilosPienso += document.getLong("kilos");
            }
        }
        return kilosPienso;
    }
    
    public int getGastos(){
        UtilsVentas util = new UtilsVentas(firestore);
        List<QueryDocumentSnapshot> gastos = util.getGastos();
        int totalGastos = 0;
        for(QueryDocumentSnapshot document : gastos){
            totalGastos += document.getLong("importe");
        }
        this.gastos = totalGastos;
        return totalGastos;
    }
    
    public int getIngresos(){
        UtilsVentas util = new UtilsVentas(firestore);
        List<QueryDocumentSnapshot> ventas = util.getVentas();
        int totalIngresos = 0;
        for(QueryDocumentSnapshot document : ventas){
            totalIngresos += document.getLong("total");
        }
        this.ingresos = totalIngresos;
        return totalIngresos;
    }
    
    public int getEmpleados(){
        UtilsVentas util = new UtilsVentas(firestore);
        List<QueryDocumentSnapshot> gastos = util.getGastos();
        int totalEmpleados = 0;
        for(QueryDocumentSnapshot document : gastos){
            if(document.getString("categoria").equals("Nomina")){
                totalEmpleados += 1;
            }
        }
        return totalEmpleados;
    }
}

