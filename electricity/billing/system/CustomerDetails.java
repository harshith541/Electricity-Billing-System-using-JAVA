package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{

    Choice meternumber, cmonth;
    
    JTable table;
    JButton search, print;
    JButton delete;

    
    CustomerDetails(){
        
        super("Customer Details");
        
        setSize(1200, 650);
        setLocation(200, 150);
        
        table = new JTable();
        delete = new JButton("Delete");
delete.addActionListener(this);
add(delete, BorderLayout.NORTH);

        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South");
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == delete) {

          int row = table.getSelectedRow();

          if (row == -1) {
              JOptionPane.showMessageDialog(null, "Select a record first");
              return;
          }

          String meter = table.getValueAt(row, 1).toString();

          int confirm = JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to delete?",
                  "Confirm",
                  JOptionPane.YES_NO_OPTION
          );

          if (confirm == JOptionPane.YES_OPTION) {
              try {
                  Conn c = new Conn();
                  c.s.executeUpdate(
                          "DELETE FROM customer WHERE meter_no = '" + meter + "'"
                  );

                  JOptionPane.showMessageDialog(null, "Record Deleted");

                  ResultSet rs = c.s.executeQuery("select * from customer");
                  table.setModel(DbUtils.resultSetToTableModel(rs));

              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }

      if (ae.getSource() == print) {
          try {
              table.print();
         } catch (Exception e) {
             e.printStackTrace();
          }
      } 
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}
