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
        return 0;
    }
    
    public int getKgPienso(){
        return 0;
    }
    
    public int getGastos(){
        return 0;
    }
    
    public int getIngresos(){
        return 0;
    }
    
    public int getEmpleados(){
        return 0;
    }
}

