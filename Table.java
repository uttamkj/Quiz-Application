package quizApp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class Table {
    JFrame frame;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    JButton closeBtn;
    Table() {
        frame = new JFrame("Result Table");
        frame.setSize(600, 500);
        frame.setLocation(350, 150);
        frame.setLayout(null);

        model = new DefaultTableModel();
        String[] colName = {"CID", "Name", "Roll", "Email", "Correct"};
        model.setColumnIdentifiers(colName);

        table = new JTable();
        table.setModel(model);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 400);
        frame.add(scrollPane);

        closeBtn = new JButton("Close");
        closeBtn.setBounds(250, 430, 100, 30);
        closeBtn.addActionListener(e -> frame.dispose());
        frame.add(closeBtn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fetchAndDisplayData();
    }
    private void fetchAndDisplayData() {
        String query = "SELECT cid, name, roll, email, correct FROM Candidate ORDER BY correct DESC";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "uttam");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                int roll = rs.getInt("roll");
                String email = rs.getString("email");
                int correct = rs.getInt("correct");

                model.addRow(new Object[]{cid, name, roll, email, correct});
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
}
