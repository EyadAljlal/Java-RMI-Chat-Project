package com.Designer;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.MyButton;
import com.Client.MainClient;
import com.ClientForms.LoginForm;
import com.ClientForms.PersonMenuForm;
import com.ClientForms.SginUp;
import com.Resource.ConnectState;
import com.Resource.Values;
import com.ServerAPI.Person;

public class Setting {
	private JButton btn_OK; 
  	private Person client;
 	
 	public JButton getBtn_OK() {
		return btn_OK;
	}
	public Setting(Person c ,Image image) throws RemoteException {
		client = c;
		LoginForm.personMenuForm.dialog = new ChoiceDialog(LoginForm.personMenuForm ,image,client.getName(),false);
 			
		JButton btn_add = ComponentDesginer.getButton("Add new Person", 230, 40, Values.ButtonRadius);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_add.setForeground(SystemColor.white);
 				ChoiceDialog dialog1 = null;
				try {
					dialog1 = new ChoiceDialog(LoginForm.personMenuForm ,image,client.getName(),false);
				} catch (RemoteException e1) {
 					e1.printStackTrace();
				}
				
				JLabel lbl_add = ComponentDesginer.getJLabel("Enter person\'s phone No. :",230,40);
				lbl_add.setBackground(SystemColor.white);
 				JTextField txt_Phone = ComponentDesginer.getTextField( 250, 40, SystemColor.white);
 				txt_Phone.addKeyListener(new KeyAdapter() {
 					@Override
 					public void keyTyped(KeyEvent e) {
 						char c = e.getKeyChar();
 						if(txt_Phone.getText().length()>=9 || c < '0' || c > '9')
 							e.consume();
 					}
 				});
				JButton btn_OK = ComponentDesginer.getButton("OK", 230, 40,Values.ButtonRadius );
				btn_OK.addActionListener(new ActionListener(){
 					@Override
					public void actionPerformed(ActionEvent e) {
 						 
 						LoginForm.personMenuForm.peopleListPanel.forEach((c)->{
 							try {
								if(Integer.parseInt(txt_Phone.getText()) == c.person.getPhoneNumber())
	 								JOptionPane.showMessageDialog(btn_add, "This person is in your list ^_^ ... ","Information",JOptionPane.INFORMATION_MESSAGE);
 									return;
							} catch (NumberFormatException | RemoteException e1) {
 								e1.printStackTrace();
							}  
 						});
 						try {
 							if(txt_Phone.getText()==""||txt_Phone.getText()==" ") {
 								JOptionPane.showMessageDialog(btn_add, "Please add phone number","Information",JOptionPane.INFORMATION_MESSAGE);
 								return;
 							}
 							Person per = MainClient.getServer().addNewPersonToList(client.getID(),Integer.parseInt(txt_Phone.getText()));
 							 LoginForm.personMenuForm.addPersonPanel(per);
  								JOptionPane.showMessageDialog(btn_add, "The person added successfully ^_^ ...","Information",JOptionPane.INFORMATION_MESSAGE);
 						} catch (Exception e1) {
 							e1.toString();
						} 
 					}
 				});
 				dialog1.addComponents(lbl_add,txt_Phone,btn_OK);
			}
			
		});
		JButton btn_modify = ComponentDesginer.getButton("Modify your information", 230, 40, Values.ButtonRadius);
		btn_modify.addActionListener(new ActionListener() {
			  
		public void actionPerformed(ActionEvent e) {
				btn_OK =  btn_modify;;
				btn_modify.setForeground(SystemColor.white);
				SginUp sginUp = new SginUp("icon\\AddPerson.png");
				sginUp.IsFormForModify(true, client);
				LoginForm.personMenuForm.dialog.setVisible(false);
				LoginForm.personMenuForm .setVisible(false);
			}});
		JButton btn_Color = ComponentDesginer.getButton("Change background color", 230, 40, Values.ButtonRadius);
		btn_Color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(LoginForm.personMenuForm , "Select Background Color",SystemColor.info);
				if(color !=null) {
 				 LoginForm.personMenuForm.contentPane.setBackground(color);
 				 LoginForm.personMenuForm.panelList.setBackground(color);
				}
  				 btn_Color.setForeground(SystemColor.white); 
 			}});
		LoginForm.personMenuForm.dialog.addComponents(btn_add,btn_modify,btn_Color);
	 
		}
	
	 
	 
}
