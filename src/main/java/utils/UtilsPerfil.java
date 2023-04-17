/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import animales.EditPanelBovino;
import animales.PnlAnimales;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Aaron
 */
public class UtilsPerfil {
    
    private Firestore firestore;
    
    public UtilsPerfil(){
    }
    
    public UtilsPerfil(Firestore firestore) {
        this.firestore = firestore;
    }
    
    public void introducirUsuario(String email, String contra, String uid){
        Map<String, Object> datos = new HashMap<>();
        datos.put("email", email);
        datos.put("contraseña", contra);
        datos.put("uid", uid);
        firestore.collection("usuarios").add(datos);
    }
    
    public List<QueryDocumentSnapshot> getUsuarios(){
        List<QueryDocumentSnapshot> documentosPerfiles = null;
        try {
            CollectionReference usuariosRef = firestore.collection("usuarios");
            Query usersQuery = usuariosRef;
            ApiFuture<QuerySnapshot> querySnapshot = usersQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            documentosPerfiles = queryResult.getDocuments();
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentosPerfiles;
    }
    
    public void borrarUsuario(String uid){
        try {
            Firestore db = FirestoreClient.getFirestore();
            QuerySnapshot query = db.collection("usuarios").whereEqualTo("uid", uid).get().get();
            if (!query.isEmpty()) {
                QueryDocumentSnapshot doc = query.getDocuments().get(0);
                DocumentReference docRef = doc.getReference();
                docRef.delete().get();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsAnimales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateContrasenaAdmin(String nuevaPass){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("empresa").document("administrador");
        HashMap<String, Object> map = new HashMap();
        map.put("contraseña", getSHA256(nuevaPass));
        docRef.update(map);
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
    
    public String[] getCredencialesAdmin(){
        String[] credenciales = new String[4];
        try {
            CollectionReference usuariosRef = firestore.collection("empresa");
            Query usersQuery = usuariosRef;
            ApiFuture<QuerySnapshot> querySnapshot = usersQuery.get();
            QuerySnapshot queryResult = querySnapshot.get();
            String email = queryResult.getDocuments().get(0).getString("email");
            String ganadero = queryResult.getDocuments().get(0).getString("ganadero");
            String explotacion = queryResult.getDocuments().get(0).getString("explotacion");
            String passwdMail = null;
            passwdMail = queryResult.getDocuments().get(0).getString("contraseña mail");
            credenciales[0] = email;
            credenciales[1] = ganadero;
            credenciales[2] = explotacion;
            if(passwdMail != null && !passwdMail.isEmpty()){
                credenciales[3] = passwdMail;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(UtilsPerfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UtilsPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return credenciales;
    }
    
    public void updateEmailAdmin(String nuevoEmail){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("empresa").document("administrador");
        HashMap<String, Object> map = new HashMap();
        map.put("email", nuevoEmail);
        docRef.update(map);
    }
    
    public void updateExplotacionAdmin(String nuevaExplotacion){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("empresa").document("administrador");
        HashMap<String, Object> map = new HashMap();
        map.put("explotacion", nuevaExplotacion);
        docRef.update(map);
    }
    
    public void updateGanaderoAdmin(String nuevoGanadero){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("empresa").document("administrador");
        HashMap<String, Object> map = new HashMap();
        map.put("ganadero", nuevoGanadero);
        docRef.update(map);
    }
    
    public void updatePassMailAdmin(String nuevaContra){
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("empresa").document("administrador");
        HashMap<String, Object> map = new HashMap();
        map.put("contraseña mail", nuevaContra);
        docRef.update(map);
    }
    
    public boolean validarCorreo(String correo) {
        String patronCorreo = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(patronCorreo);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

}
