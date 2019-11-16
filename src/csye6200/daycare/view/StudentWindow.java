package csye6200.daycare.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import csye6200.daycare.lib.*;
import csye6200.daycare.model.Person;
import csye6200.daycare.model.Student;

/*
 * author:fc
 */
public class StudentWindow extends JFrame{
	private static final StudentWindow instance = new StudentWindow();
	private StudentWindow() {start();}
	public static StudentWindow getInstance() {
		return instance;
	}
	
	private Toaster toaster;
	
    public void start() {
        JPanel mainJPanel = getMainJPanel();
        
        //search group
        ButtonGroup searchGroup = new ButtonGroup();
        mRadioButton RB_searchTeacher = new mRadioButton("Search Teacher");
        mRadioButton RB_searchRecord = new mRadioButton("Search Record");
        searchGroup.add(RB_searchTeacher);
        searchGroup.add(RB_searchRecord);
        mainJPanel.add(RB_searchTeacher);
        mainJPanel.add(RB_searchRecord);
        RB_searchTeacher.setBounds(20,20,200,40);
        RB_searchRecord.setBounds(20,220,200,40);
        
        //student Area
        JLabel L_keywordS = new JLabel("Keyword:");
        mainJPanel.add(L_keywordS);
        L_keywordS.setForeground(Color.white);
        L_keywordS.setFont(UIUtils.FONT_GENERAL_UI);
        L_keywordS.setBounds(50,70,100,40);
        
        mComboBox CB_Student=new mComboBox();    
        CB_Student.addItem("Name");    
        CB_Student.addItem("Age");
        CB_Student.addItem("ParentName");
        CB_Student.addItem("ParentPhone");
        CB_Student.addItem("Address");
        mainJPanel.add(CB_Student);
        CB_Student.setBounds(150,70,200,40);
        
        mTextField TF_Student = new mTextField();
        mainJPanel.add(TF_Student);
        TF_Student.setBounds(400,70,200,40);
        
        ButtonGroup studentGroup = new ButtonGroup();
        mRadioButton RB_studentBasic = new mRadioButton("Basic");
        mRadioButton RB_studentImmunization = new mRadioButton("Immunization");
        studentGroup.add(RB_studentBasic);
        studentGroup.add(RB_studentImmunization);
        mainJPanel.add(RB_studentBasic);
        mainJPanel.add(RB_studentImmunization);
        RB_studentBasic.setBounds(50,120,200,40);
        RB_studentImmunization.setBounds(50,170,200,40);
        
        mCheckBox CKB_student1 = new mCheckBox("Teacher"); 
        mCheckBox CKB_student2 = new mCheckBox("Classroom");
        mCheckBox CKB_student3 = new mCheckBox("Group"); 
        mainJPanel.add(CKB_student1); 
        mainJPanel.add(CKB_student2); 
        mainJPanel.add(CKB_student3); 
        CKB_student1.setBounds(250,120,150,44);
        CKB_student2.setBounds(400,120,150,44);
        CKB_student3.setBounds(550,120,150,44);
        
        //Teacher Area
        JLabel L_keywordR = new JLabel("Keyword:");
        mainJPanel.add(L_keywordR);
        L_keywordR.setForeground(Color.white);
        L_keywordR.setFont(UIUtils.FONT_GENERAL_UI);
        L_keywordR.setBounds(50,270,100,40);
        
        mComboBox CB_Teacher=new mComboBox();    
        CB_Teacher.addItem("Name");    
        CB_Teacher.addItem("Age");
        CB_Teacher.addItem("Wage");
        mainJPanel.add(CB_Teacher);
        CB_Teacher.setBounds(150,270,200,40);
        
        mTextField TF_Record = new mTextField();
        mainJPanel.add(TF_Record);
        TF_Record.setBounds(400,270,200,40);
        
        ButtonGroup recordGroup = new ButtonGroup();
        mRadioButton RB_teachingRecord = new mRadioButton("Teaching");
        mRadioButton RB_immunizationRecord = new mRadioButton("Immunization");
        recordGroup.add(RB_teachingRecord);
        recordGroup.add(RB_immunizationRecord);
        mainJPanel.add(RB_teachingRecord);
        mainJPanel.add(RB_immunizationRecord);
        RB_teachingRecord.setBounds(50,320,200,40);
        RB_immunizationRecord.setBounds(50,370,200,40);
        
        mCheckBox CKB_record1 = new mCheckBox("All"); 
        mCheckBox CKB_record2 = new mCheckBox("Upcomming");
        mCheckBox CKB_record3 = new mCheckBox("Overdue"); 
        mainJPanel.add(CKB_record1); 
        mainJPanel.add(CKB_record2); 
        mainJPanel.add(CKB_record3); 
        CKB_record1.setBounds(250,320,150,44);
        CKB_record2.setBounds(400,320,150,44);
        CKB_record3.setBounds(550,320,150,44);
        
        //seperator
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        mainJPanel.add(separator1);
        separator1.setBounds(700, 0, 1, 420);
        
        JSeparator separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.HORIZONTAL);
        separator2.setForeground(UIUtils.COLOR_OUTLINE);
        mainJPanel.add(separator2);
        separator2.setBounds(0, 420, 1080, 1);
        
        JSeparator separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.HORIZONTAL);
        separator3.setForeground(UIUtils.COLOR_OUTLINE);
        mainJPanel.add(separator3);
        separator3.setBounds(0, 210, 700, 1);
        
        //Button
        addSearchButton(mainJPanel);
        addModifyButton(mainJPanel);
        addImportButton(mainJPanel);
        addExportButton(mainJPanel);
        addBackButton(mainJPanel);
        
        //Table
        
        String [] colTitles = {"ID","Name", "Age"};
        Object[][] data= {new Object[]{1, 2, 3}, new Object[]{4, 5, 6},new Object[]{7, 8, 9}};
        simpleTable(colTitles,data,mainJPanel);
        
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
    
    private void addSearchButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_SEARCH,790,60,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SearchEventHandler();
            }
        });
    }

    private void addModifyButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_MODIFY,790,120,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ModifyEventHandler();
            }
        });
    }

    private void addImportButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_IMPORT,790,180,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ImportEventHandler();
            }
        });
    }
    
    private void addExportButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_EXPORT,790,240,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ExportEventHandler();
            }
        });
    }
    
    private void addBackButton(JPanel panel1) {
    	JLabel tmp = createDefaultButton(panel1,UIUtils.BUTTON_TEXT_BACK,790,300,200,40);
    	tmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BackEventHandler();
            }
        });
    }
    
    private void SearchEventHandler() {
        toaster.warn("search");
    }
    private void ModifyEventHandler() {
        toaster.warn("modify");
    }
    private void ImportEventHandler() {
        toaster.warn("import");
    }
    private void ExportEventHandler() {
        toaster.warn("export");
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
