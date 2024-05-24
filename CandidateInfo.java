package quizApp;
import java.sql.*;
class CandidateInfo {
    int cid,rollNo,correct;
    String name,email;
    public CandidateInfo(int cid, String name, String email, int rollNo) {
        this.cid = cid;
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setCorrect(int correct,int cid1) {
        this.correct=correct;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement stmt = conn.createStatement();
            stmt.executeQuery("update candidate set correct =" + correct + "where cid=" + cid1);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getCorrect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT correct FROM candidate WHERE cid = " + cid);
            while(rs.next()){
                correct=rs.getInt("correct");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correct;
    }

    public int getRollNo() {
        return rollNo;
    }

    public static CandidateInfo fetchCandidateDetails(int cid) {
        CandidateInfo candidate = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM candidate WHERE cid = " + cid);

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int rollNo = rs.getInt("roll");
                candidate = new CandidateInfo(cid, name, email, rollNo);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }


}
    /* candidate table
    create table candidate(
        cid number(3),
        name varchar(40),
        roll number(3),
        email varchar(30),
        correct number(3))
     */