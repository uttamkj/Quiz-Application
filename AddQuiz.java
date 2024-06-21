package quizApp;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddQuiz implements ActionListener {
    JFrame frame;
    JLabel lqid, lquestion, lop1, lop2, lop3, lop4, lans;
    JTextField tfqid, tfques, tfop1, tfop2, tfop3, tfop4;
    JButton addbtn, btnHome;
    JComboBox<String> ansComboBox;

    AddQuiz() {
        frame = new JFrame("Add Quiz");
        frame.setSize(800, 600);
        frame.setLocation(350, 150);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        lqid = new JLabel("Enter the Question id: ");
        lqid.setBounds(50, 20, 350, 30);
        frame.add(lqid);

        tfqid = new JTextField();
        tfqid.setBounds(50, 60, 350, 30);
        frame.add(tfqid);
        tfqid.setEditable(false); // Make it non-editable

        lquestion = new JLabel("Enter the Question: ");
        lquestion.setBounds(50, 100, 350, 30);
        frame.add(lquestion);

        tfques = new JTextField();
        tfques.setBounds(50, 140, 550, 50);
        frame.add(tfques);

        lop1 = new JLabel("Option 1");
        lop1.setBounds(50, 200, 350, 30);
        frame.add(lop1);

        tfop1 = new JTextField();
        tfop1.setBounds(50, 230, 350, 30);
        frame.add(tfop1);

        lop2 = new JLabel("Option 2");
        lop2.setBounds(50, 270, 350, 30);
        frame.add(lop2);

        tfop2 = new JTextField();
        tfop2.setBounds(50, 300, 350, 30);
        frame.add(tfop2);

        lop3 = new JLabel("Option 3");
        lop3.setBounds(50, 340, 350, 30);
        frame.add(lop3);

        tfop3 = new JTextField();
        tfop3.setBounds(50, 370, 350, 30);
        frame.add(tfop3);

        lop4 = new JLabel("Option 4");
        lop4.setBounds(50, 410, 350, 30);
        frame.add(lop4);

        tfop4 = new JTextField();
        tfop4.setBounds(50, 450, 350, 30);
        frame.add(tfop4);

        lans = new JLabel("Answer");
        lans.setBounds(550, 410, 350, 30);
        frame.add(lans);

        ansComboBox = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3", "Option 4"});
        ansComboBox.setBounds(550, 450, 100, 30);
        frame.add(ansComboBox);

        btnHome = new JButton("HOME");
        addbtn = new JButton("ADD");

        btnHome.setBounds(50, 500, 90, 30);
        addbtn.setBounds(550, 500, 90, 30);
        btnHome.addActionListener(this);
        addbtn.addActionListener(this);
        frame.add(btnHome);
        frame.add(addbtn);

        /*JLabel lShowResults = new JLabel("Show all student results");
        lShowResults.setBounds(110, 500, 200, 30);
        frame.add(lShowResults);

         JButton btnshow = new JButton("Show");
        btnshow.setBounds(30, 500, 70, 30);
        btnshow.addActionListener(this);
        frame.add(btnshow);*/
        // do this task


        // Fetch the next question ID
        fetchNextQuestionID();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void fetchNextQuestionID() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            if (con != null) {
                System.out.println("Connected...");
            }
            String qry = "SELECT MAX(qid) FROM Question";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            if (rs.next()) {
                int nextID = rs.getInt(1) + 1;
                tfqid.setText(String.valueOf(nextID));
            } else {
                tfqid.setText("1");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            tfqid.setText("1"); // Default to 1 if there's an error
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(btnHome)) {
            frame.dispose();
            new HomePage();
        } else if (ae.getSource().equals(addbtn)) {
            if (validateInputs()) {
                int qid = Integer.parseInt(tfqid.getText());
                String question = tfques.getText();
                String op1 = tfop1.getText();
                String op2 = tfop2.getText();
                String op3 = tfop3.getText();
                String op4 = tfop4.getText();
                String selectedOption = (String) ansComboBox.getSelectedItem();

                String ans = "";
                switch (selectedOption) {
                    case "Option 1":
                        ans = op1;
                        break;
                    case "Option 2":
                        ans = op2;
                        break;
                    case "Option 3":
                        ans = op3;
                        break;
                    case "Option 4":
                        ans = op4;
                        break;
                }

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
                    if (con != null) {
                        System.out.println("Connected...");
                    }
                    String qry = "INSERT INTO Question values("+qid+",'"+question+"','"+op1+"','"+op2+"','"+op3+"','"+op4+"','"+ans+"')";
                    Statement smt = con.createStatement();
                    int i = smt.executeUpdate(qry);
                    JOptionPane.showMessageDialog(frame, i + " Question Inserted..");

                    smt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clear();
                fetchNextQuestionID(); // Update the next question ID after insertion
            }
        }
    }

    public void clear() {
        tfop1.setText("");
        tfop2.setText("");
        tfop3.setText("");
        tfop4.setText("");
        tfques.setText("");
        ansComboBox.setSelectedIndex(-1); // Clear the combo box selection
    }

    public boolean validateInputs() {
        if (tfques.getText().isEmpty() || tfop1.getText().isEmpty() ||
                tfop2.getText().isEmpty() || tfop3.getText().isEmpty() || tfop4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
 /*   create table Question(
        qid number(2),
    question varchar2(100),
    op1 varchar2(30),
    op2 varchar2(30),
    op3 varchar2(30),
    op4 varchar2(30),
    ans varchar2(30)
);*/
