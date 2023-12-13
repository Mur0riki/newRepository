package edu.project4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

public class FractalFlame extends JFrame {
    private final int WIDTH = 1900;
    private final int HEIGHT = 1000;
    private final int MAX_ITERATIONS = 4999999;

    private Random random;

    public FractalFlame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fractal Flame");
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);

        random = new Random();
    }

    public void paint(Graphics g) {
        super.paint(g);

        double x = 0.0;
        double y = 0.0;

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            int randomIndex = random.nextInt(6);

            switch (randomIndex) {
                case 0:
                    x = Math.sin(x * x - y * y);
                    y = Math.sin(2 * x * y);
                    break;
                case 1:
                    x = Math.cos(x * y);
                    y = Math.sin(x / y);
                    break;
                case 2:
                    x = Math.tan(x * x + y * y);
                    y = Math.cos(x - y);
                    break;
                case 3:
                    x = Math.sin(Math.sin(x * y));
                    y = Math.cos(Math.cos(x / y));
                    break;
                case 4:
                    x = Math.atan(x + y);
                    y = Math.cos(Math.sin(x / y));
                    break;
                case 5:
                    x = Math.tanh(x + y);
                    y = Math.sin(x - y);
                    break;
            }

            Color color = calculateColor(i, MAX_ITERATIONS);
            g.setColor(color);
            g.drawLine((int) (WIDTH * (x + 1) / 2), (int) (HEIGHT * (y + 1) / 2), (int) (WIDTH * (x + 1) / 2), (int) (HEIGHT * (y + 1) / 2));
        }
    }

    private Color calculateColor(int i, int maxIterations) {
        int[] colors = {0xFF0033, 0xFF6600, 0xFFCC00, 0x33CC00, 0x3399FF, 0x3300FC};
        int index = (int) (i * ((double) colors.length / maxIterations)) % colors.length;
        return new Color(colors[index]);
    }

    public static void main(String[] args) {
        FractalFlame fractal = new FractalFlame();
        fractal.setVisible(true);
    }
}
