package com.hexacode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
        setPreferredSize(new Dimension(400, 60));

        JLabel titleLabel = new JLabel("Student TaskPro", SwingConstants.RIGHT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel subtitleLabel = new JLabel("BY HEXACODE", SwingConstants.RIGHT);
        subtitleLabel.setForeground(Color.LIGHT_GRAY);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(30, 30, 30));
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = loadCircularLogo();

        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            System.out.println("Failed to load circular logo!");
        }

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(30, 30, 30));
        rightPanel.add(textPanel);
        rightPanel.add(logoLabel);

        add(rightPanel, BorderLayout.EAST);
    }

    private ImageIcon loadCircularLogo() {
        BufferedImage originalImage = null;

        try {
            URL imageUrl = getClass().getResource("src/main/resources/themes/logo.png");
            if (imageUrl != null) {
                originalImage = ImageIO.read(imageUrl);
            }
        } catch (IOException e) {
            System.out.println("Error loading logo from resources: " + e.getMessage());
        }

        if (originalImage == null) {
            File file = new File("src/main/resources/themes/logo.png");
            if (file.exists()) {
                try {
                    originalImage = ImageIO.read(file);
                } catch (IOException e) {
                    System.out.println("Error loading logo from absolute path: " + e.getMessage());
                }
            }
        }

        if (originalImage != null) {
            return new ImageIcon(makeCircularImage(originalImage, 50));
        }

        return null;
    }

    private BufferedImage makeCircularImage(BufferedImage img, int size) {
        BufferedImage circularImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circularImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));

        g2.drawImage(img.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
        g2.dispose();

        return circularImage;
    }
}
