/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.enka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author Aaron
 */
public class Info extends javax.swing.JPanel {

    /**
     * Creates new form Info
     */
    public Info() {
        initComponents();
        Properties propiedades = System.getProperties();
        String osName = propiedades.getProperty("os.name");
        String osVersion = propiedades.getProperty("os.version");
        String osArch = propiedades.getProperty("os.arch");
        
        System.out.println("Ubicación del JDK: " + getJavaVersion());
        info.setText("Product Version: Enka 1.1\nJava: " + getJavaVersion() 
                + "Sistema: " + osName + "; version: " + osVersion + "; arquitectura: " + osArch);
        
        System.out.println(osName + " " + osVersion + " " + osArch);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        info = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(300, 280));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enka");
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        info.setEditable(false);
        info.setColumns(20);
        info.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        info.setRows(5);
        info.setText("Product Version: Enka 1.1\nJava: 17.0.1;\nRuntime: Java(TM) SE Runtime Environment 17.0.1\nSystem: Windows 10;\n");
        jScrollPane2.setViewportView(info);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public String ejecutarComando(String command) {
        StringBuilder result = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            
            int exitCode = process.waitFor();
            result.append("El proceso ha terminado con el código de salida: ").append(exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    
    public String getJavaVersion() {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-version");
            processBuilder.redirectErrorStream(true); // Redirige stderr a stdout
            
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Error al obtener la versión de Java. Código de salida: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
