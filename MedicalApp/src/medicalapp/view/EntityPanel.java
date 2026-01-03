package medicalapp.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class EntityPanel extends JPanel {
    protected DefaultTableModel tableModel;
    protected JTable table;

    public EntityPanel(String[] columnNames) {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e -> onAdd());
        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> onEdit());
        JButton delBtn = new JButton("Delete");
        delBtn.addActionListener(e -> onDelete());

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(delBtn);
        add(btnPanel, BorderLayout.SOUTH);
        
        // refreshData(); // Moved to subclass to avoid NPE
    }

    protected abstract void onAdd();

    protected void onEdit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            editItem(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to edit.");
        }
    }

    protected abstract void editItem(int index);
    
    protected void onDelete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            deleteItem(selectedRow);
            refreshData();
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to delete.");
        }
    }

    protected abstract void deleteItem(int index);
    protected abstract void refreshData();
}
