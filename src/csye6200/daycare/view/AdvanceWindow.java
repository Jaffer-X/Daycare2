package csye6200.daycare.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

import csye6200.daycare.controller.StudentRegisterController;
import csye6200.daycare.lib.*;

/*
 * author:fc
 */
public class AdvanceWindow extends JFrame{
	private static final AdvanceWindow instance = new AdvanceWindow();
	private AdvanceWindow() {start();}
	public static AdvanceWindow getInstance() {
		return instance;
	}
	
	private Toaster toaster;
	private ButtonGroup registerGroup;
	private mRadioButton RB_registerStudent;
	private mRadioButton RB_registerTeacher;
	private mTextField TF_StudentName;
	private mTextField TF_StudentAge;
	private mTextField TF_StudentParentName;
	private mTextField TF_StudentParentPhone;
	private mTextField TF_StudentGender;
	private mTextField TF_StudentAddress;
	private mTextField TF_ReadTest;
	private mTextField TF_SportTest;
	private mTextField TF_MathTest;
	private mTextField TF_TeacherName;
	private mTextField TF_TeacherAge;
	private mTextField TF_TeacherWage;
	private ButtonGroup StrategyGroup;
	private mRadioButton RB_basicStrategy;
	private mRadioButton RB_AISVMStrategy;
	private mRadioButton RB_AIDTStrategy;
	
	
    public void start() {
        JPanel mainJPanel = getMainJPanel();

        //register group
        registerGroup = new ButtonGroup();
        RB_registerStudent = new mRadioButton("Student Register");
        RB_registerTeacher = new mRadioButton("Teacher Register");
        registerGroup.add(RB_registerTeacher);
        registerGroup.add(RB_registerStudent);
        mainJPanel.add(RB_registerTeacher);
        mainJPanel.add(RB_registerStudent);
        RB_registerTeacher.setBounds(20,250,200,40);
        RB_registerStudent.setBounds(20,20,200,40);
        RB_registerStudent.setSelected(true);
        
        //student Area
        JLabel L_StudentName = new JLabel("Name:");
        mainJPanel.add(L_StudentName);
        L_StudentName.setForeground(Color.white);
        L_StudentName.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_StudentName.setBounds(50,70,100,40);
        
        TF_StudentName = new mTextField();
        mainJPanel.add(TF_StudentName);
        TF_StudentName.setBounds(150,70,180,40);
        
        JLabel L_StudentAge = new JLabel("Age:");
        mainJPanel.add(L_StudentAge);
        L_StudentAge.setForeground(Color.white);
        L_StudentAge.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_StudentAge.setBounds(350,70,100,40);
        
        TF_StudentAge = new mTextField();
        mainJPanel.add(TF_StudentAge);
        TF_StudentAge.setBounds(450,70,180,40);
        
        JLabel L_StudentParentName = new JLabel("ParentName:");
        mainJPanel.add(L_StudentParentName);
        L_StudentParentName.setForeground(Color.white);
        L_StudentParentName.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_StudentParentName.setBounds(650,70,100,40);
        
        TF_StudentParentName = new mTextField();
        mainJPanel.add(TF_StudentParentName);
        TF_StudentParentName.setBounds(750,70,180,40);
        
        JLabel L_StudentParentPhone = new JLabel("ParentPhone:");
        mainJPanel.add(L_StudentParentPhone);
        L_StudentParentPhone.setForeground(Color.white);
        L_StudentParentPhone.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_StudentParentPhone.setBounds(50,120,100,40);
        
        TF_StudentParentPhone = new mTextField();
        mainJPanel.add(TF_StudentParentPhone);
        TF_StudentParentPhone.setBounds(150,120,180,40);
        
        JLabel L_StudentGender = new JLabel("Gender:");
        mainJPanel.add(L_StudentGender);
        L_StudentGender.setForeground(Color.white);
        L_StudentGender.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_StudentGender.setBounds(350,120,100,40);
        
        TF_StudentGender = new mTextField();
        mainJPanel.add(TF_StudentGender);
        TF_StudentGender.setBounds(450,120,180,40);
        
        JLabel L_StudentAddress = new JLabel("Address:");
        mainJPanel.add(L_StudentAddress);
        L_StudentAddress.setForeground(Color.white);
        L_StudentAddress.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_StudentAddress.setBounds(650,120,100,40);
        
        TF_StudentAddress = new mTextField();
        mainJPanel.add(TF_StudentAddress);
        TF_StudentAddress.setBounds(750,120,180,40);
        
        JLabel L_ReadingTest = new JLabel("ReadingTest:");
        mainJPanel.add(L_ReadingTest);
        L_ReadingTest.setForeground(Color.white);
        L_ReadingTest.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_ReadingTest.setBounds(50,170,100,40);
        
        TF_ReadTest = new mTextField();
        mainJPanel.add(TF_ReadTest);
        TF_ReadTest.setBounds(150,170,180,40);
        
        JLabel L_SportTest = new JLabel("SportTest:");
        mainJPanel.add(L_SportTest);
        L_SportTest.setForeground(Color.white);
        L_SportTest.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_SportTest.setBounds(350,170,100,40);
        
        TF_SportTest = new mTextField();
        mainJPanel.add(TF_SportTest);
        TF_SportTest.setBounds(450,170,180,40);
        
        JLabel L_MathTest = new JLabel("MathTest:");
        mainJPanel.add(L_MathTest);
        L_MathTest.setForeground(Color.white);
        L_MathTest.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_MathTest.setBounds(650,170,100,40);
        
        TF_MathTest = new mTextField();
        mainJPanel.add(TF_MathTest);
        TF_MathTest.setBounds(750,170,180,40);
        
        //Teacher Area
        JLabel L_TeacherName = new JLabel("Name:");
        mainJPanel.add(L_TeacherName);
        L_TeacherName.setForeground(Color.white);
        L_TeacherName.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_TeacherName.setBounds(50,300,180,40);
        
        TF_TeacherName = new mTextField();
        mainJPanel.add(TF_TeacherName);
        TF_TeacherName.setBounds(150,300,180,40);
        
        JLabel L_TeacherAge = new JLabel("Age:");
        mainJPanel.add(L_TeacherAge);
        L_TeacherAge.setForeground(Color.white);
        L_TeacherAge.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_TeacherAge.setBounds(350,300,180,40);
        
        TF_TeacherAge = new mTextField();
        mainJPanel.add(TF_TeacherAge);
        TF_TeacherAge.setBounds(450,300,180,40);
        
        JLabel L_TeacherWage = new JLabel("Wage:");
        mainJPanel.add(L_TeacherWage);
        L_TeacherWage.setForeground(Color.white);
        L_TeacherWage.setFont(UIUtils.FONT_GENERAL_UI_SMALL);
        L_TeacherWage.setBounds(650,300,100,40);
        
        TF_TeacherWage = new mTextField();
        mainJPanel.add(TF_TeacherWage);
        TF_TeacherWage.setBounds(750,300,180,40);
        
        //Setting
        JLabel L_Settings= new JLabel("Settings:");
        mainJPanel.add(L_Settings);
        L_Settings.setForeground(Color.white);
        L_Settings.setFont(UIUtils.FONT_GENERAL_UI);
        L_Settings.setBounds(50,400,100,40);
        
        StrategyGroup = new ButtonGroup();
        RB_basicStrategy = new mRadioButton("Basic Assignment Strategy");
        RB_AISVMStrategy = new mRadioButton("AI SVM Strategy");
        RB_AIDTStrategy = new mRadioButton("AI DT Strategy");
        StrategyGroup.add(RB_basicStrategy);
        StrategyGroup.add(RB_AISVMStrategy);
        StrategyGroup.add(RB_AIDTStrategy);
        mainJPanel.add(RB_basicStrategy);
        mainJPanel.add(RB_AISVMStrategy);
        mainJPanel.add(RB_AIDTStrategy);
        RB_basicStrategy.setBounds(50,450,300,40);
        RB_AISVMStrategy.setBounds(50,500,300,40);
        RB_AIDTStrategy.setBounds(50,550,300,40);
        RB_basicStrategy.setSelected(true);
        
        //seperator
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        mainJPanel.add(separator1);
        separator1.setBounds(0, 230, 1080, 1);
        
        JSeparator separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.HORIZONTAL);
        separator3.setForeground(UIUtils.COLOR_OUTLINE);
        mainJPanel.add(separator3);
        separator3.setBounds(0, 370, 1080, 1);
        
  
        
        //Button
        addRegisterButton(mainJPanel);
        //addExecuteButton(mainJPanel);
        addBackButton(mainJPanel);
        
        //Table
       
        
        this.add(mainJPanel);
        this.pack();
        this.setVisible(false);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
    }
    
    private void simpleTable(String[] colnames, Object[][] data, JPanel p) {
    	mTable table = new mTable(colnames,data);
    	p.add(table);
    	table.setBounds(0, 420, 1080, 340);
    }

    private JPanel getMainJPanel() {
        this.setUndecorated(true);

        Dimension size = new Dimension(1080, 760);

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
    
    private void addRegisterButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_REGISTER,100,680,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                RegisterEventHandler();
            }
        });
    }

    private void addExecuteButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_EXECUTE,440,680,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ExecuteEventHandler();
            }
        });
    }

    private void addBackButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_BACK,780,680,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BackEventHandler();
            }
        });
    }
    
    private void RegisterEventHandler() {
        if(RB_registerStudent.isSelected()){
        	StudentRegisterController c = new StudentRegisterController(
        	TF_StudentName.getText(),
        	Integer.parseInt(TF_StudentAge.getText()),
        	TF_StudentParentName.getText(),
        	TF_StudentParentPhone.getText(),
        	TF_StudentGender.getText(),
        	TF_StudentAddress.getText(),
        	Integer.parseInt(TF_ReadTest.getText()),
        	Integer.parseInt(TF_SportTest.getText()),
        	Integer.parseInt(TF_MathTest.getText()));
        	if(c.register()) {
        		toaster.success("register student success");
        	}else{
        		toaster.error("register student failed");
        	}
        }else {
        	toaster.warn("register teacher");
        }
    }
    
    private void ExecuteEventHandler() {
        toaster.warn("execute");
    }
    
    private void BackEventHandler() {
        mhide();
        MainWindow.getInstance().mshow();
    }
    
    public void mshow() {
    	this.setVisible(true);
    }
    
    public void mhide() {
    	this.setVisible(false);
    }
}