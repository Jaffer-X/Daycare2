package csye6200.daycare.lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.awt.Cursor.*;

public class HyperlinkText extends JLabel {
    public HyperlinkText(String hyperlinkText, int xPos, int yPos, Runnable hyperlinkAction) {
        super(hyperlinkText);
        setForeground(UIUtils.COLOR_OUTLINE);
        setFont(UIUtils.FONT_FORGOT_PASSWORD);
        setCursor(getPredefinedCursor(HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hyperlinkAction.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(UIUtils.COLOR_OUTLINE.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(UIUtils.COLOR_OUTLINE);
            }
        });

        Dimension prefSize = getPreferredSize();
        setBounds(xPos, yPos, prefSize.width, prefSize.height);
    }
}