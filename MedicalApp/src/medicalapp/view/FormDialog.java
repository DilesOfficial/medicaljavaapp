package medicalapp.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FormDialog extends JDialog {
    private Map<String, JTextField> fields = new HashMap<>();
    private boolean submitted = false;

    public FormDialog(JFrame parent, String title, String[] fieldNames) {
        super(parent, title, true);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(fieldNames.length, 2, 5, 5));
        for (String name : fieldNames) {
            formPanel.add(new JLabel(name + ":"));
            JTextField tf = new JTextField(20);
            fields.put(name, tf);
            formPanel.add(tf);
        }
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton okBtn = new JButton("Save");
        okBtn.addActionListener(e -> {
            submitted = true;
            dispose();
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isSubmitted() { return submitted; }

    public String getValue(String fieldName) {
        return fields.get(fieldName).getText().trim();
    }

    public void setValue(String fieldName, String value) {
        if (fields.containsKey(fieldName)) {
            fields.get(fieldName).setText(value);
        }
    }

    public void setFieldEditable(String fieldName, boolean editable) {
        if (fields.containsKey(fieldName)) {
            fields.get(fieldName).setEditable(editable);
        }
    }
}
