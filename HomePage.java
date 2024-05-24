package quizApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomePage implements ActionListener{
    JFrame frame;
    JLabel lHome, ltailmsg,lShowResults,lResultClick;
    JButton btnadmin, btnguest,btnResult,btnshow;
    HomePage(){
        // Initialize the frame
        frame = new JFrame("Home Page");
        frame.setLocation(350, 150);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lHome = new JLabel("WELCOME");
        lHome.setBounds(270, 50, 600, 60);
        Font f = new Font("Arial", Font.BOLD,50);
        lHome.setFont(f);
        lHome.setForeground(Color.RED);
        frame.add(lHome);

         ltailmsg = new JLabel("@project for TA Silicon University");
         ltailmsg.setBounds(300, 300, 200, 30);
         frame.add(ltailmsg);

        btnadmin = new JButton("Admin");
        btnadmin.setBounds(300, 200, 200, 30);
        btnadmin.addActionListener(this);
        frame.add(btnadmin);

        btnguest = new JButton("Guest");
        btnguest.setBounds(300, 250, 200, 30);
        btnguest.addActionListener(this);
        frame.add(btnguest);

        lResultClick = new JLabel("Click me to show your result");
        lResultClick.setBounds(110, 450, 200, 30);
        frame.add(lResultClick);

        btnResult = new JButton("Click");
        btnResult.setBounds(30, 450, 70, 30);
        btnResult.addActionListener(this);
        frame.add(btnResult);

        lShowResults = new JLabel("Show all student results");
        lShowResults.setBounds(110, 500, 200, 30);
        frame.add(lShowResults);

        btnshow = new JButton("Show");
        btnshow.setBounds(30, 500, 70, 30);
        btnshow.addActionListener(this);
        frame.add(btnshow);

        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource().equals(btnadmin)){
            frame.dispose();
            new AddQuiz();
        } else if (ae.getSource().equals(btnguest)) {
            System.out.println("Btn guest is clicked!!!");
            frame.dispose();
            new QuizStart();
        } else if (ae.getSource().equals(btnResult)) {
            frame.dispose();
            new ResultCheck();
        }else if (ae.getSource().equals(btnshow)) {
            new Table();
        }
    }
    public static void main(String[] args) {

        new HomePage();
    }
}

// database id system pass - uttam tableName - Question
//create table Question(
//        qid number(2),
//    question varchar2(100),
//    op1 varchar2(30),
//    op2 varchar2(30),
//    op3 varchar2(30),
//    op4 varchar2(30),
//    ans varchar2(30)
//);

