package com.Designer;

import java.awt.Color;
import java.awt.Container;

import com.ServerAPI.Person;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.MyButton;
import com.MyImage;
import com.Client.MainClient;
import com.ClientForms.LoginForm;
import com.ClientForms.PersonMenuForm;
import com.Resource.MyFont;
import com.Resource.Values;

public class PersonPanel {

	private JPanel person_Panel;
	private MyImage person_Image;
	private JLabel person_Name;
 	private JLabel lbl_ConncetState;
	private JButton btn_ChoiceList;
	private static int flage = 1;
	private Image image;
	public  Person person;
	
	public JPanel getPerson_Panel() {
		return person_Panel;
	}
	
	public  PersonPanel(Image img, Person person,int width) {
		this.person = person;
		image = img;
		person_Panel = new JPanel();
		person_Panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		person_Panel.setPreferredSize(new Dimension(width, 59));
 		person_Panel.setBounds(10, 5, width, 59);
		person_Panel.setLayout(null);
 		setPerson_Image(image);
		try {
			setPerson_Name(this.person.getName());
			setConnectState(this.person.getState());
		} catch (RemoteException e) {
 			e.printStackTrace();
		}
		
	}


	public  PersonPanel(Image image,Person person) {
		this.person = person;
		person_Panel = new JPanel();
		person_Panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		person_Panel.setBounds(10, 11, 397, 59);
		person_Panel.setLayout(null);
		person_Panel.setPreferredSize(new Dimension(397, 59));
 		setPerson_Image(image);
		try {
			setPerson_Name(person.getName());
			setConnectState(person.getState());
		} catch (RemoteException e1) {
 			System.out.println("PersonPanel :"+e1.toString());
		}
   		btn_ChoiceList = ComponentDesginer.getImageButton("icons\\iconSetting.png", 40, 40, Values.CircularBorder);
 		btn_ChoiceList.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e)  {
 				ChoiceDialog dialog= new ChoiceDialog(LoginForm.personMenuForm,image,"Eyad",false);
 				 
 				JButton btn_Strick = ComponentDesginer.getButton("Strick it", 150, 40, Values.ButtonRadius);
 		 		btn_Strick.addActionListener(new ActionListener() {
 		 			public void actionPerformed(ActionEvent e) {
 		 				PersonMenuForm perPane = LoginForm.personMenuForm;
 		 				for(int i = 0 ;i<perPane.peopleListPanel.size(); i++) {
 		 					if(perPane.peopleListPanel.get(i).getPerson_Panel().equals(person_Panel)) {
 		 						perPane.peopleListPanel.get(i).getPerson_Panel().setBackground(Color.green);
  		 						Collections.swap(LoginForm.personMenuForm.peopleListPanel,0,i);
// 		 						try {
// 		 							//perPane.fillList(MainClient.getServer().getPeopleList(perPane.person.getID()));
//								} catch (IOException | NotBoundException e1) {
// 									e1.printStackTrace();
//								}
 		 					}
 		 				  }
 		 			}
 		 				});
 		 	  
// 		 		});
 		 	 
 		 		JButton btn_Star = ComponentDesginer.getButton("Make star", 150, 40, Values.ButtonRadius);
 		 		btn_Star.addActionListener(new ActionListener() {
 		 			 
 		 			public void actionPerformed(ActionEvent e) {
 		 				
 		 			}
 		 		});
 		 		 
 		 		JButton btn_Delete =ComponentDesginer.getButton("Delete it", 150, 40, Values.ButtonRadius);
 		 		btn_Delete.addActionListener(new ActionListener() {
 		 			public void actionPerformed(ActionEvent e) {
 		 				try {
							if(JOptionPane.showConfirmDialog(btn_ChoiceList,"Do you want really remove "+person.getName()+"?") ==JOptionPane.YES_OPTION) {
							Container c = person_Panel.getParent();
							c.remove(person_Panel);
							c.revalidate();
							c.repaint();
							MainClient.getServer().removePersonFromList(LoginForm.person.getID(), person.getID());
							}
						} catch (HeadlessException | RemoteException e1) {
 							e1.printStackTrace();
						}
 		 			}
 		 			
 		 		}); 
 		 		dialog.addComponents(btn_Strick,btn_Star,btn_Delete);
 			}});
 		btn_ChoiceList.setBounds(354, 11, 33, 33);
  		btn_ChoiceList.setBorder(new EmptyBorder(0, 0, 0, 0));
 		btn_ChoiceList.setBorderPainted(false);
 		btn_ChoiceList.setBackground(Color.WHITE);
 		//btn_ChoiceList.setIcon(new ImageIcon(new ImageIcon("icons\\iconSetting.png").getImage().getScaledInstance(btn_ChoiceList.getWidth()-10,btn_ChoiceList.getHeight()-10,Image.SCALE_AREA_AVERAGING)));
 		btn_ChoiceList.setContentAreaFilled(false);
 		person_Panel.add(btn_ChoiceList);
 	}
 
	public MyImage getPerson_Image() {
		return person_Image;
	}


	private void setPerson_Image(Image image) {
		
		person_Image = new MyImage();
		person_Image.setBounds(0, 0, 59, 59);
 		person_Image.setForeground(new Color(250,250,250));
		if(image!= null) {
			person_Image.setIcon(new ImageIcon(image));
		} else {
			person_Image.setIcon(Values.DEFUALT_IMAGE_PROFILE);
		}
 		person_Image.setBorderSize(Values.BorderWidth);
		person_Panel.add(person_Image);
	}


	public JLabel getPerson_Name() {
		return person_Name;
	}


	private void setPerson_Name(String name ) {
		
		person_Name = new JLabel();
		person_Name.setFont(new Font(MyFont.MessageFontFamily,Font.BOLD,MyFont.MessageFontSize));
		person_Name.setText(name);
		person_Name.setBounds(82, 11, 254, 33);
		person_Panel.add(person_Name); 
	}
 
	private void setConnectState(String connectState) {
		
		lbl_ConncetState = new JLabel(connectState);
 		lbl_ConncetState.setForeground(new Color(34, 139, 34));
 		lbl_ConncetState.setFont(new Font("Times New Roman", Font.ITALIC, ١٣));
 		lbl_ConncetState.setBounds(92, 38, 80, 21);
 		person_Panel.add(lbl_ConncetState);
	}
	
	public JLabel getConnectState() {
		return lbl_ConncetState;
	}
 }
