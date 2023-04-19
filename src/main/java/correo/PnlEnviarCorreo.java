/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package correo;

import com.google.cloud.firestore.Firestore;
import java.io.File;
import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import utils.UtilsCorreo;
import utils.UtilsPerfil;

/**
 *
 * @author Aaron
 */
public class PnlEnviarCorreo extends javax.swing.JPanel {

    /**
     * Creates new form PnlEnviarCorreo
     */
    private File archivoAEnviar;
    private Firestore firestore;
    public Email email;
    
    public PnlEnviarCorreo(Firestore firestore) {
        initComponents();
        this.firestore = firestore;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        destinatario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        asunto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        contenido = new javax.swing.JEditorPane();
        jPanel5 = new javax.swing.JPanel();
        enviar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        archivo = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(4, 1));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setText("Destinatario");
        jPanel4.add(jLabel3);

        destinatario.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jPanel4.add(destinatario);

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel1.setText("Asunto");
        jPanel4.add(jLabel1);

        asunto.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jPanel4.add(asunto);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Cuerpo");
        jPanel3.add(jLabel2, java.awt.BorderLayout.NORTH);

        contenido.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(contenido);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 38));

        enviar.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });
        jPanel5.add(enviar);

        jButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton2.setText("Limpiar cuerpo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jButton1.setText("Seleccionar archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        archivo.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPanel5.add(archivo);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel2);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser selector = new JFileChooser();
        int result = selector.showOpenDialog(null);
        archivoAEnviar = selector.getSelectedFile();
        long size = archivoAEnviar.length();
        double sizeKB = 0;
        String tamanio = "";
        if(size >= 1024 && size < 1000000){
            sizeKB = (double) size / 1024;
            sizeKB = Math.round(sizeKB * 100.0) / 100.0;
            tamanio = sizeKB + " KB";
        }else if(size >= 1000000 && size <= 1000000000){
            sizeKB = (double) size / 1048576;
            sizeKB = Math.round(sizeKB * 100.0) / 100.0;
            tamanio = sizeKB + " MB";
        }else if(size >= 1000000000){
            sizeKB = (double) size / 1073741824;
            sizeKB = Math.round(sizeKB * 100.0) / 100.0;
            tamanio = sizeKB + " GB";
        }else{
            sizeKB = size;
            tamanio = sizeKB + " bytes";
        }
        if (result == JFileChooser.APPROVE_OPTION) {
            MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
            Icon icon = javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(archivoAEnviar);
            archivo.setText(archivoAEnviar.getAbsolutePath() + " | Tamaño: " + tamanio);
            archivo.setIcon(icon);
        }
        
        System.out.println(archivoAEnviar.getName());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        Thread h1 = new Thread(new Runnable(){
            @Override
            public void run() {
                UtilsCorreo util = new UtilsCorreo(firestore);
                EnviarCorreo correo = new EnviarCorreo(destinatario.getText(), asunto.getText(),
                        contenido.getText(), util.getPuertoSmtp(),
                        util.getServidorSmtp(), util.getEmail(), util.getContraseña());
                correo.crearEmailConFichero(archivoAEnviar);
                correo.enviarEmail();
                JOptionPane.showMessageDialog(null, "Correo enviado", "Información", INFORMATION_MESSAGE);
            }
            
        });
        h1.start();
    }//GEN-LAST:event_enviarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        contenido.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel archivo;
    private javax.swing.JTextField asunto;
    private javax.swing.JEditorPane contenido;
    private javax.swing.JTextField destinatario;
    private javax.swing.JButton enviar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
