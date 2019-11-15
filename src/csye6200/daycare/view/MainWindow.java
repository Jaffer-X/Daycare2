package csye6200.daycare.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import csye6200.daycare.lib.*;

/*
 * author:fc
 */
public class MainWindow extends JFrame{
	private static final MainWindow instance = new MainWindow();
	private MainWindow() {}
	public static MainWindow getInstance() {
		return instance;
	}
	
	private Toaster toaster;
	
    public void start() {
        JPanel mainJPanel = getMainJPanel();

        addLogo(mainJPanel);

        addSeparator(mainJPanel);
        
        addTeacherButton(mainJPanel);
        addStudentButton(mainJPanel);
        addAdvanceButton(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
    }

    private JPanel getMainJPanel() {
        this.setUndecorated(true);

        Dimension size = new Dimension(800, 400);

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

        MouseAdapter ma = new MouseAdapter() {
            int lastX, lastY;

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                lastX = x;
                lastY = y;
            }
        };

        panel1.addMouseListener(ma);
        panel1.addMouseMotionListener(ma);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        return panel1;
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        panel1.add(separator1);
        separator1.setBounds(310, 80, 1, 240);
    }

    private void addLogo(JPanel panel1) {
        JLabel label1 = new JLabel();
        label1.setFocusable(false);
        label1.setIcon(new ImageIcon("resources//LoginWindow_logo.png"));
        panel1.add(label1);
        label1.setBounds(55, 146, 200, 110);
    }

    private JLabel createDefaultButton(JPanel panel1, String text, int positionX, int positionY, int width, int height) {
    	final Color[] ButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};
    	JLabel m_Button = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(ButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(text)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(ButtonColors[1]);
                g2.drawString(text, x2, y2);
            }
        };
    	m_Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	ButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
            	ButtonColors[1] = UIUtils.OFFWHITE;
            	m_Button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	ButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
            	ButtonColors[1] = Color.white;
            	m_Button.repaint();
            }
        });

        m_Button.setBackground(UIUtils.COLOR_BACKGROUND);
        m_Button.setBounds(positionX, positionY, width, height);
        m_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(m_Button);
		return m_Button;
    }
    
    private void addTeacherButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_TEACHER,423,70,250,44);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TeacherEventHandler();
            }
        });
    }

    private void addStudentButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_STUDENT,423,170,250,44);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                StudentEventHandler();
            }
        });
    }

    private void addAdvanceButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_ADVANCE,423,270,250,44);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AdvanceEventHandler();
            }
        });
    }
    
    private void TeacherEventHandler() {
        toaster.warn("goto teacher view event");
    }
    private void StudentEventHandler() {
        toaster.warn("goto student view event");
    }
    private void AdvanceEventHandler() {
        //toaster.warn("goto advance view event");
    	mhide();
    	AdvanceWindow.getInstance().start();
    }
    public void mshow() {
    	this.setVisible(true);
    }
    
    public void mhide() {
    	this.setVisible(false);
    }
}
