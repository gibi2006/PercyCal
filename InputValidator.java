import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class InputValidator {
    private final Color defaultColor;
    private final Color invalidColor;

    public InputValidator(Color defaultColor, Color invalidColor) {
        this.defaultColor = defaultColor;
        this.invalidColor = invalidColor;
    }

    public void apply(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            void validate() {
                String text = field.getText();
                if (text.isEmpty() || isValidNumber(text)) {
                    field.setBorder(new LineBorder(defaultColor, 1, true));
                    field.setToolTipText(null);
                } else {
                    field.setBorder(new LineBorder(invalidColor, 2, true));
                    field.setToolTipText("Please enter a valid number.");
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { validate(); }
            @Override public void removeUpdate(DocumentEvent e) { validate(); }
            @Override public void changedUpdate(DocumentEvent e) { validate(); }
        });
    }

    private boolean isValidNumber(String input) {
        return input.matches("^\\d*\\.?\\d*$");
    }
}