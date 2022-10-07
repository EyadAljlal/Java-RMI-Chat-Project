package com.Designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
 import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;
import com.MyButton;
import com.MyImage;
import com.MyLabel;
import com.ClientForms.LoginForm;
import com.Resource.MyColor;
import com.Resource.MyFont;
import com.Resource.Values;
import java.time.Month;
public class ChoiceDialog extends JDialog {
	
	public  JPanel MainPanel;
	public static GridLayout grid;
 	public  int autoHeight;
   
	public ChoiceDialog(JFrame frame,Image image, String text,boolean isGridLayout) {
	    super(frame);
 		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
		setIconImage(image);
		setTitle(text);
		setBounds(450, 150, 245, 266);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(231, 229));
		scrollPane.setBounds(0, 0, 231, 229);
		getContentPane().add(scrollPane);
		
		MainPanel = new JPanel();
		MainPanel.setBackground(SystemColor.window);
		MainPanel.setPreferredSize(new Dimension(231, 225));
		scrollPane.setViewportView(MainPanel);
		if(isGridLayout) {
			grid = new GridLayout(5,5, 10, 10);
			MainPanel.setLayout(grid);
 		}
		else {
			MainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
 		}
 	}
	
	public ChoiceDialog(JDialog frame,Image image, String text,boolean isGridLayout) {
	    super(frame);
 		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
		setIconImage(image);
		setTitle(text);
		setBounds(450, 150, 245, 266);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(231, 229));
		scrollPane.setBounds(0, 0, 231, 229);
		getContentPane().add(scrollPane);
		
		MainPanel = new JPanel();
		MainPanel.setBackground(SystemColor.window);
		MainPanel.setPreferredSize(new Dimension(231, 225));
		scrollPane.setViewportView(MainPanel);
		if(isGridLayout) {
			grid = new GridLayout(5,5, 10, 10);
			MainPanel.setLayout(grid);
 		}
		else {
			MainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
 		}
 	}
	
	public ChoiceDialog(Image contentImage,Image dialogImage,String name) {
		super(LoginForm.personMenuForm);
 		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
		setIconImage(dialogImage);
		setTitle(name);
		setBounds(450, 150, 245, 266);
		getContentPane().setLayout(null);
 		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(231, 229));
		scrollPane.setBounds(0, 0, 231, 229);
		getContentPane().add(scrollPane);
 		MainPanel = new JPanel();
		MainPanel.setBackground(SystemColor.window);
		MainPanel.setPreferredSize(new Dimension(231, 225));
		scrollPane.setViewportView(MainPanel);
		MainPanel.setLayout(new BorderLayout(3,3));
		try {
			JLabel lbl = ComponentDesginer.getImageMyLabel(contentImage, MainPanel.getWidth() , MainPanel.getHeight(), 13);
 
			MainPanel.add(lbl,BorderLayout.CENTER);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
 	}
	 
	public void addComponents(JComponent ... component) {
		 
		for(JComponent c: component) {
			if(c instanceof  JButton)
			MainPanel.add((JButton)c);
			else if(c instanceof  MyImage)
				MainPanel.add((MyImage)c);
			else if(c instanceof  JLabel)
				MainPanel.add((JLabel)c);
			else if(c instanceof  JPanel)
				MainPanel.add((JPanel)c);
 			else if(c instanceof  JTextField)
				MainPanel.add((JTextField)c);
			else 
				JOptionPane.showMessageDialog(this, "Component is Not Found !");
 		}
		
	}
}
