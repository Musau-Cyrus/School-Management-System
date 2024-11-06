package UserInterface.Interface;

import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class PromptTextFieldUI extends BasicTextFieldUI {
    private String promptText;

    public PromptTextFieldUI(String promptText) {
        this.promptText = promptText;
    }

    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(g);
        JTextComponent component = getComponent();
        if (component.getText().isEmpty() && !component.hasFocus()) {
            g.setColor(Color.GRAY);
            g.drawString(promptText, 5, component.getHeight() - 5);
        }
    }
}