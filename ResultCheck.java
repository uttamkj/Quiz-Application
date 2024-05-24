package quizApp;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class ResultCheck implements ActionListener {
    JFrame frame;
    JButton btnShow, btnClose;
    JLabel lname, lroll, lmark, lrollInput;
    JTextField tfrollInput;
    ResultCheck() {
        frame = new JFrame("Your Result");
        frame.setSize(400, 300);
        frame.setLocation(350, 150);
        frame.setLayout(null);

        lrollInput = new JLabel("Enter Roll No:");
        lrollInput.setBounds(30, 20, 100, 30);
        frame.add(lrollInput);

        tfrollInput = new JTextField();
        tfrollInput.setBounds(150, 20, 100, 30);
        frame.add(tfrollInput);

        lname = new JLabel("Name: ");
        lname.setBounds(30, 60, 300, 30);
        frame.add(lname);

        lroll = new JLabel("Roll No: ");
        lroll.setBounds(30, 100, 300, 30);
        frame.add(lroll);

        lmark = new JLabel("Mark: ");
        lmark.setBounds(30, 140, 300, 30);
        frame.add(lmark);

        btnShow = new JButton("Show");
        btnShow.setBounds(30, 200, 80, 30);
        btnShow.addActionListener(this);
        frame.add(btnShow);

        btnClose = new JButton("Close");
        btnClose.setBounds(150, 200, 80, 30);
        btnClose.addActionListener(this);
        frame.add(btnClose);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(btnClose)) {
            frame.dispose();
        } else if (ae.getSource().equals(btnShow)) {
            ShowResult();
        }
    }

    public void ShowResult() {
        int rollInput = Integer.parseInt(tfrollInput.getText());
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement smt = con.createStatement();
            String qry = "select * from Candidate where roll = "+rollInput+" ";
            ResultSet rs = smt.executeQuery(qry);
            if (rs.next()) {
                String name = rs.getString(2);
                int roll = rs.getInt(3);
                int correct = rs.getInt(5);

                // Set the values to the labels
                lname.setText("Name: " + name);
                lroll.setText("Roll No: " + roll);
                lmark.setText("Mark: " + correct);
            } else {
                JOptionPane.showMessageDialog(frame, "No result found for Roll No: " + rollInput);
            }

            rs.close();
            smt.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
