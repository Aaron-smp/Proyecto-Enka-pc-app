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
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class UtilsCorreo {
    
    private Firestore firestore;

    public UtilsCorreo(Firestore firestore) {
        this.firestore = firestore;
    }
    
    public void updateServidorSmtp(String nuevoServidor){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("configuracion").document("ajustes");
        HashMap<String, Object> map = new HashMap();
        map.put("servidor", nuevoServidor);
        docRef.update(map);
    }
    
    public void updatePuertoSmtp(String nuevoPuerto){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("configuracion").document("ajustes");
        HashMap<String, Object> map = new HashMap();
        map.put("puerto", nuevoPuerto);
        docRef.update(map);
    }
    
    public String getServidorSmtp(){
        String servidor = "";
        try {
            CollectionReference usuariosRef = firestore.collection("configuracion");
            Query usersQuery = usuariosRef;
            ApiFuture<QuerySnapshot> querySnapshot = usersQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            servidor = queryResult.getDocuments().get(0).getString("servidor");
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servidor;
    }
    
    public String getPuertoSmtp(){
        String puerto = "";
        try {
            CollectionReference configRef = firestore.collection("configuracion");
            Query usersQuery = configRef;
            ApiFuture<QuerySnapshot> querySnapshot = usersQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            puerto = queryResult.getDocuments().get(0).getString("puerto");
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puerto;
    }
    
    public String getEmail(){
        String email = "";
        try {
            CollectionReference usuariosRef = firestore.collection("empresa");
            Query usersQuery = usuariosRef;
            ApiFuture<QuerySnapshot> querySnapshot = usersQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            email = queryResult.getDocuments().get(0).getString("email");
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsPerfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
    
    public String getContraseña(){
        String contrasenia = "";
        try {
            CollectionReference usuariosRef = firestore.collection("empresa");
            Query usersQuery = usuariosRef;
            ApiFuture<QuerySnapshot> querySnapshot = usersQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            contrasenia = queryResult.getDocuments().get(0).getString("contraseña mail");
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsPerfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrasenia;
    }
}
