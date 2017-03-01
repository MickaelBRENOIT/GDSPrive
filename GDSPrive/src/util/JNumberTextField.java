package util;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JNumberTextField extends JTextField {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur qui permet de créer un JTextField qui n'accepte que les
     * chiffres
     */
    public JNumberTextField() {
        super("", 0);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        int key = e.getKeyCode();
        if (Character.isDigit(e.getKeyChar()) || key == e.VK_BACK_SPACE) {
            this.setEditable(true);
        } else {
            this.setEditable(false);
        }
        e.consume();
        return;
    }

}
