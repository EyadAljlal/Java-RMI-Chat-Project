package com.Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ServerAPI.ServerInterface;

import DataBase.DB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;

public class MainServer extends JFrame {

	private JPanel contentPane;
	private static JPanel panel;
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();

 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 MainServer frame = new MainServer();
					 frame.setVisible(true);
  					 ServerImp imp = new ServerImp();
		  			 Registry reg = LocateRegistry.createRegistry(1098);
					 reg.rebind(ServerInterface.LOOKINGUP_NAME,imp);
					 System.setProperty("java.rmi.server.hostname","127.0.0.1");
    				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainServer() {
		setResizable(false);
		setTitle(" Server ");
		Image image = null;
		try {
			image = ImageIO.read(new File("iconYemen.png"));
		} catch (IOException e) {
 			e.printStackTrace();
		}
		this.setIconImage( image);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 70, 451, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setAutoscrolls(true);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 0, 417, 493);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.info);
		scrollPane.setViewportView(panel);
		panel.setBounds(10, 67, 417, 368);
		panel.setPreferredSize(new Dimension(400, 368));
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JLabel lbl = new JLabel("The server is started ... ^_^ ...");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, ٢٣));
		lbl.setPreferredSize(new Dimension(400, 40));
 		panel.add(lbl);
		  
	}
	
	public static void  setClient(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setFont(new Font("Times New Roman", Font.BOLD, ٢٣));
		lbl.setPreferredSize(new Dimension(400, 40));
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		labels.add(lbl);
		panel.add(lbl);
 		panel.revalidate();
		panel.repaint();
	}

	public static void removeLabels(String name) {
		labels.forEach((lbl)->{
			if(lbl.getText().startsWith(name)) {
			Container c = lbl.getParent();
	 		c.remove(lbl);
	 		c.revalidate();
	 		c.repaint();
			}
		});
 		
	}
}
