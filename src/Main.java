import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Extreme Writer");

            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setUndecorated(true);

            JTextArea editor = new JTextArea();

            editor.setFont(new Font("Serif", Font.PLAIN, 24));
            editor.setLineWrap(true);
            editor.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(editor);

            JLabel wordCounter = new JLabel("Words: 0");
            wordCounter.setFont(new Font("SansSerif", Font.BOLD, 18));

            JPanel bottom = new JPanel(new BorderLayout());
            bottom.add(wordCounter, BorderLayout.EAST);

            window.setLayout(new BorderLayout());
            window.add(scrollPane, BorderLayout.CENTER);
            window.add(bottom, BorderLayout.SOUTH);

            editor.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    private void update() {
                        String text = editor.getText().trim();

                        int words = text.isEmpty()
                                ? 0
                                : text.split("\\s+").length;

                        wordCounter.setText("Words: " + words);
                    }

                    @Override
                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {
                        update();
                    }

                    @Override
                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {
                        update();
                    }

                    @Override
                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {
                        update();
                    }
                }
            );

            window.setVisible(true);
            editor.requestFocusInWindow();
        });
    }
}