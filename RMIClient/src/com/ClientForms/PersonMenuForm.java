package com.ClientForms;
import com.Resource.*;
import com.ServerAPI.ClientInterface;
import com.ServerAPI.FileType;
import com.ServerAPI.Message;
import com.ServerAPI.Person;
import com.ServerAPI.ServerInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import com.MyImage;
import com.Client.MainClient;
import com.Designer.ChoiceDialog;
import com.Designer.ComponentDesginer;
import com.Designer.PersonPanel;
import com.Designer.Setting;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import com.MyButton;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
 import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JToolBar;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonMenuForm extends JFrame implements ClientInterface {
	 
	private static final long serialVersionUID = 1L;
 	public  PersonPanel personPanel;
	public  ChoiceDialog dialog;
	public  int autoHeight = 80;
	public  ArrayList<PersonPanel> peopleListPanel = new ArrayList<PersonPanel>();
	 
 	public JPanel contentPane;
	public JPanel panelList;
	private Image image;
	public Person person = new Person();
	public MessagesForm msgForm;
  
	public void setProfileInformation(Person p )  {
	 try {
		 person = p;
		 ByteArrayInputStream fin = new ByteArrayInputStream(p.getImage());
		 BufferedImage img = ImageIO.read(fin);
		 image = img;
		 setIconImage(image);
		 setTitle(p.getName());
		 ArrayList<Person> pers = MainClient.getServer().getPeopleList(person.getID());
		 if(pers != null) {
 		    fillList(pers);
 		    MainClient.getServer().refreshState();
		 }
	 }catch(Exception e) {
		 JOptionPane.showMessageDialog(this,"in person Menu form : "+ e.getMessage());
		 System.out.println(e.getMessage());
	 }
	}
	public PersonMenuForm() throws RemoteException {
		
		UnicastRemoteObject.exportObject(this, 0);
 		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 70, 451, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
 		contentPane.setBackground(MyColor.BackgroundBigPanelColor);
 		contentPane.setLayout(null);
 		
 		JScrollPane scrollPane = new JScrollPane();
 		scrollPane.setMaximumSize(new Dimension(32767, 1000000));
 		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
 		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 		scrollPane.setAutoscrolls(true);
 		scrollPane.setPreferredSize(new Dimension(417, autoHeight));
 		scrollPane.setBounds(10, 36, 417, 457);
 	 
 		contentPane.add(scrollPane);
 		
 		panelList = new JPanel();
  		panelList.setBorder(new LineBorder(SystemColor.controlHighlight, 2));
 		panelList.setMaximumSize(new Dimension(32767, 1000000));
 		panelList.setAutoscrolls(true);
 		scrollPane.setViewportView(panelList);
 		panelList.setBounds(0, 0, 417, autoHeight);
  		panelList.setPreferredSize(new Dimension(417, autoHeight));
 		panelList.setBackground(SystemColor.info);
 		FlowLayout fl_panelList = new FlowLayout(FlowLayout.LEFT, 5, 5);
 		fl_panelList.setAlignOnBaseline(true);
 		panelList.setLayout(fl_panelList);
 		 
 		JLabel lbl_List = new JLabel("People List");
 		lbl_List.setBounds(10, 0, 194, 34);
 		lbl_List.setFont(MyFont.CAPTION_FONT);
 		contentPane.add(lbl_List);
 		 
 		MyImage ImageSetting = new MyImage();
 		ImageSetting.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
  				Setting setting;
				try {
 					setting = new Setting(person,image);
 				} catch (RemoteException e2) {
					JOptionPane.showMessageDialog(null,e2.getMessage(), "Image Error", JOptionPane.ERROR_MESSAGE);
				} 
 			}});
  		ImageSetting.setBounds(394, 0, 33, 33);
 		ImageSetting.setIcon(new ImageIcon("icons\\Setting.png"));
  		contentPane.add(ImageSetting);
   		setVisible(false);
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
	}
	
	public void fillList(ArrayList<Person>  clients) throws IOException, NotBoundException {
 		for(Person c  :clients) {
 			 ByteArrayInputStream in = new ByteArrayInputStream(c.getImage());
			 BufferedImage bimg = ImageIO.read(in);
			 Image img = new ImageIcon(bimg).getImage();
			 PersonPanel personPane = new PersonPanel(img ,c);
			 peopleListPanel.add(personPane);
 			 JPanel pl = personPane.getPerson_Panel();
	 			pl.addMouseListener(new MouseAdapter() {
		 			@Override
		 			public void mouseClicked(MouseEvent e) {
		 				try {
		 					msgForm  = new MessagesForm(c,person,image);
 							LoginForm.personMenuForm.setVisible(false);
 							
						} catch (RemoteException e1) {
 							   e1.printStackTrace();
						}
		 			}
		 		});
   	 			 panelList.setPreferredSize(new Dimension(417, autoHeight));
 	   			 pl.setPreferredSize(new Dimension(panelList.getWidth() - 10 ,59));
	 	 		 panelList.add(pl);
	 	 		 panelList.revalidate();
		 		 panelList.repaint();
		 		 autoHeight += 71;
 		}
	}
 		public void addPersonPanel( Person p) {
 			 
 			try {
 				 ByteArrayInputStream in = new ByteArrayInputStream(p.getImage());
				 BufferedImage bimg = ImageIO.read(in);
				 Image img = new ImageIcon(bimg).getImage();
 				 PersonPanel pan = new PersonPanel(img ,p);
 				 peopleListPanel.add(pan);
				 JPanel pl = pan.getPerson_Panel();
				 pl.addMouseListener(new MouseAdapter() {
			 			@Override
			 			public void mouseClicked(MouseEvent e) {
			 				try {
								 msgForm = new MessagesForm(p,person,image );
								LoginForm.personMenuForm.setVisible(false);
							} catch (RemoteException e1) {
 								 e1.printStackTrace();
							}
			 			}
			 		});
  				 panelList.setPreferredSize(new Dimension(417, autoHeight));
	   			 pl.setPreferredSize(new Dimension(panelList.getWidth(),59));
 		 		 panelList.add(pl);
 		 		 panelList.revalidate();
		 		 panelList.repaint();
		 		 autoHeight += 71;
 			}catch(Exception e) {
 				System.out.println(e.toString());
 			}
  		}
 		@Override
 		public void recieveMessage(Message message) throws RemoteException {
 
			if(message != null) {
 				msgForm.setRightDirection(msgForm.myPerson.getID() == message.getSenderId());
				msgForm.showMessage(message.getMessage(),message.getDate());
				msgForm.setRightDirection(false);
 				} 
  		}

 		@Override
 		public void recieveState(String state,int id) throws RemoteException {
 	 		  peopleListPanel.forEach((c)->{
 	 			  try {
					if(c.person.getID() == id) {
						c.person.setState(state);
						c.getConnectState().setText(state);
 					}
					
				} catch (RemoteException e) {
 					e.printStackTrace();
				}
 	 		  });
 		}

 		@Override
 		public void recieveFile(Message message) throws RemoteException {
 			 try {
 				 if(message == null)
 					 return;
 				     msgForm.setRightDirection(msgForm.myPerson.getID() == message.getSenderId());
 				     File f = new File(message.getFileName());
 				     String fileExt = FileTest.getExtentionOf(message.getFileName());
				    
 	 						 if(f.createNewFile()) {
 	 							FileTest.copyFile(f,message.getFile());
   	 							msgForm.showFile(FileTest.getshownImagePath(fileExt),message.getDate(), f.getPath());
 	 						 }else 
 	 							msgForm.showFile(FileTest.getshownImagePath(fileExt),message.getDate(), f.getPath());
 			 	 
	  			} catch (RemoteException e) {
 					e.printStackTrace();
				} catch (IOException e) {
 					e.printStackTrace();
				}
 			msgForm.setRightDirection(false);
   		}

 		@Override
 		public void exit() throws RemoteException {
 			
 		}
 		 
		@Override
		public void recieveImage(Message message) throws RemoteException {
			try {
				if(message == null)
					return;
			 msgForm.setRightDirection(msgForm.myPerson.getID() == message.getSenderId());
		     File f = message.getFile();
		    // String fileExt = FileTest.getExtentionOf(message.getFileName());
			 
				 if(f.createNewFile()) {
					 FileTest.copyFile(f,message.getFile());
 					 msgForm.showImage(f, msgForm.getStringDate());
				 } else {
  					 msgForm.showImage(f, msgForm.getStringDate());
				 }
				
			}catch(Exception e) {
				System.out.println(e.toString());
			}
			 msgForm.setRightDirection(false);
		}
		@Override
		public void recieveImogy(Message message) throws RemoteException {
			msgForm.setRightDirection(true); 
			msgForm.showImogy(message.getFileName());
 			msgForm.setRightDirection(false);
		}
  }
