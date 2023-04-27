/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ventas;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.awt.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import utils.UtilsVentas;

/**
 *
 * @author Aaron
 */
public class PnlVentas extends javax.swing.JPanel {

    /**
     * Creates new form PnlVentas
     */
    private final int IVA = 10;
    private Firestore firestore;
    
    public PnlVentas(Firestore firestore) {
        initComponents();
        this.firestore = firestore;
        Font headerFont = new Font("Segoe UI", Font.PLAIN, 18);
        JTableHeader header = tablaVentas.getTableHeader();
        header.setFont(headerFont);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaVentas.getColumnCount(); i++) {
            tablaVentas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        UtilsVentas util = new UtilsVentas(firestore);
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Bovino").toArray(new String[0]));
        codigos.setModel(modelo);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        vender = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        animal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        codigos = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        peso = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        precio = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        borrar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(929, 50));

        vender.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        vender.setText("Vender");
        vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venderActionPerformed(evt);
            }
        });
        jPanel4.add(vender);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 250));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Animal");
        jLabel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jLabel1.setPreferredSize(new java.awt.Dimension(70, 60));
        jPanel1.add(jLabel1);

        animal.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        animal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bovino", "Porcino", "Aves" }));
        animal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animalActionPerformed(evt);
            }
        });
        jPanel1.add(animal);

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Codigo/Lote");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 60));
        jPanel1.add(jLabel2);

        codigos.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        codigos.setPreferredSize(new java.awt.Dimension(220, 34));
        jPanel1.add(codigos);

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setText("Peso(Kg)");
        jPanel1.add(jLabel5);

        peso.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        peso.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        peso.setPreferredSize(new java.awt.Dimension(90, 34));
        jPanel1.add(peso);

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Precio(sin iva)");
        jLabel3.setPreferredSize(new java.awt.Dimension(135, 60));
        jPanel1.add(jLabel3);

        precio.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        precio.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        precio.setPreferredSize(new java.awt.Dimension(80, 34));
        jPanel1.add(precio);

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setText("€");
        jPanel1.add(jLabel4);

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(929, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        tablaVentas.setFont(new java.awt.Font("Poppins", 0, 17)); // NOI18N
        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo/Lote", "Animal", "Fecha de venta", "Kilos", "Precio €", "Iva %", "Total €"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        borrar.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        add(borrar, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void animalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animalActionPerformed
        UtilsVentas util = new UtilsVentas(this.firestore);
        if(animal.getSelectedItem().equals("Bovino")){
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Bovino").toArray(new String[0]));
            codigos.setModel(modelo);
        }else if(animal.getSelectedItem().equals("Porcino")){
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Porcino").toArray(new String[0]));
            codigos.setModel(modelo);
        }else{
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Aves").toArray(new String[0]));
            codigos.setModel(modelo);
        }
    }//GEN-LAST:event_animalActionPerformed

    private void venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venderActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Aceptar la venta?", "Confirmación", JOptionPane.YES_NO_OPTION);
        UtilsVentas util = new UtilsVentas(firestore);
        if (opcion == JOptionPane.YES_OPTION) {
            if((float)precio.getValue() > 0){
                System.out.println(codigos.getSelectedItem());
                if(animal.getSelectedItem().equals("Bovino")){
                    util.introducirVenta((String) animal.getSelectedItem(), (String) codigos.getSelectedItem(), util.obtenerFechaHoraActual(),
                            IVA, (float) precio.getValue(), util.getPrecioConIVA((float) precio.getValue(), IVA),(int) peso.getValue());
                    util.venderAnimal("Bovino", (String)codigos.getSelectedItem());
                    actualizarVentas();
                    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Bovino").toArray(new String[0]));
                    codigos.setModel(modelo);
                }else if(animal.getSelectedItem().equals("Porcino")){
                    util.introducirVenta((String) animal.getSelectedItem(), (String) codigos.getSelectedItem(), util.obtenerFechaHoraActual(),
                            IVA, (float) precio.getValue(), util.getPrecioConIVA((float) precio.getValue(), IVA),(int) peso.getValue());
                    util.venderAnimal("Porcino", (String)codigos.getSelectedItem());
                    actualizarVentas();
                    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Porcino").toArray(new String[0]));
                    codigos.setModel(modelo);
                }else{
                    util.introducirVenta((String) animal.getSelectedItem(), (String) codigos.getSelectedItem(), util.obtenerFechaHoraActual(),
                            IVA, (float) precio.getValue(), util.getPrecioConIVA((float) precio.getValue(), IVA),(int) peso.getValue());
                    util.venderAnimal("Aves", (String)codigos.getSelectedItem());
                    actualizarVentas();
                    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(util.getCodigos("Aves").toArray(new String[0]));
                    codigos.setModel(modelo);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se puede vender a 0€", "Informacion", 1);
            }
            
        }
    }//GEN-LAST:event_venderActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        UtilsVentas util = new UtilsVentas(firestore);
        if(tablaVentas.getSelectedRowCount() > 0 && tablaVentas.getSelectedRowCount() == 1){
            util.borrarVenta((String) tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 0));
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(PnlVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarVentas();
    }//GEN-LAST:event_borrarActionPerformed
    
    public void actualizarVentas(){
        UtilsVentas util = new UtilsVentas(firestore);
        List<QueryDocumentSnapshot> listaVentas = util.getVentas();
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaVentas.getModel();
        modeloTabla.setRowCount(0);
        for (QueryDocumentSnapshot documento : listaVentas) {
            String animal = documento.getString("animal");
            String codigo = documento.getString("codigo");
            String fechaVenta = documento.getString("fechaVenta");
            Long iva = documento.getLong("iva");
            Long precio = documento.getLong("precio");
            Long total = documento.getLong("total");
            Long kilos = documento.getLong("kilogramos");
            modeloTabla.addRow(new Object[] {codigo, animal, fechaVenta, kilos, precio, iva, total});
        }
        tablaVentas.setModel(modeloTabla);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> animal;
    private javax.swing.JButton borrar;
    private javax.swing.JComboBox<String> codigos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner peso;
    private javax.swing.JSpinner precio;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JButton vender;
    // End of variables declaration//GEN-END:variables
}
