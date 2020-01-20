/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot;
//import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Vivek
 */
public class Charts extends javax.swing.JPanel {

    /**
     * Creates new form ManageUsersJPanel
     */
    private Statement statement;
    private JPanel userProcessContainer;
    private Connection connection;

    public Charts(Statement statement, JPanel userProcessContainer, Connection connection) {
        initComponents();
       this.statement = statement;
         this.connection = connection;
         this.userProcessContainer = userProcessContainer;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        getOrgans = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        chartPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(55, 96, 128));

        jPanel3.setBackground(new java.awt.Color(23, 35, 51));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Charts");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getOrgans.setText("chart");
        getOrgans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getOrgansActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(55, 96, 128));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Get Piechart for all the Treatments");

        chartPanel.setBackground(new java.awt.Color(55, 96, 128));
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 500));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getOrgans, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(getOrgans)
                        .addGap(466, 466, 466))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void getOrgansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getOrgansActionPerformed
        // TODO add your handling code here:
        try {
        int countEndodontics = 0 ;
        
        String endodontics = "select  dbo.findTreatmentCount ('Endodontics')";

        ResultSet values_endodontics = statement.executeQuery(endodontics);

        while (values_endodontics.next()) {
            countEndodontics =  values_endodontics.getInt(1);

        }
        
        int countExtraction = 0 ;
        
        String extraction = "select  dbo.findTreatmentCount ('Extraction')";

        ResultSet values_extraction = statement.executeQuery(extraction);

        while (values_extraction.next()) {
            countExtraction =  values_extraction.getInt(1);

        }
        int countbruxism = 0 ;
        
        String bruxism = "select  dbo.findTreatmentCount ('Bruxism')";

        ResultSet values_bruxism = statement.executeQuery(bruxism);

        while (values_bruxism.next()) {
            countbruxism =  values_bruxism.getInt(1);

        }
        
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Endodontics", countEndodontics);
        pieDataset.setValue("Extraction",countExtraction );
        pieDataset.setValue("Bruxism", countbruxism);
        JFreeChart chart = ChartFactory.createPieChart("Pie chart", pieDataset, true, true, true);
        PiePlot P = (PiePlot) chart.getPlot();
        //P.setForegroundAlpha(TOP_ALIGNMENT);
        ChartPanel panel = new ChartPanel(chart);
        chartPanel.add(panel);
        panel.setVisible(true);
        panel.setSize(550, 600);
      } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_getOrgansActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JButton getOrgans;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
