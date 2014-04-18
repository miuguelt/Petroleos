package Herramientas;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Graficador extends ApplicationFrame {

    private JFreeChart diagrama;
    ChartPanel chartPanel;

    public Graficador(ArrayList<Double> x, ArrayList<Double> y, String titulo, int[] rango) {

        super("Graficador");         
        XYDataset paresDeDatos = PasarASerie(x, y);
        diagrama = crearDiagrama(paresDeDatos, titulo, rango);        
        chartPanel = new ChartPanel(diagrama);
        chartPanel.setPreferredSize(new Dimension(640, 440));    
    }

    public Graficador(String title) {
        super(title);
    }

    private XYDataset PasarASerie(ArrayList<Double> x, ArrayList<Double> y) {

//le pasamos una funcion generadora f(x)

        XYSeries datos = new XYSeries("Gráfica");
        int n = x.size();
        for (int i = 0; i < n; i++) {
            datos.add(x.get(i), y.get(i));
        }
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        conjuntoDatos.addSeries(datos);
        return conjuntoDatos;
    }

    private JFreeChart crearDiagrama(XYDataset conjuntoDatos, String titulo, int[] rango) {

        JFreeChart diag = ChartFactory.createXYLineChart(
                titulo, //Titulo Grafica
                "Ángulo [º]", // Leyenda eje X
                "", // Leyenda eje Y

                conjuntoDatos, // Los datos

                PlotOrientation.VERTICAL, //orientacion

                true, // ver titulo de linea
                true, //tooltips
                false //URL
                );

        diag.setBackgroundPaint(Color.LIGHT_GRAY);//Pintar el fondo
        diag.getPlot().setBackgroundPaint(Color.WHITE);
        diag.getPlot().setOutlinePaint(Color.GRAY);
        XYPlot plot = (XYPlot) diag.getPlot();
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        
        //range.setTickUnit(new NumberTickUnit(1.0)); //De uno en uno
        range.setRange(rango[0]-5, rango[1]+5);
        XYLineAndShapeRenderer render = (XYLineAndShapeRenderer)plot.getRenderer();
        render.setSeriesShapesVisible(0, true);        
        render.setItemLabelAnchorOffset(2);
        render.setItemLabelGenerator(new StandardXYItemLabelGenerator());
        render.setItemLabelsVisible(true);
        return diag;        
    }
    
    public ChartPanel getChartPanel() {
        return chartPanel;
    } 
}