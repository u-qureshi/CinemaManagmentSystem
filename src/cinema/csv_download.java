package cinema;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class csv_download {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table Demo");
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Name");
        model.addColumn("Age");
        model.addRow(new Object[]{"John Doe", 25});
        model.addRow(new Object[]{"Jane Smith", 30});
        JButton button = new JButton("Download CSV");
       button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String filename = "table.csv";
                    FileWriter fw = new FileWriter(filename);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    if (model.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "There is no data in the table to export.");
                        return;
                    }
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        fw.write(model.getColumnName(i) + ",");
                    }
                    fw.write("\n");
                    for (int i = 0; i < model.getRowCount(); i++) {
                        for (int j = 0; j < model.getColumnCount(); j++) {
                            fw.write(model.getValueAt(i, j).toString() + ",");
                        }
                        fw.write("\n");
                    }
                    fw.close();
                    JOptionPane.showMessageDialog(null, "CSV file has been downloaded.");
                    System.out.println("File written to: " + new File(filename).getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(new JScrollPane(table));
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}