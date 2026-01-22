import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class WavePanel extends JPanel {

    // INI TEMPATNYA 👇
    public WavePanel() {
        setOpaque(false); // WAJIB
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(w - 120, 0);

        path.curveTo(w, h * 0.25, w, h * 0.35, w - 120, h * 0.5);
        path.curveTo(w - 250, h * 0.7, w, h * 0.85, w - 120, h);

        path.lineTo(0, h);
        path.closePath();

        g2.setColor(new Color(18, 52, 63));
        g2.fill(path);
    }
}
