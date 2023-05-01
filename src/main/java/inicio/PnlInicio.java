/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package inicio;

import com.google.cloud.firestore.Firestore;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utils.UtilsInicio;

/**
 *
 * @author Aaron
 */
public class PnlInicio extends javax.swing.JPanel {

    /**
     * Creates new form Inicio
     */
    private Firestore firestore;
    
    public PnlInicio(String[] nombres, Firestore firestore) {
        initComponents();
        this.firestore = firestore;
        ganaderia.setText("Ganaderia: " + nombres[0]);
        ganadero.setText("Bienvenido " + nombres[1] + "!");
        
        Thread h1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        updateStats();
                        updateUI();
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PnlInicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        
        h1.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        ganaderia = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ganadero = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nBovinos = new javax.swing.JLabel();
        beneficios = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        nPorcino = new javax.swing.JLabel();
        gastos = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        nAves = new javax.swing.JLabel();
        ingresos = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        piensoKg = new javax.swing.JLabel();
        empleados = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        bovin = new javax.swing.JLabel();
        porcin = new javax.swing.JLabel();
        aves = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setLayout(new java.awt.BorderLayout());

        ganaderia.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        ganaderia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ganaderia.setText("Ganaderia: No introducida");
        add(ganaderia, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(6, 1));

        ganadero.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        ganadero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ganadero.setText("Bienvenido ganadero!");
        ganadero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(ganadero);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        nBovinos.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        nBovinos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nBovinos.setText("Nº de bovinos: 0");
        nBovinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(nBovinos);

        beneficios.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        beneficios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beneficios.setText("Beneficios: 0");
        beneficios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(beneficios);

        jPanel2.add(jPanel1);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        nPorcino.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        nPorcino.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nPorcino.setText("Nº de porcinos: 0");
        nPorcino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(nPorcino);

        gastos.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        gastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gastos.setText("Gastos: 0");
        gastos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(gastos);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        nAves.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        nAves.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nAves.setText("Nº de aves: 0");
        nAves.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(nAves);

        ingresos.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        ingresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ingresos.setText("Ingresos: 0");
        ingresos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(ingresos);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        piensoKg.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        piensoKg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        piensoKg.setText("Consumo de pienso: 0kg");
        piensoKg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.add(piensoKg);

        empleados.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        empleados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empleados.setText("Empleados: 0");
        empleados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.add(empleados);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.GridLayout(1, 3));

        bovin.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        bovin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bovin.setIcon(new ImageIcon(getClass().getResource("/iconos/vacaXL.png")));
        bovin.setToolTipText("Nº bovinos:");
        jPanel6.add(bovin);

        porcin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        porcin.setIcon(new ImageIcon(getClass().getResource("/iconos/cerdo.png")));
        porcin.setToolTipText("Nº de cerdos:");
        jPanel6.add(porcin);

        aves.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aves.setIcon(new ImageIcon(getClass().getResource("/iconos/gallina.png")));
        aves.setToolTipText("Nº de aves:");
        jPanel6.add(aves);

        jPanel2.add(jPanel6);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    public void updateStats(){
        UtilsInicio util = new UtilsInicio(this.firestore);
                
        int numBovino = util.getNumberBovinos();
        int numPorcino = util.getNumberPorcinos();
        int numAves = util.getNumberAves();
        
        nBovinos.setText("Nº de bovinos: " + numBovino);
        nPorcino.setText("Nº de porcinos: " + numPorcino);
        nAves.setText("Nº de aves: " + numAves);
        bovin.setToolTipText("Nº de bovinos: " + numBovino);
        porcin.setToolTipText("Nº de porcinos: " + numPorcino);
        aves.setToolTipText("Nº de aves: " + numAves);
        ingresos.setText("Ingresos: " + util.getIngresos() + "€");
        gastos.setText("Gastos: " + util.getGastos() + "€");
        piensoKg.setText("Consumo de pienso: " + util.getKgPienso() + "KG");
        empleados.setText("Empleados: " + util.getEmpleados());
        
        int beneficio = util.getBeneficios();
        
        if(beneficio > 0){
            beneficios.setText("Beneficios: " + beneficio + "€");
            beneficios.setForeground(Color.GREEN);
        }else if(beneficio < 0){
            beneficios.setText("Beneficios: " + beneficio + "€");
            beneficios.setForeground(Color.RED);
        }else{
            beneficios.setText("Beneficios: " + beneficio + "€");
            beneficios.setForeground(Color.BLACK);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aves;
    private javax.swing.JLabel beneficios;
    private javax.swing.JLabel bovin;
    private javax.swing.JLabel empleados;
    private javax.swing.JLabel ganaderia;
    private javax.swing.JLabel ganadero;
    private javax.swing.JLabel gastos;
    private javax.swing.JLabel ingresos;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel nAves;
    private javax.swing.JLabel nBovinos;
    private javax.swing.JLabel nPorcino;
    private javax.swing.JLabel piensoKg;
    private javax.swing.JLabel porcin;
    // End of variables declaration//GEN-END:variables
}
