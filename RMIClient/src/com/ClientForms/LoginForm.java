package com.ClientForms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.MyLabel;
import com.Client.MainClient;
import com.Designer.ComponentDesginer;
import com.Resource.ConnectState;
import com.Resource.MyColor;
import com.Resource.Values;
import com.ServerAPI.Person;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import com.MyButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginForm extends JFrame {
	
	public  static Person person = new Person(); 
	public static PersonMenuForm personMenuForm;
	private static LoginForm frame;
	private JPanel contentPane;
	private JTextField txt_UserName;
	private JPasswordField txt_Password;
	private JPanel panel_1;
	private JPanel panel_Login;
	private JLabel lbl_logIn;
	private JLabel lbl_UserName ;
	private JLabel lbl_Password;
	private JLabel lbl_New; 
	private JLabel lbl_ClickHere; 
	private JLabel lbl_SginUp; 
	private JButton btn_Login; 
	private JLabel lbl_ForgetPassword; 
	private JLabel lbl_Title; 
  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginForm();
					frame.setVisible(true);
 				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		
		setTitle("WhatsApp");
 		getContentPane().setLayout(null);
 		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons\\3.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 70, 451, 541);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.info);
		panel_1.setBounds(10, 11, 417, 482);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		 panel_Login = new JPanel();
		panel_Login.setBounds(41, 60, 335, 383);
		panel_1.add(panel_Login);
		panel_Login.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_Login.setBackground(new Color(152, 251, 152));
		panel_Login.setLayout(null);
		
		txt_UserName = ComponentDesginer.getTextField(189, 29,MyColor.BackgroundPanelColor);
 		txt_UserName.setBounds(120, 80, 198, 29);
		panel_Login.add(txt_UserName);
		  
		lbl_logIn = new JLabel("Log In");
		lbl_logIn.setFont(new Font("Segoe Print", Font.BOLD, ٢٥));
		lbl_logIn.setBounds(117, 11, 97, 58);
		panel_Login.add(lbl_logIn);
		
		lbl_UserName = new JLabel("Username  :");
		lbl_UserName.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_UserName.setBounds(10, 80, 106, 29);
		panel_Login.add(lbl_UserName);
		
		lbl_Password = new JLabel("Password   :");
		lbl_Password.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_Password.setBounds(10, 129, 106, 29);
		panel_Login.add(lbl_Password);
		
		lbl_New = new JLabel("If you are new, ");
		lbl_New.setToolTipText("");
		lbl_New.setFont(new Font("Times New Roman", Font.BOLD, ٢٠));
		lbl_New.setBounds(10, 316, 131, 29);
		panel_Login.add(lbl_New);
		
		txt_Password = ComponentDesginer.getPasswordField(189, 29,MyColor.BackgroundPanelColor);
 		txt_Password.setBounds(120, 129, 198, 29);
		panel_Login.add(txt_Password);
		
		lbl_ClickHere = new JLabel("click here");
		lbl_ClickHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_ClickHere.setForeground(new Color(0, 0, 200));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SginUp sginUp = new SginUp("icons\\AddPerson.png");
 				frame.dispose();
			}
		});
		lbl_ClickHere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_ClickHere.setForeground(new Color(0, 0, 255));
		lbl_ClickHere.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_ClickHere.setBounds(141, 316, 87, 29);
		panel_Login.add(lbl_ClickHere);
		
		lbl_SginUp = new JLabel("to Sgin Up");
		lbl_SginUp.setBackground(SystemColor.menu);
		lbl_SginUp.setForeground(new Color(0, 0, 0));
		lbl_SginUp.setFont(new Font("Times New Roman", Font.BOLD, ١٩));
		lbl_SginUp.setBounds(231, 316, 87, 29);
		panel_Login.add(lbl_SginUp);
		
		btn_Login =ComponentDesginer.getButton("LOGIN",175, 40,Values.ButtonRadius);
 		btn_Login.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
				btn_Login.setForeground(new Color(255, 255, 255));
				try {
					personMenuForm = new PersonMenuForm();
    				person = MainClient.getServer().logIn(personMenuForm,txt_UserName.getText(), txt_Password.getText());
  					if(person  == null) {
  	 					JOptionPane.showMessageDialog(null,"Username or Password is not correct -_- ","Error",JOptionPane.ERROR_MESSAGE);
  	 					return;
   					}
  					personMenuForm.setProfileInformation(person);
  					personMenuForm.setVisible(true);
					frame.dispose();
				} catch (RemoteException e1) {
 					JOptionPane.showMessageDialog(null,"Remote Exception in personMenuForm :"+e1.getMessage());
 				} 
 			}
		});
		btn_Login.setBounds(81, 224, 175, 40);
 		btn_Login.setBorder(new LineBorder(new Color(0, 0, 0)));
 		btn_Login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_Login.add(btn_Login);
		
		lbl_ForgetPassword = new JLabel("Forget Passwod !");
		lbl_ForgetPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_ForgetPassword.setForeground(Color.BLUE);
		lbl_ForgetPassword.setToolTipText("");
		lbl_ForgetPassword.setFont(new Font("Times New Roman", Font.BOLD, ١٧));
		lbl_ForgetPassword.setBounds(187, 173, 131, 29);
		panel_Login.add(lbl_ForgetPassword);
		
		lbl_Title = new JLabel("Wellcome In WhatsApp ");
		lbl_Title.setFont(new Font("Segoe Print", Font.BOLD, ٢٧));
		lbl_Title.setBounds(31, 7, 358, 42);
		panel_1.add(lbl_Title);
		 
		
	}
}
