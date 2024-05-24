package quizApp;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;
import java.sql.*;
class QuizStart implements ActionListener {
    JFrame frame;
    JLabel lQuiz, lUserName, lRollNo, lEmail;
    JTextField tfUserName, tfRollNo, tfEmail;
    JButton btStart, btnHome;
    int countcandidate;
    QuizStart() {
        countcandidate=setCountcandidate();
        System.out.println(countcandidate);
        frame = new JFrame();
        frame.setLocation(350, 150);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        lQuiz = new JLabel("Quiz Application");
        lQuiz.setBounds(200, 50, 350, 80);
        lQuiz.setFont(new Font("Calibri", Font.ITALIC, 80));
        frame.add(lQuiz);

        lUserName = new JLabel("Username");
        lUserName.setBounds(200, 150, 400, 40);
        lUserName.setFont(new Font("Calibri", Font.BOLD, 20));
        frame.add(lUserName);

        tfUserName = new JTextField();
        tfUserName.setBounds(200, 190, 400, 30);
        frame.add(tfUserName);

        lRollNo = new JLabel("Roll No");
        lRollNo.setBounds(200, 240, 400, 20);
        lRollNo.setFont(new Font("Calibri", Font.BOLD, 20));
        frame.add(lRollNo);

        tfRollNo = new JTextField();
        tfRollNo.setBounds(200, 270, 400, 30);
        frame.add(tfRollNo);

        lEmail = new JLabel("Email");
        lEmail.setBounds(200, 320, 400, 20);
        lEmail.setFont(new Font("Calibri", Font.BOLD, 20));
        frame.add(lEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(200, 350, 400, 30);
        frame.add(tfEmail);

        btStart = new JButton("Start");
        btStart.setBounds(320, 400, 130, 40);
        btStart.setFont(new Font("Calibri", Font.BOLD, 20));
        btStart.addActionListener(this);
        frame.add(btStart);

        btnHome = new JButton("HOME");
        btnHome.setBounds(320, 460, 130, 40);
        btnHome.setFont(new Font("Calibri", Font.BOLD, 20));
        btnHome.addActionListener(this);
        frame.add(btnHome);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(btnHome)) {
            frame.dispose();
            new HomePage();
        } else if (ae.getSource().equals(btStart)) {
            if (tfUserName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter username");
                tfUserName.setText("");

                return;
            }
            if (tfRollNo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter roll no");
                tfRollNo.setText("");

                return;
            }
            if (tfEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter email");
                tfEmail.setText("");

                return;
            }
            countcandidate++;
            frame.dispose();
            int numberOfQuestions = getNumberOfQuestionsFromDatabase(); // Replace with actual database call
            new QuestionFrame(numberOfQuestions, numberOfQuestions,countcandidate);
        }
    }

    private int getNumberOfQuestionsFromDatabase() {
        int numberOfQuestions = 0;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            String qry = "INSERT INTO candidate values("+countcandidate+",'"+tfUserName.getText()+ "','" +tfRollNo.getText()+ "','" + tfEmail.getText() + "'," +0+ ")";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM question");

            if (resultSet.next()) {
                numberOfQuestions = resultSet.getInt(1);
            }
            stmt.executeUpdate(qry);
            resultSet.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return numberOfQuestions;
    }
    public int setCountcandidate(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM candidate");

            if (resultSet.next()) {
                countcandidate = resultSet.getInt(1);
            }
            resultSet.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return countcandidate;
    }
}