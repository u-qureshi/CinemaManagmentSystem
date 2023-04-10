package cinema;

import javax.swing.*;
import java.awt.*;

public class Admin extends JFrame {

    public Admin() {
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JButton btnManageMovies = new JButton("Manage Movies");
        btnManageMovies.addActionListener(e -> {
            dispose();
            new MovieManagment().setVisible(true);
        });
        add(btnManageMovies);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
            });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnManageMovies)
                    .addComponent(btnLogout))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(btnManageMovies)
                .addComponent(btnLogout)
        );

        Font font = new Font("Arial", Font.PLAIN, 14);
        btnManageMovies.setFont(font);
        btnLogout.setFont(font);
    }
}
