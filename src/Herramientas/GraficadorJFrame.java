/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import Central.Interface;
import Modelos.BeggsBrill;
import Modelos.Eaton;
import Modelos.LockhartMartinelli;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

/**
 *
 * @author Miguel
 */
public class GraficadorJFrame extends javax.swing.JFrame {

    /**
     * Creates new form GraficadorJFrame
     */
    public GraficadorJFrame() {
        initComponents();         
        setDefaultCloseOperation(Interface.HIDE_ON_CLOSE);
        setResizable(false);  
        //getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));      
    }
    
    
 //MI CODIGO
    public final JScrollPane jsp = new JScrollPane(null);
    
    Graficador g2; 
    private static ChartPanel panel;
    
    public void graficar(ArrayList<Double> x, ArrayList<Double> y, String titulo, int[] rango) {
        g2 = new Graficador(x, y, titulo,rango);
        g2.pack(); 
        
        JFreeChart chart = g2.getChartPanel().getChart();
        
        panel = new ChartPanel(chart);
        panel.addMouseListener(new java.awt.event.MouseAdapter() {           
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }       
            
        });
        jsp.setBounds(30, 30, 760, 480); // le doy tamaño
        jsp.setViewportView(panel); // le asigno el ChartPanel    
        this.setBounds((int)CENTER_ALIGNMENT,(int)CENTER_ALIGNMENT , 860, 580);        
        jPanelgrafica.add(jsp); // Esto es para agregar el JScrollPane con el CharPanel Adentro a mi JPanel           
    }        
    
    public void tablaBeggsBrill(Object[] modelo)
    {
        BeggsBrill beggsbrill;
        int cantidad=10;//Datos de salida
        jTableresultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [cantidad][3],
            new String [] { "Longitud ", "Caida de presión" }));
        for (int i = 0; i < 3; i++) {
           beggsbrill = (BeggsBrill) modelo[i];
           jTableresultados.setValueAt(beggsbrill.getLongitud(), i, 0); 
           jTableresultados.setValueAt(beggsbrill.getDp(), i, 1); 
        }        
    }
    
    public void tablaLockhartMartinelli(Object[] modelo)
    {
        LockhartMartinelli lockhartmartinelli;
        int cantidad=10;//Datos de salida
        jTableresultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [cantidad][3],
            new String [] { "Longitud ", "Caida de presión" }));
        for (int i = 0; i < 3; i++) {
            lockhartmartinelli = (LockhartMartinelli) modelo[i];
           jTableresultados.setValueAt(lockhartmartinelli.getL(), i, 0); 
           jTableresultados.setValueAt(lockhartmartinelli.getDPTL(), i, 1); 
        }        
    }
    
    public void tablaEaton(Object[] modelo)
    {
        Eaton eaton;
        int cantidad=10;//Datos de salida
        jTableresultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [cantidad][3],
            new String [] { "Longitud ", "Caida de presión" }));
        for (int i = 0; i < 3; i++) {
            eaton = (Eaton) modelo[i];
           jTableresultados.setValueAt("Agregar", i, 0); 
           jTableresultados.setValueAt("Agregar", i, 1); 
        }        
    }
        
    private void panelMouseClicked(java.awt.event.MouseEvent evt) {        
        Point2D p = panel.translateScreenToJava2D(evt.getPoint());
        JFreeChart g3 = panel.getChart();
        Rectangle2D plotArea = panel.getScreenDataArea();
        XYPlot plot = (XYPlot) g3.getPlot(); // your plot
        double chartX = plot.getDomainAxis().java2DToValue(p.getX(), plotArea, plot.getDomainAxisEdge());
        double chartY = plot.getRangeAxis().java2DToValue(p.getY(), plotArea, plot.getRangeAxisEdge());
        jLabelxy.setText("Posición: "+"("+chartX+"  ,  "+chartY+")");
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPaneltab1 = new javax.swing.JPanel();
        jPanelgrafica = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelxy = new javax.swing.JLabel();
        jPanelsalida = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableresultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jPaneltab1.setLayout(new java.awt.BorderLayout());

        jPanelgrafica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelgraficaMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelgraficaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelgraficaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelgraficaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelgraficaMousePressed(evt);
            }
        });
        jPanelgrafica.setLayout(new java.awt.GridLayout(1, 0));
        jPaneltab1.add(jPanelgrafica, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 16));

        jLabelxy.setText("Posición:");
        jLabelxy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelxy.setMinimumSize(new java.awt.Dimension(100, 140));
        jLabelxy.setName(""); // NOI18N
        jPanel1.add(jLabelxy);
        jLabelxy.getAccessibleContext().setAccessibleDescription("");

        jPaneltab1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Grafica", jPaneltab1);

        jTableresultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableresultados);

        jPanelsalida.add(jScrollPane1);

        jTabbedPane1.addTab("Datos de salida", jPanelsalida);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:    
     
    }//GEN-LAST:event_formComponentHidden

    private void jPanelgraficaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelgraficaMouseClicked

       
    }//GEN-LAST:event_jPanelgraficaMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formMousePressed

    private void jPanelgraficaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelgraficaMouseEntered
      
    }//GEN-LAST:event_jPanelgraficaMouseEntered

    private void jPanelgraficaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelgraficaMouseExited
       
    }//GEN-LAST:event_jPanelgraficaMouseExited

    private void jPanelgraficaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelgraficaMousePressed
    
    }//GEN-LAST:event_jPanelgraficaMousePressed

    private void jPanelgraficaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelgraficaMouseReleased
       
    }//GEN-LAST:event_jPanelgraficaMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraficadorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficadorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficadorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficadorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficadorJFrame().setVisible(true);  
                       
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel jLabelxy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelgrafica;
    private javax.swing.JPanel jPanelsalida;
    private javax.swing.JPanel jPaneltab1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableresultados;
    // End of variables declaration//GEN-END:variables
}
