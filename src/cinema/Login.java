package cinema;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private static Object[] validateUser(String username, String password) {
    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";
    
    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        
        ResultSet resultSet = statement.executeQuery();
        Object[] user_data = new Object[2];
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String account_type =resultSet.getString("account_type");
            user_data[0] = account_type;
            user_data[1]=id;
             return user_data;
        } else {
            return null;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        txtUsername.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            Object[] user = validateUser(username, password);
            if (user != null) {
                dispose();
                if ("admin".equals(user[0])){
                new Admin().setVisible(true);
                }
                else{
                    int user_id = ((Integer) user[1]).intValue();
                new Booking(user_id).setVisible(true);
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        });
        
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> {
            dispose();
            new Registration().setVisible(true);
        });
        add(btnRegister);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
    layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblUsername)
            .addComponent(lblPassword))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(txtUsername)
            .addComponent(txtPassword)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnLogin)
                .addComponent(btnRegister)))
);

layout.setVerticalGroup(
    layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(lblUsername)
            .addComponent(txtUsername))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(lblPassword)
            .addComponent(txtPassword))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(btnLogin)
            .addComponent(btnRegister))
);

Font font = new Font("Arial", Font.PLAIN, 14);

lblUsername.setFont(font);
txtUsername.setFont(font);
lblPassword.setFont(font);
txtPassword.setFont(font);
btnLogin.setFont(font);
btnRegister.setFont(font);
    }}
