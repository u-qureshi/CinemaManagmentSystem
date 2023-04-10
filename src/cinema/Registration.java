package cinema;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends JFrame {
    
    private static boolean registerUser(Customer customer) {
    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";
    
    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "INSERT INTO users (username, password, account_type) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getUsername());
        statement.setString(2, customer.getPassword());
        statement.setString(3, customer.getAccountType());
        
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public Registration() {
        setTitle("Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        JPasswordField txtConfirmPassword = new JPasswordField();

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String accountType = "customer";
            Customer customer = new Customer(1, username, password);

            if (registerUser(customer)) {
                JOptionPane.showMessageDialog(null, "Registration successful.");
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed.");
            }
        });;

        JButton btnBackToLogin = new JButton("Back to Login");
        // Add action listener for the back to login button as shown before
        btnBackToLogin.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
            });
            add(btnBackToLogin);
            

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsername)
                    .addComponent(lblPassword)
                    .addComponent(lblConfirmPassword))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword)
                    .addComponent(txtConfirmPassword)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegister)
                        .addComponent(btnBackToLogin)))
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
                    .addComponent(lblConfirmPassword)
                    .addComponent(txtConfirmPassword))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnBackToLogin))
        );

        Font font = new Font("Arial", Font.PLAIN, 14);
        lblUsername.setFont(font);
        txtUsername.setFont(font);
        lblPassword.setFont(font);
        txtPassword.setFont(font);
        lblConfirmPassword.setFont(font);
        txtConfirmPassword.setFont(font);
        btnRegister.setFont(font);
        btnBackToLogin.setFont(font);
    }
}
