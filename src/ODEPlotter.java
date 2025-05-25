import net.miginfocom.swing.MigLayout;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.IntegratorException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ODEPlotter {
    private XYSeriesCollection dataset;
    private double y0value;
    private double x0value;
    private final List<Double> timePoints;
    private final List<double[]> yPoints;
    private JButton refreshButton;
    private JButton plotButton;
    private int n = 1;
    private XYSeries    series0;
    private XYSeries series1;

    public ODEPlotter(List<Double> timePoints, List<double[]> yPoints) {
        this.timePoints = timePoints;
        this.yPoints = yPoints;
    }

    public void createChart() {
        //creating the dataset
        dataset = new XYSeriesCollection();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Ordinary Differential Equation Solution",
                "t",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        JFrame frame = new JFrame("Gráfico de Solução");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setVisible(true);

        JPanel southPanel = new JPanel(new MigLayout("center"));

        refreshButton = new JButton("<html>Refresh (x<sub>n</sub>, y<sub>n</sub>)</html>");
        plotButton = new JButton("<html>Add (x<sub>n</sub>, y<sub>n</sub>)</html>");
        JTextField textYInitialCondition = new JTextField("1", 6);
        JTextField textXInitialCondition = new JTextField("1", 6);

        y0value = Double.parseDouble(textYInitialCondition.getText());
        x0value = Double.parseDouble(textXInitialCondition.getText());

        ButtonsHandler buttonsHandler = new ButtonsHandler(textXInitialCondition, textYInitialCondition);

        plotButton.addActionListener(buttonsHandler);
        refreshButton.addActionListener(buttonsHandler);

        southPanel.add(new JLabel("y(0) = "), "alignx center", 0);
        southPanel.add(textYInitialCondition,"growy", 1);
        southPanel.add(plotButton, "wrap, growx", 2);
        southPanel.add(new JLabel("x(0) = "), "", 3);
        southPanel.add(textXInitialCondition, "growy", 4);
        southPanel.add(refreshButton, "", 5);
        southPanel.setBackground(Color.WHITE);

        frame.add(southPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    //creating and defining buttons' listener
    public class ButtonsHandler implements ActionListener {
        private JTextField textYInitialCondition;
        private JTextField textXInitialCondition;

        public ButtonsHandler(JTextField textXInitialCondition,JTextField textYInitialCondition) {
            this.textYInitialCondition = textYInitialCondition;
            this.textXInitialCondition = textXInitialCondition;
        }

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == plotButton) {
                y0value = Double.parseDouble(textYInitialCondition.getText());
                x0value = Double.parseDouble(textXInitialCondition.getText());

                ODESystem system = new ODESystem();
                ODESolver solver = new ODESolver(system, 0.01);

                double[] y = new double[]{x0value, y0value};

                try {
                    solver.solve(0.0, 10.0, y);
                } catch (IntegratorException | DerivativeException e) {
                    throw new RuntimeException(e);
                }

                List<Double> timePoints = solver.getTimePoints();
                List<double[]> yPoints = solver.getYPoints();

                series0 = new XYSeries("x" + n);
                series1 = new XYSeries("y" + n);
                n += 1;

                for (int i = 0; i < timePoints.size(); i++) {
                    double t = timePoints.get(i);
                    double x_t = yPoints.get(i)[0];
                    double y_t = yPoints.get(i)[1];

                    series0.add(t, x_t); // plot x(t)
                    series1.add(t, y_t); // plot y(t)
                }

                dataset.addSeries(series0);
                dataset.addSeries(series1);
            }

            if (event.getSource() == refreshButton) {
                dataset.removeAllSeries();
                n = 1;
            }
        }
    }
}
