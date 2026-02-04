package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton back, pay;

    Paytm(String meter) {
        this.meter = meter;

        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Scan & Pay");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 20, 200, 30);
        add(heading);

        // Payment Image (QR / Logo)
        ImageIcon i1 = new ImageIcon(
                ClassLoader.getSystemResource("icon/scan.png")
        );
        Image i2 = i1.getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(250, 70, 300, 350);
        add(image);

        // Pay Button
        pay = new JButton("Payment Done");
        pay.setBounds(250, 450, 140, 30);
        pay.addActionListener(this);
        add(pay);

        // Back Button
        back = new JButton("Back");
        back.setBounds(410, 450, 140, 30);
        back.addActionListener(this);
        add(back);

        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }

    public static void main(String[] args) {
        new Paytm("");
    }
}
