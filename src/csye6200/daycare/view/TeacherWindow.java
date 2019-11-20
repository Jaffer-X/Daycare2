package csye6200.daycare.view;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

<<<<<<< HEAD
import csye6200.daycare.controller.TeacherModifyController;
=======
>>>>>>> 8e8420225b68031b4cee7578d88ab964965203fd
import csye6200.daycare.controller.TeacherRecordSearchController;
import csye6200.daycare.controller.TeacherSearchController;
import csye6200.daycare.lib.*;
import csye6200.daycare.main.UserCircumstances;

/*
 * author:jf
 */
public class TeacherWindow extends JFrame{
	private static final TeacherWindow instance = new TeacherWindow();
	private TeacherWindow() {
		start();
	}
	public static TeacherWindow getInstance() {
		return instance;
	}
	
	private ButtonGroup searchgroup;
	private mRadioButton RB_searchTeacher;
	private mRadioButton RB_searchRecord;
	private mCheckBox CB_classroom;
	private mCheckBox CB_group;
	private mCheckBox CB_feature;
	private mComboBox COB_teacher;
	private mTextField TF_teacher;
	private mTextField TF_record;
	private mComboBox COB_record;
	private Toaster toaster;
	private mTable table;
	private mScrollPane mSP;
	private String [] colTitles = {"ID","Name", "Age"};
	private Object[][] data= {new Object[]{1, 2, 3}, new Object[]{4, 5, 6},new Object[]{7, 8, 9}};
<<<<<<< HEAD
	private DefaultTableModel model;
	private int firstchangerow;
	private int lastchangerow;
	private int changecolumn;
	private int tablechangetype;
=======
>>>>>>> 8e8420225b68031b4cee7578d88ab964965203fd

	public void start() {
		JPanel mainpanel = getMainJPanel();
		
		searchgroup = new ButtonGroup();
		RB_searchTeacher = new mRadioButton("Search Teacher");
		RB_searchRecord = new mRadioButton("Search Record");
		searchgroup.add(RB_searchRecord);
		searchgroup.add(RB_searchTeacher);
		mainpanel.add(RB_searchRecord);
		mainpanel.add(RB_searchTeacher);
		RB_searchTeacher.setBounds(20,20,200,40);
		RB_searchRecord.setBounds(20,220,200,40);
		
		JLabel tkeyword = new JLabel("Keyword:");
		tkeyword.setForeground(Color.WHITE);
		tkeyword.setBounds(50,70,100,40);
		tkeyword.setFont(UIUtils.FONT_GENERAL_UI);
		mainpanel.add(tkeyword);
		COB_teacher = new mComboBox();
		COB_teacher.addItem("Name");
		COB_teacher.addItem("Age");
		COB_teacher.addItem("Wage");
		COB_teacher.setBounds(150,70,200,40);
		mainpanel.add(COB_teacher);
		TF_teacher = new mTextField();
		TF_teacher.setBounds(400,70,200,40);
		mainpanel.add(TF_teacher);
		CB_classroom = new mCheckBox("Classroom");
		CB_group = new mCheckBox("Group");
		CB_feature = new mCheckBox("Feature");
		CB_classroom.setBounds(50,150,150,44);
		CB_group.setBounds(300,150,150,44);
		CB_feature.setBounds(550,150,150,44);
		mainpanel.add(CB_classroom);
		mainpanel.add(CB_group);
		mainpanel.add(CB_feature);
		
		JLabel rkeyword = new JLabel("Keyword:");
		rkeyword.setForeground(Color.WHITE);
		rkeyword.setBounds(50,300,100,40);
		rkeyword.setFont(UIUtils.FONT_GENERAL_UI);
		mainpanel.add(rkeyword);
		COB_record = new mComboBox();
		COB_record.addItem("StudentID");
		COB_record.addItem("TeacherID");
		COB_record.addItem("ClassroomID");
		COB_record.addItem("GroupID");
		COB_record.addItem("Status");
		COB_record.addItem("Period");
		COB_record.addItem("Rank");
		COB_record.setBounds(150,300,200,40);
		mainpanel.add(COB_record);
		TF_record = new mTextField();
		TF_record.setBounds(400,300,200,40);
		mainpanel.add(TF_record);
		
		JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        mainpanel.add(separator1);
        separator1.setBounds(700, 0, 1, 420);
        
        JSeparator separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.HORIZONTAL);
        separator2.setForeground(UIUtils.COLOR_OUTLINE);
        mainpanel.add(separator2);
        separator2.setBounds(0, 420, 1080, 1);
        
        JSeparator separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.HORIZONTAL);
        separator3.setForeground(UIUtils.COLOR_OUTLINE);
        mainpanel.add(separator3);
        separator3.setBounds(0, 210, 700, 1);
		
        addSearchButton(mainpanel);
        addModifyButton(mainpanel);
        addImportButton(mainpanel);
        addExportButton(mainpanel);
        addBackButton(mainpanel);
		
        table = new mTable(colTitles,data);
        mSP = new mScrollPane();
        mSP.setViewportView(table);
        mSP.getViewport().setBackground(UIUtils.COLOR_BACKGROUND);
    	mainpanel.add(mSP);
    	mSP.setBounds(0, 420, 1080, 340);
<<<<<<< HEAD
    	
=======
>>>>>>> 8e8420225b68031b4cee7578d88ab964965203fd
        
        this.add(mainpanel);
        this.pack();
        this.setVisible(false);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainpanel);
	}
	
	private JPanel getMainJPanel() {
		this.setUndecorated(true);
		Dimension size = new Dimension(1080,720);
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
    
    
//    private void simpleTable(String[] colnames, Object[][] data, JPanel p) {
//    	mTable table = new mTable(colnames,data);
//    	p.add(table);
//    	table.setBounds(0, 420, 1080, 340);
//    }
    
    private void SearchEventHandler() {
    	if (UserCircumstances.getInstance().isDataBaseOP_asyn()) {
    		if (RB_searchTeacher.isSelected()) {
    			Runnable tSearch = new TeacherSearchController(COB_teacher.getSelectedItem().toString(),TF_teacher.getText());
    			new Thread(tSearch).start();
    			new Thread(() -> {
    				try {
    					int n = 0;
    					while (!((TeacherSearchController) tSearch).isSuccess()) {
    						Thread.sleep(200);
    						n++;
    						if (n > 100) {
    							toaster.error("search failed");
    							return;
    						}
    					}
    					toaster.success("search teacher success!");
<<<<<<< HEAD
    					 model = new DefaultTableModel(((TeacherSearchController) tSearch).getDataString(),((TeacherSearchController) tSearch).getTitle().toArray());
    					table.removeAll();
    					table.setModel(model);
    					model.addTableModelListener(new TableModelListener() {
    			    		public void tableChanged(TableModelEvent e) {
    			    		 firstchangerow = e.getFirstRow();
    			    		 lastchangerow = e.getLastRow();
    			    		 changecolumn = e.getColumn();
    			    		 tablechangetype = e.getType();
    			    		}
    			    	});
=======
    					DefaultTableModel model = new DefaultTableModel(((TeacherSearchController) tSearch).getDataString(),((TeacherSearchController) tSearch).getTitle().toArray());
    					table.removeAll();
    					table.setModel(model);
>>>>>>> 8e8420225b68031b4cee7578d88ab964965203fd
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    			}).start();
    		}else if(RB_searchRecord.isSelected()) {
    			Runnable rSearch = new TeacherRecordSearchController(COB_record.getSelectedItem().toString(),TF_record.getText());
    			new Thread(rSearch).start();
    			new Thread(() -> {
    				try {
    					int n = 0;
    					while(!((TeacherRecordSearchController) rSearch).isSuccess()) {
    						Thread.sleep(200);
    						n++;
    						if(n > 100) {
    							toaster.error("search teaching record failed!");
    							return;
    						}
    					}
    					toaster.success("search teahcing record success!");
    					DefaultTableModel rmodel = new DefaultTableModel(((TeacherRecordSearchController) rSearch).getDataString(),((TeacherRecordSearchController) rSearch).getTitle().toArray());
    					table.removeAll();
    					table.setModel(rmodel);
    				}catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    			}).start();
    		}
    	}else {
    		if(RB_searchTeacher.isSelected()) {
    			TeacherSearchController tsearch = new TeacherSearchController(COB_teacher.getSelectedItem().toString(),TF_teacher.getText());
    			if(tsearch.query()) {
    				toaster.success("search teacher success!");
    			}else {
    				toaster.error("search teacher failed!");
    			}
    		}else if(RB_searchRecord.isSelected()) {
    			TeacherRecordSearchController rsearch = new TeacherRecordSearchController(COB_record.getSelectedItem().toString(),TF_record.getText());
    			if(rsearch.query()) {
    				toaster.success("search teaching record success!");
    			}else {
    				toaster.error("search teaching record failed!");
    			}
    		}
    	}
    	refreshUITable();
    }
    private void refreshUITable() {
		// TODO Auto-generated method stub
		
	}
	private void ModifyEventHandler() {
<<<<<<< HEAD
		if (UserCircumstances.getInstance().isDataBaseOP_asyn()) {
			if(RB_searchTeacher.isSelected()) {
				for(int row = firstchangerow; row<= lastchangerow; row++) {
					Object idobj =  model.getValueAt(row, 0);
					Object nameobj =  model.getValueAt(row, 1);
					Object ageobj =  model.getValueAt(row, 2);
					Object wageobj =  model.getValueAt(row, 3);
					
					int id = 0;
                    try {
                        id = Integer.parseInt(""+ idobj);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                    String name = "";
                    try {
                        name = String.valueOf(nameobj);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                    int age = 0;
                    try {
                        age = Integer.parseInt(""+ ageobj);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                    int wage = 0;
                    try {
                        wage = Double.valueOf((String) wageobj).intValue();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
          
                    	TeacherModifyController tmodify = new TeacherModifyController(id,name,age,wage);
                   if(tmodify.update()) {
                	   toaster.success("update success!");
                   }else {
                	   toaster.error("update wrong!");
                   }
                    
//                    Runnable tModify = new TeacherModifyController(id,name,age,wage);
//                    new Thread(tModify).start();
//        			new Thread(() -> {
//        				try {
//        					int n = 0;
//        					while (!((TeacherModifyController) tModify).isSuccess()) {
//        						Thread.sleep(200);
//        						n++;
//        						if (n > 100) {
//        							toaster.error("modify failed");
//        							return;
//        						}
//        					}
//      
//       					table.removeAll();
//       					table.setModel(model);
//       				} catch (InterruptedException e) {
//       					e.printStackTrace();
//       				}
//       			}).start();
				}//toaster.success("modify teacher success!");
				
			}
			
		}
       // toaster.warn("modify");
=======
        toaster.warn("modify");
>>>>>>> 8e8420225b68031b4cee7578d88ab964965203fd
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
