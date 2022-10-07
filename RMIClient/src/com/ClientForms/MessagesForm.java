package com.ClientForms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import com.MyButton;
import com.MyImage;
import com.Designer.ChoiceDialog;
import com.Designer.ComponentDesginer;
import com.Designer.PersonPanel;
import com.Resource.ConnectState;
import com.Resource.FileTest;
import com.Resource.MyColor;
import com.Resource.MyFont;
import com.Resource.Values;
import com.ServerAPI.ClientInterface;
import com.ServerAPI.FileType;
import com.ServerAPI.Message;
import com.ServerAPI.Person;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.MyLabel;
import com.Client.MainClient;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessagesForm extends JFrame {

	private JPanel contentPane;
	public JTextField txt_Message;
	private boolean directionRight = false;
	private static JPanel panelCenter;
	private int autoAlignY = 10;
	private Message myMessage;
	public static ArrayList<JButton> ImogyList = new ArrayList<JButton>();
	private Person owner;
	public Person myPerson;
	private Image ownerImage;
	protected int i = 0;
	protected File fImogy = null;
	 
	
 	public MessagesForm(Person person,Person own,Image image) throws RemoteException {
 		 addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					MainClient.getServer().logOut(person.getID());
				} catch (RemoteException e1) {
					System.out.println("Logout exception :"+e1.toString());	
				}
			}
		});
 		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  		myPerson = person;
 		owner = own;
 		ownerImage = image;
  		setIconImage(ownerImage);
		setTitle(owner.getName());
 		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(350, 70, 451, 541);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new LineBorder(Color.GRAY));
		scrollPane.setAutoscrolls(true);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 67, 417, 368);
		contentPane.add(scrollPane);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(SystemColor.info);
		scrollPane.setViewportView(panelCenter);
		panelCenter.setBounds(10, 67, 417, 368);
		panelCenter.setPreferredSize(new Dimension(400, 368));
		panelCenter.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCenter.setLayout(null);
		
		JPanel panel_Bottom = new JPanel();
		panel_Bottom.setBackground(SystemColor.info);
		panel_Bottom.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		panel_Bottom.setBounds(10, 439, 417, 54);
		contentPane.add(panel_Bottom);
		panel_Bottom.setLayout(null);
		
		txt_Message = ComponentDesginer.getTextField(252, 40, SystemColor.window);
		txt_Message.setAlignmentX(Component.LEFT_ALIGNMENT);
		txt_Message.setBounds(105, 7, 252, 40);
		txt_Message.setBorder(new LineBorder(new Color(0,0,0)));
		txt_Message.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					MainClient.getServer().sendState(ConnectState.WRITTING,owner.getID(),myPerson.getID());
				} catch (RemoteException e1) {
 					e1.printStackTrace();
				}
			}
		});
		panel_Bottom.add(txt_Message);
		txt_Message.setColumns(10);
		
		JButton image_send = ComponentDesginer.getImageButton("icons\\ic_cam_back.png", 40, 40, 50);
		image_send.setBackground(MyColor.BackgroundButtonColor);
 		image_send.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				String text = txt_Message.getText();
 				if(text.length() == 0) {
 					return;
 				}
 				else {
    				setRightDirection(false);
   					try {
   						Message msg = new Message();
   						msg.setMessage(text);
   						msg.setDate(getStringDate());
   						msg.setSenderId(owner.getID());
   						msg.isFile(false);
   						msg.setType(FileType.MESSAGE);
						MainClient.getServer().sendMessage(msg, myPerson.getID());
  	  					showMessage(text, getStringDate());
 					} catch (RemoteException e1) {
 						System.out.println(e1.toString());
					}
   				}
 				txt_Message.setText("");
  			}
 		});
		image_send.setBounds(367, 7, 40, 40);
    	panel_Bottom.add(image_send);
 		
   		JButton image_File = ComponentDesginer.getImageButton("icons\\iconSendMedia.png", 40, 40, 50);
   		image_File.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				try {
 				JFileChooser fileChooser = new JFileChooser();
 				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files","pdf","png","jpg","svg","txt","mp3","mp4","ppt","doc","docx");
 				fileChooser.setFileFilter(filter);
  				int result = fileChooser.showDialog(panelCenter, "Send File");
 				if(result == JFileChooser.APPROVE_OPTION) {
 				File file = fileChooser.getSelectedFile();
 				String filename = file.getName();
				String fileExt = FileTest.getExtentionOf(filename);
 			    File newFile = new File(FileTest.getFilePath(fileExt)+owner.getID()+filename);
  					if(newFile.createNewFile()) {
 							FileTest.copyFile(file, newFile);
					}
  					myMessage = new Message();
  					if(FileTest.getFileType(fileExt) == FileType.IMAGE) {
   						showImage(newFile, getStringDate());
  						myMessage.setType(FileType.IMAGE);
   					}
  					else {
  						myMessage.setType(FileTest.getFileType(fileExt));
						showFile(FileTest.getshownImagePath(fileExt),getStringDate(),newFile.getPath());
  					}
    				
  				    myMessage.setDate(getStringDate());
 	  				myMessage.setFileName(newFile.getPath());
	  				myMessage.setSenderId(owner.getID());
	  				myMessage.isFile(true);
 	  				myMessage.setFile(newFile);
  	  				MainClient.getServer().sendMessage(myMessage, myPerson.getID());
 				  }
 				}catch(Exception ex) {
 					JOptionPane.showMessageDialog(null, "send File exception:"+ex.getMessage());
 				}
  			}});
		image_File.setBounds(55, 7, 40, 40);
		Image image2;
		try {
			image2 = ImageIO.read(new File("icons\\iconSendMedia.png")).getScaledInstance( 30,30,Image.SCALE_AREA_AVERAGING);
			image_File.setIcon(new ImageIcon(image2));
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
  		panel_Bottom.add(image_File);
  		
  		JButton image_Imogy = ComponentDesginer.getImageButton("icons\\iconSendImogy.png", 40, 40, 50);
 		image_Imogy.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				showImogies();
  				}
  			 });
 		image_Imogy.setBounds(10, 7, 40, 40);
  		panel_Bottom.add(image_Imogy);
  		try {
	  		 ByteArrayInputStream in = new ByteArrayInputStream(myPerson.getImage());
			 BufferedImage bimg = ImageIO.read(in);
			 Image img = new ImageIcon(bimg).getImage();  
	 		 JPanel panel_Top = new PersonPanel(img,myPerson,417).getPerson_Panel();
	 		 panel_Top.setBackground(MyColor.BackgroundPanelColor);
	 		 contentPane.add(panel_Top);
  		}catch(Exception e) {
  			JOptionPane.showMessageDialog(this, e.getMessage());
  		}
  		 
  		setVisible(true);
   		initPerson(owner);
   		initAllMessage(myPerson);
 	}
	 
	private void initAllMessage(Person c) throws RemoteException {
 		
			ArrayList<Message> msgs = c.getMessages();
			for(Message m : msgs){
				try {
					if(m == null ) {
						continue;
					 } 
 					   setRightDirection(m.getSenderId() == myPerson.getID());
 							 switch(m.getType()) {
							 case FileType.MESSAGE: showMessage(m.getMessage(), m.getDate()); break;
 								 case FileType.IMAGE:
 									
 									 showImage(new File(m.getFileName()),m.getDate()); break;
 								 case FileType.DOCUMENT:
 								 case FileType.VIDEO:
 									 String ext = FileTest.getExtentionOf(m.getFileName());
							    	 showFile(FileTest.getshownImagePath(ext),m.getDate(),m.getFileName());break;
 								 case FileType.IMOGY:
 									 showImogy(m.getFileName()); break;
 									 default :
 										showMessage(m.getMessage(), m.getDate()); break;
 								 }
   				} catch (IOException e) {
 					e.printStackTrace();
				}
			}
		} 

 	private void initPerson(Person c) {
		if(c!= null) {
  			try {
 				ByteArrayInputStream in = new ByteArrayInputStream(c.getImage());
				BufferedImage bimg;
	 			bimg = ImageIO.read(in);
	 			Image img = new ImageIcon(bimg).getImage();
	 			JPanel personPane = new PersonPanel(img,owner,417).getPerson_Panel();
 	 			personPane.setPreferredSize(new Dimension(397, 59));
	 			personPane.setBackground(SystemColor.green);
	 			JButton btn_GoBack = ComponentDesginer.getImageJButton("icons\\ic_forward_message.png", 40, 40, 50);
 	 	 		btn_GoBack.addActionListener(new ActionListener() {
	 	 			public void actionPerformed(ActionEvent e) {
 	 	 					LoginForm.personMenuForm.setVisible(true);
  	 	  					dispose();
	 	 			}
 	 	 			}
	 	 		 );
 	 	 		btn_GoBack.setBounds(370, 7, 40, 40);
	 	 		btn_GoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 	 		btn_GoBack.setBackground(SystemColor.green);
	 	 		btn_GoBack.setForeground(SystemColor.green);
	 	 		btn_GoBack.setContentAreaFilled(false);
 	 	 		personPane.add(btn_GoBack);
 	 	 		JButton removeAllMsgs = ComponentDesginer.getImageJButton("icons\\delete.png", 40, 40, 50);
	 	 		removeAllMsgs.addActionListener(new ActionListener() {
	 	 			public void actionPerformed(ActionEvent e) {
	 	 				try {
	 	 					if(JOptionPane.showConfirmDialog(null,"Do you want really remove all message ?") ==JOptionPane.YES_OPTION){
 	 	 						Component [] components = panelCenter.getComponents();
								for(Component c:components )
									panelCenter.remove(c);
								
								panelCenter.setBounds(10, 67, 417, 368);
								panelCenter.setPreferredSize(new Dimension(400, 368));
								panelCenter.revalidate();
								panelCenter.repaint();
	 	 						boolean f = MainClient.getServer().removeAllMessages(owner.getID(), myPerson.getID());
	 	 						if(f) {
	 	 							JOptionPane.showMessageDialog(null, "deleted successfully ^_^ ");
	 	 							autoAlignY = 10;
	 	 						}
	 	 							
	 	 					}
						} catch (RemoteException e1) {
 							e1.printStackTrace();
						}
 	 					}
 	 	 			}
	 	 		 );
	 	 		removeAllMsgs.setBounds(327, 7, 40, 40);
	 	 		removeAllMsgs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 	 		removeAllMsgs.setBackground(SystemColor.green);
	 	 		removeAllMsgs.setForeground(SystemColor.green);
	 	 		removeAllMsgs.setContentAreaFilled(false);
 	 	 		personPane.add(removeAllMsgs);
 	 	 		removeAllMsgs.revalidate();
	 	 		personPane.repaint();
	 			contentPane.add(personPane);
				} catch (Exception e1) {
	 				e1.printStackTrace();
				}
		}
	 
}

 	public void showMessage(String text ,String date) {
		
 		JPanel panel = ComponentDesginer.getJPanel(200, 60, 15);
		panel.setBackground(panelCenter.getBackground());
		panel.setLayout(new BorderLayout());
    	JLabel lbl1 = new JLabel(" "+text);
		lbl1.setFont(MyFont.MESSAGE_FONT);
		lbl1.setHorizontalTextPosition(SwingConstants.RIGHT);
		JLabel lbl2 = new JLabel(  date +"  ");
 		panel.add(lbl1,BorderLayout.CENTER);
		panel.add(lbl2,BorderLayout.SOUTH);
		if(directionRight) {
			int alignX = 400 - 200 - 10;
			panel.setPreferredSize(new Dimension(200,60));
			panel.setBounds(alignX,autoAlignY,200,60);
  		}
		else {
 			panel.setBounds(10,autoAlignY,200,60);
			panel.setPreferredSize(new Dimension(200,60));
 			}
		autoAlignY += 70;
		if(autoAlignY >= panelCenter.getHeight()) {
			panelCenter.setPreferredSize(new Dimension(400,autoAlignY));
			panelCenter.setBounds(0, 0, 400, autoAlignY);
		}
		panelCenter.add(panel);
		panelCenter.revalidate();
		panelCenter.repaint();
 }
	
    public void showImage(File file ,String date) throws IOException {
		 
			JPanel panel = ComponentDesginer.getJPanel(150, 150,13);
			panel.setSize(150, 150);
			panel.setBackground(panelCenter.getBackground());
			panel.setLayout(new BorderLayout());
			 Image image = ImageIO.read(file).getScaledInstance(panel.getWidth(),panel.getHeight(),Image.SCALE_AREA_AVERAGING);
			panel.addMouseListener(new MouseAdapter() {
				@Override
	 			public void mouseClicked(MouseEvent e) {
 					try {
 		 				ChoiceDialog dailog = new ChoiceDialog(image,ownerImage,owner.getName());
  					} catch (IOException e1) {
 						System.out.println("panel :"+e1.toString());
					}
	 			}
			});
 	 		JLabel lbl1 = ComponentDesginer.getImageMyLabel(image, 150, 150, 13);
  			JLabel lbl2 = new JLabel(date);
	 		panel.add(lbl1,BorderLayout.CENTER);
			panel.add(lbl2,BorderLayout.SOUTH);
			
			if(directionRight) {
				int alignX = 400 - 150 -10;
				panel.setBounds(alignX ,autoAlignY,150,150);
				panel.setPreferredSize(new Dimension(150,150));
 			}
			else {
				panel.setBounds(10,autoAlignY,150,150);
				panel.setPreferredSize(new Dimension(150,150));
			}
		autoAlignY += 155;
		if(autoAlignY >= panelCenter.getHeight()) {
			panelCenter.setPreferredSize(new Dimension(400,autoAlignY));
			panelCenter.setBounds(0, 0, 400, autoAlignY);
		}
		    panelCenter.add(panel);
			panelCenter.revalidate();
			panelCenter.repaint();
    }
	
    public void showFile(String showImagePath ,String date, String filename) throws IOException {
 		
    	JPanel panel = ComponentDesginer.getJPanel(150, 150,13);
		panel.setBackground(panelCenter.getBackground());
		panel.setLayout(new BorderLayout());
		panel.addMouseListener(new MouseAdapter() {
			@Override
 			public void mouseClicked(MouseEvent e) {
  					   try {
						Desktop.getDesktop().open(new File(filename));
					} catch (IOException e1) {
 						System.out.println("Open files Exception :"+e1.toString());
					}
  			}
		});
	 	JLabel lbl1 = ComponentDesginer.getImageMyLabel(showImagePath, 150, 150, 13);
	 	lbl1.setBorder(new EmptyBorder(0,0,0,0));
 			JLabel lbl2 = new JLabel( date);
	  		panel.add(lbl1,BorderLayout.CENTER);
			panel.add(lbl2,BorderLayout.SOUTH);
		
		if(directionRight) {
			int alignX = 400 - 150 -10;
			panel.setBounds(alignX ,autoAlignY,150,150);
			panel.setPreferredSize(new Dimension(150,150));
			}
		else {
			panel.setBounds(10,autoAlignY,150,150);
			panel.setPreferredSize(new Dimension(150,150));
		}
	autoAlignY += 155;
	if(autoAlignY >= panelCenter.getHeight()) {
		panelCenter.setPreferredSize(new Dimension(400,autoAlignY));
		panelCenter.setBounds(0, 0, 400, autoAlignY);
	}
	    panelCenter.add(panel);
		panelCenter.revalidate();
		panelCenter.repaint();
}

    public void showImogy(String ImogyName) {
    	   
     	    JButton btn = ComponentDesginer.getImageButton(ImogyName, 40, 40, 50);
 			if(directionRight) {
				int alignX = 400 - 40 -10;
				btn.setBounds(alignX ,autoAlignY,40,40);
				btn.setPreferredSize(new Dimension(40,40));
			}
			else {
				btn.setBounds(10,autoAlignY,40,40);
				btn.setPreferredSize(new Dimension(40,40));
			}
			autoAlignY += 44;
			
			if(autoAlignY >= panelCenter.getHeight()) {
				panelCenter.setPreferredSize(new Dimension(panelCenter.getWidth(),autoAlignY));
				panelCenter.setBounds(0, 0, panelCenter.getWidth(), autoAlignY);
			}
			    panelCenter.add(btn);
				panelCenter.revalidate();
				panelCenter.repaint();
    		}
      
 
	public void setRightDirection(Boolean b) {
		directionRight = b;
	}
	
	private void showImogies() {
		Image image;
		ChoiceDialog dialog = null;
		try {
			image = ImageIO.read(new File("icons\\iconSendImogy.png"));
			dialog = new ChoiceDialog(this,image,"Imogies",true);
 			dialog.setBounds(360, 255, dialog.getWidth(), dialog.getHeight());
			
		} catch (IOException e) {
 			e.printStackTrace();
		}
			String [] ImogyFileNames = new String[13];
				
  			for(int i = 0 ;i<ImogyFileNames.length;i++) {
 				ImogyFileNames[i] ="imogy\\i"+(i+1)+".png";
 				final Integer innerI = new Integer(i); 
 				JButton btn = ComponentDesginer.getImageButton(ImogyFileNames[i], 35, 35, 50);
 				btn.addActionListener(new ActionListener() {
 	  				 public void actionPerformed(ActionEvent e) {
 	  					setRightDirection(false);
  	   					showImogy(ImogyFileNames[innerI]);
 	 					try {
 	 					Message messag = new Message();
 	 				    messag.setDate(getStringDate());
 	 				    messag.setFileName(ImogyFileNames[innerI]);
 	 				    messag.setSenderId(owner.getID());
 	 	  				messag.isFile(true);
 	 	  				messag.setType(FileType.IMOGY);
 	 	  				messag.setFile(null);
 	 	  				MainClient.getServer().sendMessage(messag, myPerson.getID());
 	 				}catch(Exception e1) {
 	 					JOptionPane.showMessageDialog(null,"Error in send Imogy"+e1.toString());
 	 				}
 				 }
 	 			});
  				ImogyList.add(btn);
				dialog.addComponents(btn);
 			} 
// 			for(imogyCounter = 0; imogyCounter < ImogyFileNames.length - 1; imogyCounter++) {
// 				ImogyList.get(imogyCounter)
// 			}
// 			imogyCounter = 0;
//			for(JButton b: ImogyList){
//				b.addActionListener(new ActionListener() {
// 					@Override
//					public void actionPerformed(ActionEvent e) {
//						showImogy(ImogyFileNames[imogyCounter]);
//						try {
//		 					Message messag = new Message();
//		 				    messag.setDate(getStringDate());
//		 				    messag.setFileName(ImogyFileNames[imogyCounter]);
//		 				    messag.setSenderId(owner.getID());
//		 	  				messag.isFile(true);
//		 	  				messag.setType(FileType.IMOGY);
//		 	  				messag.setFile(null);
//		 	  				MainClient.getServer().sendMessage(messag, myPerson.getID());
//		 				}catch(Exception e1) {
//		 					System.out.println("Imogy Exception "+e1.toString());
//		 				}
//					}
//					
//				});
//				imogyCounter++;
// 					}
//			 imogyCounter = 0;
			 
 	}
	public String getStringDate() {
		    Date d = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols();
			SimpleDateFormat format = new SimpleDateFormat("hh:mm aa", dfs);
			return format.format(d);
	}
 
 }
