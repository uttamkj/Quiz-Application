package quizApp;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddQuiz implements ActionListener {
    JFrame frame;
    JLabel lqid,lquestion ,lop1,lop2,lop3,lop4,lans;
    JTextField tfqid, tfques,tfop1,tfop2,tfop3,tfop4,tfans;
    JButton addbtn,btnHome;
    AddQuiz(){
        frame = new JFrame("Add Quiz");
        frame.setSize(800,600);
        frame.setLocation(200,100);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        ImageIcon imgIcon = new ImageIcon("logo.jpeg");
//        frame.setIconImage(imgIcon.getImage());


        lqid = new JLabel("Enter the Question id: ");
        lqid.setBounds(50,20,350,30);
        frame.add(lqid);

        tfqid = new JTextField();
        tfqid.setBounds(50,60,350,30);
        frame.add(tfqid);

        lquestion = new JLabel("Enter the Question: ");
        lquestion.setBounds(50,50,350,50);
        frame.add(lquestion);

        tfques = new JTextField();
        tfques.setBounds(50,110,550,50);
        frame.add(tfques);

        lop1 = new JLabel("Option 1");
        lop1.setBounds(50,170,350,30);
        frame.add(lop1);

        tfop1 = new JTextField();
        tfop1.setBounds(50,210,350,30);
        frame.add(tfop1);
// op2
        lop2 = new JLabel("Option 2");
        lop2.setBounds(50,250,350,30);
        frame.add(lop2);

        tfop2 = new JTextField();
        tfop2.setBounds(50,290,350,30);
        frame.add(tfop2);

        lop3 = new JLabel("Option 3");
        lop3.setBounds(50,330,350,30);
        frame.add(lop3);

        tfop3 = new JTextField();
        tfop3.setBounds(50,370,350,30);
        frame.add(tfop3);

        lop4 = new JLabel("Option 4");
        lop4.setBounds(50,410,350,30);
        frame.add(lop4);

        tfop4 = new JTextField();
        tfop4.setBounds(50,450,350,30);
        frame.add(tfop4);

        lans = new JLabel("Answer");
        lans.setBounds(550,410,350,30);
        frame.add(lans);

        tfans = new JTextField();
        tfans.setBounds(550,450,100,30);
        frame.add(tfans);
//btn
        btnHome = new JButton("HOME");
        addbtn = new JButton("ADD");

        btnHome.setBounds(50,500,90,30);
        addbtn.setBounds(550,500,90,30);
        btnHome.addActionListener(this);
        addbtn.addActionListener(this);
        frame.add(btnHome);
        frame.add(addbtn);

        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(btnHome)){
            frame.dispose();
            new HomePage();
        }
        else if(ae.getSource().equals(addbtn)){
            int qid = Integer.parseInt(tfqid.getText());
            String question = tfques.getText();
            String op1 = tfop1.getText();
            String op2 = tfop2.getText();
            String op3 = tfop3.getText();
            String op4 = tfop4.getText();
            String ans = tfans.getText();

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","uttam");
                if(con != null){
                    System.out.println("Connected...");
                }
                String qry = "INSERT INTO Question values("+qid+",'"+question+"','"+op1+"','"+op2+"','"+op3+"','"+op4+"','"+ans+"')";
                Statement smt = con.createStatement();
                int i = smt.executeUpdate(qry);
                JOptionPane.showMessageDialog(frame,i+" Question Inserted..");

                smt.close();
                con.close();
            }catch(SQLException se){
                System.out.println(se);
            }catch (ClassNotFoundException ce){
                System.out.println(ce);
            }catch(Exception e){
                System.out.println(e);
            }
            clear();
        }
    }
    public void clear(){
        tfop1.setText("");tfop2.setText("");tfop3.setText("");tfop4.setText("");tfans.setText("");tfques.setText("");tfqid.setText("");
    }

}
