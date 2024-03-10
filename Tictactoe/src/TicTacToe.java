import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private char symbol;
    private JButton[] buttons;

    public TicTacToe() {
        symbol = 'X';
        buttons = new JButton[9];
        setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        for(int i=0; i<9; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton)e.getSource();
                    buttonClicked.setText(String.valueOf(symbol));
                    buttonClicked.setEnabled(false);
                    check();
                    switchSymbol();
                }
            });
            add(buttons[i]);
        }
        setVisible(true);
    }

    public void switchSymbol() {
        if (symbol == 'X') {
            symbol = 'O';
        } else {
            symbol = 'X';
        }
    }

    public void check() {
        for (int i = 0; i < 9; i += 3) {
            if (row(i, i + 1, i + 2)) {
                end(buttons[i], buttons[i + 1], buttons[i + 2]);
                return;
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (row(i, i + 3, i + 6)) {
                end(buttons[i], buttons[i + 3], buttons[i + 6]);
                return;
            }
        }

        if (row(0, 4, 8)) {
            end(buttons[0], buttons[4], buttons[8]);
            return;
        }
        if (row(2, 4, 6)) {
            end(buttons[2], buttons[4], buttons[6]);
            return;
        }

        if (draw()) {
            JOptionPane.showMessageDialog(this, "Draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    public boolean draw() {
        int buttonCount = buttons.length;
        for (int i = 0; i < buttonCount; i++) {
            JButton button = buttons[i];
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean row(int a, int b, int c) {
        return buttons[a].getText().equals(buttons[b].getText()) && buttons[b].getText().equals(buttons[c].getText()) && !buttons[a].getText().equals("");
    }

    public void end(JButton a, JButton b, JButton c) {
        a.setBackground(Color.GREEN);
        b.setBackground(Color.GREEN);
        c.setBackground(Color.GREEN);
        int buttonCount = buttons.length;
        for (int i = 0; i < buttonCount; i++) {
            buttons[i].setEnabled(false);
        }
        JOptionPane.showMessageDialog(this, "Winner: " + symbol, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
