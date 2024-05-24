package quizApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Result implements ActionListener {
    JFrame frame;
    public Result(int cid)  {
        // Fetch candidate details (candidate class used)
        CandidateInfo candidateInfo = CandidateInfo.fetchCandidateDetails(cid);

        // Create JFrame to display the result
        frame = new JFrame("Quiz Result");
        frame.setLocation(500,200);
        frame.setSize(600, 400);
        frame.setLayout(null);

        JLabel resultLabel=new JLabel("RESULT");
        resultLabel.setFont(new Font("Arial",Font.BOLD,25));
        resultLabel.setBounds(240,20,100,40);
        frame.add(resultLabel);

        JLabel nameLabel = new JLabel("Name: " + candidateInfo.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setBounds(10,100,300,30);
        frame.add(nameLabel);

        JLabel emailLabel = new JLabel("Email: " + candidateInfo.getEmail());
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setBounds(10,130,300,30);
        frame.add(emailLabel);

        JLabel rollNoLabel = new JLabel("Roll No: " + candidateInfo.getRollNo());
        rollNoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        rollNoLabel.setBounds(10,160,300,30);
        frame.add(rollNoLabel);

        JLabel correctLabel = new JLabel("Correct Answers: " + candidateInfo.getCorrect());
        correctLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        correctLabel.setBounds(10,190,300,30);
        frame.add(correctLabel);

        // Add details panel to frame

        // Create a button to close the result window
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        closeButton.setBounds(238,280,100,30);
        closeButton.addActionListener(this);

        frame.add(closeButton);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command=ae.getActionCommand();
        if(command.equals("Close")){
            frame.dispose();
        }
    }

}