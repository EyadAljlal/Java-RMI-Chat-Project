package com.ClientForms;

import java.awt.BorderLayout;
import java.io.*;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import com.MyButton;
import com.MyImage;
import com.MyLabel;
import com.Client.MainClient;
import com.Designer.ComponentDesginer;
import com.Resource.MyColor;
import com.Resource.Values;
import com.ServerAPI.Person;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.RenderedImage;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SginUp extends JFrame {
	 
	private static final long serialVersionUID = 1L;
	private SginUp frame;
	private JPanel contentPane;
	private JTextField txt_Name;
	private JTextField txt_phoneNumber;
	private JTextField txt_UserName;
	private JTextField txt_Password;
	private static boolean isFormForModify = false;
	private JButton btn_Save;
	private JButton btn_Cancle;
	private JButton btn_GoBack;
	private JLabel lbl_ProfileImage;
	private int personModifyId;
	
	byte [] profileImageData;
 
 
	public void IsFormForModify(boolean flag,Person p) {
		this.setInformation(p);
		isFormForModify = flag;
 		if(flag) {
   			btn_Save.setText("Modify");
		}
		else {
			
		}
	}
	public SginUp(String iconPath) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconPath));
		setBounds(350, 70, 451, 541);
  		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.info);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(138, 206, 244, 29);
		panel_1.add(txt_Name);
		txt_Name.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		txt_Name.setColumns(10);
		txt_Name.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		txt_Name.setBackground(SystemColor.info);
		
		JLabel lbl_Name = new JLabel("Your Name :");
		lbl_Name.setBounds(18, 206, 110, 29);
		panel_1.add(lbl_Name);
		lbl_Name.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		
		btn_Cancle = ComponentDesginer.getButton("Cancel", 92, 40, Values.ButtonRadius);
		btn_Cancle.setFocusPainted(false);
		btn_Cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Cancle.setForeground(new Color(255, 255, 255));
				if(SginUp.isFormForModify) {
				 LoginForm.personMenuForm.setVisible(true);
				 LoginForm.personMenuForm.dialog.setVisible(true);
					 dispose();
				}
				else {
					LoginForm.main(new String [] { } );
	 				 dispose();
				}
			}
		});
		btn_Cancle.setBounds(81, 431, 92, 40);
 		btn_Cancle.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		btn_Cancle.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_Cancle.setBackground(new Color(0, 128, 0));
		btn_Cancle.setForeground(new Color(255, 255, 255));
 		btn_Cancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(btn_Cancle);
		
		JLabel lbl_PhoneNumber = new JLabel("Phone No.  :");
		lbl_PhoneNumber.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_PhoneNumber.setBounds(24, 254, 104, 29);
		panel_1.add(lbl_PhoneNumber);
		
		txt_phoneNumber = new JTextField();
		txt_phoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(txt_phoneNumber.getText().length()>=9 || c < '0' || c > '9')
					e.consume();
			}
		});
		txt_phoneNumber.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		txt_phoneNumber.setColumns(10);
		txt_phoneNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		txt_phoneNumber.setBackground(SystemColor.info);
		txt_phoneNumber.setBounds(138, 254, 244, 29);
		panel_1.add(txt_phoneNumber);
		
		JLabel lbl_UserName = new JLabel("Username  :");
		lbl_UserName.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_UserName.setBounds(24, 302, 104, 29);
		panel_1.add(lbl_UserName);
		
		txt_UserName = new JTextField();
		txt_UserName.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		txt_UserName.setColumns(10);
		txt_UserName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		txt_UserName.setBackground(SystemColor.info);
		txt_UserName.setBounds(138, 302, 244, 29);
		panel_1.add(txt_UserName);
		
		JLabel lbl_Password = new JLabel("Password  :");
		lbl_Password.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_Password.setBounds(24, 348, 104, 28);
		panel_1.add(lbl_Password);
		
		txt_Password = new JTextField();
		txt_Password.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		txt_Password.setColumns(10);
		txt_Password.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		txt_Password.setBackground(SystemColor.info);
		txt_Password.setBounds(138, 348, 244, 29);
		panel_1.add(txt_Password);
		
		btn_Save =  ComponentDesginer.getButton("Save", 92, 40, Values.ButtonRadius);
		btn_Save.setFocusPainted(false);
 		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Save.setForeground(new Color(255, 255, 255));
				try {
 					 Person client = new Person();
					 client.setName(txt_Name.getText());
					 client.setImage(getImageIconBytes(lbl_ProfileImage.getIcon()));
					 client.setPhoneNumber(Integer.parseInt( txt_phoneNumber.getText()));
					 client.setUserName(txt_UserName.getText());
					 client.setPassword(txt_Password.getText());
 		             MainClient.getServer().sginUp(client);
 		             int result = JOptionPane.showConfirmDialog(frame,"Sgin Up Done ... ^_^ "
 		             		+ "\nDo you want to go for chating ?",client.getName(),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
 		             if(result ==  JOptionPane.YES_OPTION) {
 		            	LoginForm.personMenuForm = new PersonMenuForm();
 		            	LoginForm.person = MainClient.getServer().logIn(LoginForm.personMenuForm,client.getUserName(), client.getPassword());
 	  					 
 	  					LoginForm.personMenuForm.setProfileInformation(LoginForm.person);
 	  					LoginForm.personMenuForm.setVisible(true);
 						dispose();
 		           
	              } else {
	            	    LoginForm.main(new String [] { } );
		 				dispose();
				 }
 					} catch (Exception e1) {
 					JOptionPane.showMessageDialog(null,"Sgin Up Error: "+ e1.getMessage());
 				} 
			}
		});
		btn_Save.setBounds(237, 431, 92, 40);
 		btn_Save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 		panel_1.add(btn_Save);
 		
 		JPanel panel = new JPanel();
 		panel.setBounds(0, 0, 427, 48);
 		panel_1.add(panel);
 		panel.setLayout(null);
 		
 		JLabel lbl_Title = new JLabel("Enter Your Information");
 		lbl_Title.setBounds(10, 0, 341, 48);
 		panel.add(lbl_Title);
 		lbl_Title.setFont(new Font("Segoe Print", Font.BOLD, ٢٧));
 		
 		btn_GoBack = ComponentDesginer.getImageButton("icons\\ic_forward_message.png", 40, 40, 50);
 		btn_GoBack.setFocusPainted(false);
 		btn_GoBack.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				if(SginUp.isFormForModify) {
 					 LoginForm.personMenuForm.setVisible(true);
 					 LoginForm.personMenuForm.dialog.setVisible(true);
  					 dispose();
				}
				else {
					LoginForm.main(new String [] { } );
	 				dispose();
				}
 			}
 		});
  		btn_GoBack.setBounds(380, 7, 40, 40);
 		btn_GoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 		panel.add(btn_GoBack);
 		
 		lbl_ProfileImage = new JLabel();
 		lbl_ProfileImage.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				JFileChooser fileChooser = new JFileChooser();
 				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files","png","jpg","svg");
 				fileChooser.setFileFilter(filter);
 				fileChooser.showDialog(lbl_ProfileImage, "Select image");
 				
 				try {
 					if(fileChooser.getSelectedFile() != null ) {
 					Image image = ImageIO.read(fileChooser.getSelectedFile()).getScaledInstance(lbl_ProfileImage.getWidth(),lbl_ProfileImage.getHeight(),Image.SCALE_AREA_AVERAGING);
   					lbl_ProfileImage.setIcon(new ImageIcon(image));
 					}
 				}catch(Exception ex) {
 					JOptionPane.showMessageDialog(lbl_ProfileImage, ex.getMessage());
 				}
 			}
 		});
 		lbl_ProfileImage.setBorder(new LineBorder(new Color(0, 0, 0)));
 		lbl_ProfileImage.setBounds(281, 59, 136, 136);
 		lbl_ProfileImage.setIcon(new ImageIcon(Values.DEFUALT_IMAGE_PROFILE.getImage().getScaledInstance(lbl_ProfileImage.getWidth(),lbl_ProfileImage.getHeight(),Image.SCALE_AREA_AVERAGING)));
  		panel_1.add(lbl_ProfileImage);
	}
	
	public static byte [] getBytesFile(File file) {
		 try {
			 byte [] data = new byte[(int)file.length()];
			 FileInputStream fin = new FileInputStream(file);
 			 fin.read(data);
 			 return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	private void setInformation(Person p)  {
		 
		try {
			 ByteArrayInputStream byteIn = new ByteArrayInputStream(p.getImage());
			 BufferedImage newImage = ImageIO.read(byteIn);
			 ImageIO.write(newImage,"jpg",new File("Files\\ProfileImageFile\\newImage.jpg"));
			 lbl_ProfileImage.setIcon(new ImageIcon("Files\\ProfileImageFile\\newImage.jpg"));
			 personModifyId = p.getID();
			 this.txt_Name.setText(p.getName());
			 this.txt_Password.setText(p.getPassword());
 			 this.txt_phoneNumber.setText(""+p.getPhoneNumber());
 			 this.txt_UserName.setText(p.getUserName());
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this,"Modify Error: "+e.toString(),"Modify",JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
	}
	
	public byte[] getImageIconBytes(Icon icon) {
		
		 byte [] imageBytes = null;
 		 BufferedImage imag = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
		 java.awt.Graphics g = imag.createGraphics();
		 icon.paintIcon(null, g, 0, 0);
		 g.setColor(Color.WHITE);
		 g.dispose();
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 try {
			ImageIO.write(imag,"jpg",out);
 			InputStream fin = new ByteArrayInputStream(out.toByteArray());
			byte[] b = new byte[1024];
			ByteArrayOutputStream out1 = new ByteArrayOutputStream();
			int i;
			while( (i = fin.read(b)) != -1) {
					out1.write(b,0,i);
				}
			  imageBytes = out1.toByteArray();
		 } catch (IOException e) {
	 			e.printStackTrace();
		 }
		 return imageBytes;
	}
	
}
