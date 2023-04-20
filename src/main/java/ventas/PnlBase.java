/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ventas;

import com.google.cloud.firestore.Firestore;
import java.awt.CardLayout;

/**
 *
 * @author Aaron
 */
public class PnlBase extends javax.swing.JPanel {

    /**
     * Creates new form PnlBase
     */
    private Firestore firestore;
    
    public PnlBase(Firestore firestore) {
        initComponents();
        this.firestore = firestore;
        PnlVentas ventas = new PnlVentas(this.firestore);
        PnlGastos gastos = new PnlGastos();
        ventas.actualizarVentas();
        base.add(ventas, "ventas");
        base.add(gastos, "gastos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topbar = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        cambiarPnl = new javax.swing.JButton();
        base = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        topbar.setLayout(new java.awt.BorderLayout());

        titulo.setFont(new java.awt.Font("Poppins", 0, 30)); // NOI18N
        titulo.setText("Ventas");
        topbar.add(titulo, java.awt.BorderLayout.CENTER);

        cambiarPnl.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        cambiarPnl.setText("Gastos");
        cambiarPnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarPnlActionPerformed(evt);
            }
        });
        topbar.add(cambiarPnl, java.awt.BorderLayout.LINE_END);

        add(topbar, java.awt.BorderLayout.PAGE_START);

        base.setLayout(new java.awt.CardLayout());
        add(base, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cambiarPnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarPnlActionPerformed
        if(cambiarPnl.getText().equals("Gastos")){
            titulo.setText("Gastos");
            cambiarPnl.setText("Ventas");
            CardLayout card = (CardLayout) base.getLayout();
            card.show(base, "gastos");
        }else{
            titulo.setText("Ventas");
            cambiarPnl.setText("Gastos");
            CardLayout card = (CardLayout) base.getLayout();
            card.show(base, "ventas");
        }
    }//GEN-LAST:event_cambiarPnlActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel base;
    private javax.swing.JButton cambiarPnl;
    private javax.swing.JLabel titulo;
    private javax.swing.JPanel topbar;
    // End of variables declaration//GEN-END:variables
}
