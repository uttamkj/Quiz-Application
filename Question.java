package quizApp;
import java.sql.*;

class Question {
    int id;
    String questionText;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String correctOption;

    public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD, String correctOption) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }
    public String getQuestionText() {

        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }
    public static Question[] fetchQuestions(int currentQuestionNumber) {
        Question[] questions = new Question[currentQuestionNumber];

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from question");

            int index = 0;
            while (rs.next()) {
                int currque= rs.getInt("QID");
                String questionText = rs.getString("QUESTION");
                String optionA = rs.getString("op1");
                String optionB = rs.getString("op2");
                String optionC = rs.getString("op3");
                String optionD = rs.getString("op4");
                String correctopt = rs.getString("ans");

                questions[index++] = new Question(currque, questionText, optionA, optionB, optionC, optionD, correctopt);

            }
        } catch (ClassNotFoundException ce) {
            System.out.println(ce);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return questions;
    }
}
