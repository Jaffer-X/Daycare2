package csye6200.daycare.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import csye6200.daycare.controller.ModifyController;
import csye6200.daycare.controller.StudentRegisterController;
import csye6200.daycare.controller.StudentSearchController;
import csye6200.daycare.controller.StudentSearchController.SEARCH_STUDENT;
import csye6200.daycare.lib.*;
import csye6200.daycare.main.UserCircumstances;
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
	
	private JPanel mainJPanel;
	private Toaster toaster;
	private ButtonGroup searchGroup;
	private mRadioButton RB_searchStudent;
	private mRadioButton RB_searchRecord;
	private mComboBox CB_Student;
	private mTextField TF_Student;
	private ButtonGroup studentGroup;
	private mRadioButton RB_studentBasic;
	private mRadioButton RB_studentImmunization;
	private mCheckBox CKB_student1;
	private mCheckBox CKB_student2;
	private mCheckBox CKB_student3;
	private mComboBox CB_Teacher;
	private mTextField TF_Record;
	private ButtonGroup recordGroup;
	private mRadioButton RB_teachingRecord;
	private mRadioButton RB_immunizationRecord;
	private mCheckBox CKB_record1;
	private mCheckBox CKB_record2;
	private mCheckBox CKB_record3;
	private mTable table;
	private mScrollPane mSP;
	
	private int firstchangerow;
	private int lastchangerow;
	private int changecolumn;
	private int tablechangetype;
	private DefaultTableModel smodel;
	
	private String [] colTitles = {"ID","Name", "Age"};
    private Object[][] data= {new Object[]{1, 2, 3}, new Object[]{4, 5, 6},new Object[]{7, 8, 9}};
	
    public void start() {
        mainJPanel = getMainJPanel();
        
        //search group
        searchGroup = new ButtonGroup();
        RB_searchStudent = new mRadioButton("Search Student");
        RB_searchRecord = new mRadioButton("Search Record");
        searchGroup.add(RB_searchStudent);
        searchGroup.add(RB_searchRecord);
        mainJPanel.add(RB_searchStudent);
        mainJPanel.add(RB_searchRecord);
        RB_searchStudent.setBounds(20,20,200,40);
        RB_searchRecord.setBounds(20,220,200,40);
        RB_searchStudent.setSelected(true);
        
        //student Area
        JLabel L_keywordS = new JLabel("Keyword:");
        mainJPanel.add(L_keywordS);
        L_keywordS.setForeground(Color.white);
        L_keywordS.setFont(UIUtils.FONT_GENERAL_UI);
        L_keywordS.setBounds(50,70,100,40);
        
        CB_Student=new mComboBox();    
        CB_Student.addItem("Name");    
        CB_Student.addItem("Age");
        CB_Student.addItem("parentName");
        CB_Student.addItem("parentPhone");
        CB_Student.addItem("Address");
        mainJPanel.add(CB_Student);
        CB_Student.setBounds(150,70,200,40);
        
        TF_Student = new mTextField();
        mainJPanel.add(TF_Student);
        TF_Student.setBounds(400,70,200,40);
        
        studentGroup = new ButtonGroup();
        RB_studentBasic = new mRadioButton("Basic");
        RB_studentImmunization = new mRadioButton("Immunization");
        studentGroup.add(RB_studentBasic);
        studentGroup.add(RB_studentImmunization);
        mainJPanel.add(RB_studentBasic);
        mainJPanel.add(RB_studentImmunization);
        RB_studentBasic.setBounds(50,120,200,40);
        RB_studentImmunization.setBounds(50,170,200,40);
        RB_studentBasic.setSelected(true);
        
        CKB_student1 = new mCheckBox("Teacher"); 
        CKB_student2 = new mCheckBox("Classroom");
        CKB_student3 = new mCheckBox("Group"); 
        mainJPanel.add(CKB_student1); 
        mainJPanel.add(CKB_student2); 
        mainJPanel.add(CKB_student3); 
        CKB_student1.setBounds(250,120,150,44);
        CKB_student2.setBounds(400,120,150,44);
        CKB_student3.setBounds(550,120,150,44);
        
        //Record Area
        JLabel L_keywordR = new JLabel("Keyword:");
        mainJPanel.add(L_keywordR);
        L_keywordR.setForeground(Color.white);
        L_keywordR.setFont(UIUtils.FONT_GENERAL_UI);
        L_keywordR.setBounds(50,270,100,40);
        
        CB_Teacher=new mComboBox();    
        CB_Teacher.addItem("Name");    
        CB_Teacher.addItem("Age");
        mainJPanel.add(CB_Teacher);
        CB_Teacher.setBounds(150,270,200,40);
        
        TF_Record = new mTextField();
        mainJPanel.add(TF_Record);
        TF_Record.setBounds(400,270,200,40);
        
        recordGroup = new ButtonGroup();
        RB_teachingRecord = new mRadioButton("Teaching");
        RB_immunizationRecord = new mRadioButton("Immunization");
        recordGroup.add(RB_teachingRecord);
        recordGroup.add(RB_immunizationRecord);
        mainJPanel.add(RB_teachingRecord);
        mainJPanel.add(RB_immunizationRecord);
        RB_teachingRecord.setBounds(50,320,200,40);
        RB_immunizationRecord.setBounds(50,370,200,40);
        RB_teachingRecord.setSelected(true);
        
        CKB_record1 = new mCheckBox("All"); 
        CKB_record2 = new mCheckBox("Upcomming");
        CKB_record3 = new mCheckBox("Overdue"); 
        mainJPanel.add(CKB_record1); 
        mainJPanel.add(CKB_record2); 
        mainJPanel.add(CKB_record3); 
        CKB_record1.setBounds(250,370,150,44);
        CKB_record2.setBounds(400,370,150,44);
        CKB_record3.setBounds(550,370,150,44);
        
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
    	table = new mTable(colTitles,data);
        mSP = new mScrollPane();
        mSP.setViewportView(table);
        mSP.getViewport().setBackground(UIUtils.COLOR_BACKGROUND);
    	mainJPanel.add(mSP);
    	mSP.setBounds(0, 420, 1080, 340);
        
        this.add(mainJPanel);
        this.pack();
        this.setVisible(false);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
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
    
    private void refreshUITable() {
    	
    }
    
    private void SearchEventHandler() {
    	StudentSearchController.SEARCH_STUDENT current = null;
		if (UserCircumstances.getInstance().isDataBaseOP_asyn()) {
			if (RB_searchStudent.isSelected()) {
				if (RB_studentBasic.isSelected())
					current = SEARCH_STUDENT.STUDENT_BASIC;
				else if (RB_studentImmunization.isSelected())
					current = SEARCH_STUDENT.STUDENT_IMMUNIZATION;
				Runnable sSearch = new StudentSearchController(CB_Student.getSelectedItem().toString(),
						TF_Student.getText(), current);
				new Thread(sSearch).start();
				new Thread(() -> {
					try {
						int n = 0;
						while (!((StudentSearchController) sSearch).isSuccess()) {
							Thread.sleep(200);
							n++;
							if (n > 100) {
								toaster.error("search failed");
								return;
							}
						}
						toaster.success("search success");
//						System.out.println(((StudentSearchController) sSearch).getContent().toString());
//						System.out.println(((StudentSearchController) sSearch).getTitle().toString());
				        smodel = new DefaultTableModel(((StudentSearchController) sSearch).getDataString(),((StudentSearchController) sSearch).getTitle().toArray());
				    	table.removeAll();
				    	table.setModel(smodel);
				    	smodel.addTableModelListener(new TableModelListener() {
    			    		public void tableChanged(TableModelEvent e) {
    			    		 firstchangerow = e.getFirstRow();
    			    		 lastchangerow = e.getLastRow();
    			    		 changecolumn = e.getColumn();
    			    		 tablechangetype = e.getType();
    			    		}
    			    	});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();
			} else if (RB_searchRecord.isSelected()) {
//				if (RB_teachingRecord.isSelected())
//					current = SEARCH.RECORD_TEACHING;
//				else if (RB_immunizationRecord.isSelected())
//					current = SEARCH.RECORD_IMMUNIZATION;
//				Runnable sSearch = new StudentSearchController(CB_Teacher.getSelectedItem().toString(),
//						TF_Record.getText(), current);
//				new Thread(sSearch).start();
//				new Thread(() -> {
//					try {
//						int n = 0;
//						while (!((StudentSearchController) sSearch).isSuccess()) {
//							Thread.sleep(200);
//							n++;
//							if (n > 50) {
//								toaster.error("search failed");
//								return;
//							}
//						}
//						toaster.success("search success");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}).start();
			}
		}
    	else{//sync method
			if (RB_searchStudent.isSelected()) {
				if (RB_studentBasic.isSelected())
					current = SEARCH_STUDENT.STUDENT_BASIC;
				else if (RB_studentImmunization.isSelected())
					current = SEARCH_STUDENT.STUDENT_IMMUNIZATION;
				StudentSearchController s = new StudentSearchController(CB_Student.getSelectedItem().toString(),
						TF_Student.getText(), current);
    			if(s.query()) {
    				toaster.success("search success");
    			}else{
    				toaster.error("search failed");
    			}
			} else if (RB_searchRecord.isSelected()) {
//				if (RB_teachingRecord.isSelected())
//					current = SEARCH.RECORD_TEACHING;
//				else if (RB_immunizationRecord.isSelected())
//					current = SEARCH.RECORD_IMMUNIZATION;
//				StudentSearchController s = new StudentSearchController(CB_Teacher.getSelectedItem().toString(),
//						TF_Record.getText(), current);
//    			if(s.query()) {
//    				toaster.success("search success");
//    			}else{
//    				toaster.error("search failed");
//    			}
			}			
    	}
		refreshUITable();
    }

    private void ModifyEventHandler() {
    	if (UserCircumstances.getInstance().isDataBaseOP_asyn()) {
    		if(RB_searchStudent.isSelected()) {
    			for(int row = firstchangerow; row<= lastchangerow; row++) {
    				String updatekeywords = smodel.getColumnName(changecolumn);
    				Object updatedata = smodel.getValueAt(row, changecolumn);
    				int updateid = (int) smodel.getValueAt(row, 0);
    				ModifyController tmodify = new ModifyController(updatekeywords,updatedata,updateid,3);
    				if(tmodify.update()) {
    					toaster.success("update success!");
    				}else {
    					toaster.error("update wrong!");
    				}
    			}
    		}
    	}
    	
//    	String [] colTitles2 = {"ID","Name", "Age","ff","gg"};
//        Object[][] data2= {new Object[]{1, 2, 3}, new Object[]{4, 5, 6},new Object[]{7, 8, 9},new Object[]{7, 8, 9},new Object[]{7, 8, 9}};
//        DefaultTableModel model = new DefaultTableModel(data2, colTitles2);
//    	table.removeAll();
//    	table.setModel(model);
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
