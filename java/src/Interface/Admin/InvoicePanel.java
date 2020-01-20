/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

/**
 *
 * @author Vivek
 */
public class InvoicePanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    private Statement statement;
    private JPanel userProcessContainer;
    private Connection connection;
    private long pId;
    private String appointmentId;

    public InvoicePanel(Statement statement, JPanel userProcessContainer, Connection connection, String appointmentId) throws SQLException {
        initComponents();
        this.statement = statement;
        this.connection = connection;
        this.appointmentId = appointmentId;
        this.userProcessContainer = userProcessContainer;

        populateDefault();

    }
    
  

    private void populateDefault() throws SQLException {

        String values = "select * from dbo.getDentistPatient (" + appointmentId + ")";

        ResultSet values_result = statement.executeQuery(values);

        while (values_result.next()) {
            dentistName.setText(values_result.getString(1).trim());
            dentistContact.setText(values_result.getString(2).trim());
            patientName.setText(values_result.getString(3).trim());
            patientContact.setText(values_result.getString(4).trim());

        }
        
        //String insertInvoice = "insert into invoice (date,amount,appointment_id) values (GETDATE(),800," + appointmentId + ")";
        String invoiceId = "select invoice_id,amount,date from invoice where appointment_id = " + appointmentId + " ";
        ResultSet invoice_result = statement.executeQuery(invoiceId);
        while (invoice_result.next()) {
            invoiceIdText.setText(invoice_result.getString(1));
            totalAmountText.setText(invoice_result.getString(2));
            dateText.setText(invoice_result.getString(3));
        }
        String treatments = "select treatment_name , treatment_cost from treatment  where appointment_id = " + appointmentId + "";

        ResultSet treatments_result = statement.executeQuery(treatments);
        DefaultTableModel treatmentModel = (DefaultTableModel) treatmentTable.getModel();

        while (treatments_result.next()) {

            treatmentModel.addRow(new Object[]{treatments_result.getString(1), treatments_result.getString(2)});
        }

        String presciptions = "select medicine_name , morning,afternoon,night, duration from prescription where appointment_id = " + appointmentId + "";

        ResultSet presciptions_result = statement.executeQuery(presciptions);
        DefaultTableModel presciptionModel = (DefaultTableModel) presciptionTable.getModel();
        while (presciptions_result.next()) {
            presciptionModel.addRow(new Object[]{
                presciptions_result.getString(1),
                presciptions_result.getString(2),
                presciptions_result.getString(3),
                presciptions_result.getString(4),
                presciptions_result.getString(5)

            });

        }

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
        jPanel1 = new javax.swing.JPanel();
        nameSeparator5 = new javax.swing.JSeparator();
        dentistContact = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nameSeparator4 = new javax.swing.JSeparator();
        dentistName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        patientName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        patientContact = new javax.swing.JTextField();
        nameSeparator6 = new javax.swing.JSeparator();
        nameSeparator7 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        invoiceIdText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dateText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        presciptionTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        treatmentTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        totalAmountText = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(55, 96, 128));
        setPreferredSize(new java.awt.Dimension(1000, 575));

        jPanel2.setBackground(new java.awt.Color(55, 96, 128));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        nameSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        dentistContact.setEditable(false);
        dentistContact.setBorder(null);
        dentistContact.setCaretColor(new java.awt.Color(30, 59, 92));
        dentistContact.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        dentistContact.setOpaque(false);
        dentistContact.setSelectedTextColor(new java.awt.Color(55, 96, 128));
        dentistContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dentistContactFocusGained(evt);
            }
        });
        dentistContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dentistContactActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Dentist Contact");

        nameSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        dentistName.setEditable(false);
        dentistName.setBorder(null);
        dentistName.setCaretColor(new java.awt.Color(55, 96, 128));
        dentistName.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        dentistName.setOpaque(false);
        dentistName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dentistNameFocusGained(evt);
            }
        });
        dentistName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dentistNameActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Dentist Name");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Patient Name");

        patientName.setEditable(false);
        patientName.setBorder(null);
        patientName.setCaretColor(new java.awt.Color(55, 96, 128));
        patientName.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        patientName.setOpaque(false);
        patientName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientNameFocusGained(evt);
            }
        });
        patientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientNameActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Patient Contact");

        patientContact.setEditable(false);
        patientContact.setBorder(null);
        patientContact.setCaretColor(new java.awt.Color(30, 59, 92));
        patientContact.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        patientContact.setOpaque(false);
        patientContact.setSelectedTextColor(new java.awt.Color(55, 96, 128));
        patientContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientContactFocusGained(evt);
            }
        });
        patientContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientContactActionPerformed(evt);
            }
        });

        nameSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        nameSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Invoice Id :");

        invoiceIdText.setEditable(false);
        invoiceIdText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        invoiceIdText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        invoiceIdText.setCaretColor(new java.awt.Color(55, 96, 128));
        invoiceIdText.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        invoiceIdText.setOpaque(false);
        invoiceIdText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                invoiceIdTextFocusGained(evt);
            }
        });
        invoiceIdText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceIdTextActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Date :");

        dateText.setEditable(false);
        dateText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dateText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dateText.setCaretColor(new java.awt.Color(55, 96, 128));
        dateText.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        dateText.setOpaque(false);
        dateText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateTextFocusGained(evt);
            }
        });
        dateText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceIdText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dentistContact, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameSeparator5)
                            .addComponent(dentistName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameSeparator4)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameSeparator6)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(nameSeparator7)
                                .addGap(12, 12, 12))
                            .addComponent(patientName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(patientContact, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(invoiceIdText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(patientName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(patientContact, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(dentistName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(dentistContact, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Prescription");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Treatments");

        presciptionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Morning", "Afternoon", "Night", "Duration"
            }
        ));
        jScrollPane3.setViewportView(presciptionTable);

        treatmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Treatment Name", "Treatment Cost"
            }
        ));
        jScrollPane4.setViewportView(treatmentTable);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total Amount :");

        totalAmountText.setEditable(false);
        totalAmountText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalAmountText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totalAmountText.setCaretColor(new java.awt.Color(55, 96, 128));
        totalAmountText.setDisabledTextColor(new java.awt.Color(55, 96, 128));
        totalAmountText.setOpaque(false);
        totalAmountText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                totalAmountTextFocusGained(evt);
            }
        });
        totalAmountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAmountTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(totalAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 10, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(23, 35, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Invoice");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dentistNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dentistNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistNameActionPerformed

    private void dentistNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dentistNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistNameFocusGained

    private void dentistContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dentistContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistContactActionPerformed

    private void dentistContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dentistContactFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistContactFocusGained

    private void patientNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientNameFocusGained

    private void patientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientNameActionPerformed

    private void patientContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientContactFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientContactFocusGained

    private void patientContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientContactActionPerformed

    private void invoiceIdTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_invoiceIdTextFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_invoiceIdTextFocusGained

    private void invoiceIdTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceIdTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invoiceIdTextActionPerformed

    private void dateTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateTextFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTextFocusGained

    private void dateTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTextActionPerformed

    private void totalAmountTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_totalAmountTextFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountTextFocusGained

    private void totalAmountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAmountTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dateText;
    private javax.swing.JTextField dentistContact;
    private javax.swing.JTextField dentistName;
    private javax.swing.JTextField invoiceIdText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator nameSeparator4;
    private javax.swing.JSeparator nameSeparator5;
    private javax.swing.JSeparator nameSeparator6;
    private javax.swing.JSeparator nameSeparator7;
    private javax.swing.JTextField patientContact;
    private javax.swing.JTextField patientName;
    private javax.swing.JTable presciptionTable;
    private javax.swing.JTextField totalAmountText;
    private javax.swing.JTable treatmentTable;
    // End of variables declaration//GEN-END:variables

}
