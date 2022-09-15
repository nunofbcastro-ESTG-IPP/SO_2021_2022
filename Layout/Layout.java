package Layout;

import javax.swing.*;

import java.awt.*;

public class Layout {

    public static JFrame makeFrame(String nameJFrame, String fileIcon, int width, int height) {
        JFrame newFrame = new JFrame(nameJFrame);
        ImageIcon icone = new ImageIcon(fileIcon);
        newFrame.setIconImage(icone.getImage());
        newFrame.setPreferredSize(new Dimension(width, height));
        newFrame.setFont(new java.awt.Font("Consolas", 0, 14));
        return newFrame;
    }

    public static void visibleFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void setLayoutLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static JButton createButton(String text) {
        JButton button = new JButton();
        button.setText(text);
        return button;
    }

    public static JLabel createLabel(String text, int textSize) {
        JLabel jLabel = new JLabel();
        jLabel.setFont(new java.awt.Font("Segoe UI", 0, textSize));
        jLabel.setText(text);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }

    public static JLabel createImage(String path, int width, int height) {
        ImageIcon imgIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        JLabel jLabel = new JLabel(imgIcon);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public static void clearPanel(JPanel panel) {
        if (panel != null) {
            panel.removeAll();
        }
    }

    public static void refreshPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

}
