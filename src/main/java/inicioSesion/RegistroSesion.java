/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package inicioSesion;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Aaron
 */
public class RegistroSesion extends javax.swing.JDialog {

    /**
     * Creates new form RegistroSesion
     */
    public boolean continuar;
    Firestore firestore;
    
    public RegistroSesion(java.awt.Frame parent, boolean modal, Firestore firestore) {
        super(parent, modal);
        initComponents();
        continuar = false;
        this.firestore = firestore;
        nombreGan.putClientProperty( "JComponent.roundRect", true );
        nombreExp.putClientProperty( "JComponent.roundRect", true );
        email.putClientProperty( "JComponent.roundRect", true );
        passwd.putClientProperty( "JComponent.roundRect", true );
        
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public Firestore getFirestore() {
        return firestore;
    }
    
    public boolean validarCorreoElectronico(String correo) {
        String expresionRegular = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expresionRegular, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nombreGan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombreExp = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        passwd = new javax.swing.JPasswordField();
        jPanel10 = new javax.swing.JPanel();
        register = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(780, 450));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(780, 450));
        setResizable(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido a ENKA");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nombre ganadero/administrador:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 40));

        nombreGan.setMinimumSize(new java.awt.Dimension(71, 22));
        nombreGan.setPreferredSize(new java.awt.Dimension(71, 22));
        jPanel5.add(nombreGan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 350, 30));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombre explotacion/empresa:");
        jLabel3.setPreferredSize(new java.awt.Dimension(28, 28));
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 280, 37));

        nombreExp.setMinimumSize(new java.awt.Dimension(71, 22));
        jPanel5.add(nombreExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 350, 30));

        email.setMinimumSize(new java.awt.Dimension(71, 22));
        jPanel5.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 350, 30));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setText("Email:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 311, 32));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setText("Contraseña:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 310, 30));
        jPanel5.add(passwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 350, 30));

        jPanel6.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        register.setBackground(new java.awt.Color(102, 102, 102));
        register.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        register.setForeground(new java.awt.Color(255, 255, 255));
        register.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        register.setText("Registrarse");
        register.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        register.setOpaque(true);
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jPanel6.add(jPanel10, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseClicked
        if(nombreGan.getText().length() != 0 && nombreExp.getText().length() != 0 && validarCorreoElectronico(email.getText()) && passwd.getPassword().length != 0){
            String nombre = nombreGan.getText();
            String explotacion = nombreExp.getText();
            String emailT = email.getText();
            char[] contraseña = passwd.getPassword();
            String passwdT = "";
            for(int i = 0; i < contraseña.length; i++){
                passwdT += i;
            }
            passwdT = getSHA256(passwdT);
            Map<String, Object> datos = new HashMap<>();
            datos.put("ganadero", nombre);
            datos.put("explotacion", explotacion);
            datos.put("email", emailT);
            datos.put("contraseña", passwdT);
            insertarDatos("empresa", "administrador", datos);
            continuar = true;
            this.dispose();
        }
        if(!validarCorreoElectronico(email.getText())){
            JOptionPane.showMessageDialog(null, "Correo electronico no valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_registerMouseClicked

    private void registerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseEntered
        register.setBackground(new Color( 138, 138, 138));
    }//GEN-LAST:event_registerMouseEntered

    private void registerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseExited
        register.setBackground(new Color( 102, 102, 102));
    }//GEN-LAST:event_registerMouseExited
    
    public static String getSHA256(String input){

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
    
    public void insertarDatos(String coleccion, String documento, Map<String, Object> dato){
        if(firestore != null){
            try {
                DocumentReference docRef = firestore.collection(coleccion).document(documento);
                ApiFuture<WriteResult> result = docRef.set(dato);
                System.out.println("" + result.get().getUpdateTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(RegistroSesion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(RegistroSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void crearConexion(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String filePath = "src/main/recursos/credenciales/firebaseConexion.json";
            InputStream is = new FileInputStream(filePath);
            //InputStream is = classLoader.getResourceAsStream("src/main/recursos/credenciales/firebaseConexion.json");
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(is);
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(googleCredentials)
                .build();
            
            FirebaseApp.initializeApp(options);
            firestore = FirestoreClient.getFirestore();
        } catch (IOException ex) {
            Logger.getLogger(RegistroSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField nombreExp;
    private javax.swing.JTextField nombreGan;
    private javax.swing.JPasswordField passwd;
    private javax.swing.JLabel register;
    // End of variables declaration//GEN-END:variables
}
