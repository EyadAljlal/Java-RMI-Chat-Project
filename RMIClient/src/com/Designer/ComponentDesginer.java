package com.Designer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.MyButton;
import com.MyImage;
import com.MyLabel;
import com.Resource.MyColor;
import com.Resource.MyFont;
import com.Resource.Values;

public class ComponentDesginer {

	private static JButton button ;
	private static MyImage image ;
	private static JLabel label ;
	private static JLabel jlabel ;
	private static JTextField textField;
	private static JPanel panel;
	
	public static JButton getButton(String text, int width,int height,int radius) {
		button = new JButton() {
		  
			private static final long serialVersionUID = 1L;
 			@Override
			protected void paintComponent(Graphics g) {
			    if (getModel().isArmed()) {
			        g.setColor(Color.WHITE); 
			        setForeground(Color.BLACK);
			    } else if(!getModel().isArmed()) {
			        g.setColor(getBackground()); 
			        setForeground(getForeground()); 
 			    }
			    
			    Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    setPreferredSize(size);
			    setContentAreaFilled(false);
			    g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
 			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			    g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
		};
		button.setText(text);
 		button.setBackground(MyColor.BackgroundButtonColor);
		button.setForeground(new Color(250,250,250));
		button.setFocusPainted(false);
		button.setFont(new Font(MyFont.MessageFontFamily, Font.BOLD,MyFont.MessageFontSize));
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
	public static JButton getImageJButton(String imagePath, int width,int height,int radius)  {
		button = new JButton() {
			 
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
			    if (getModel().isArmed()) {
			        g.setColor(Color.WHITE); 
			        setForeground(Color.BLACK);
			    } else if(!getModel().isArmed()) {
			        g.setColor(getBackground()); 
			        setForeground(getForeground()); 
 			    }
			    
			    Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    setPreferredSize(size);
			    setContentAreaFilled(false);
			    g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
 			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			   // g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
		};
		Image image;
		try {
			image = ImageIO.read(new File(imagePath)).getScaledInstance( width -15 ,height -15 ,Image.SCALE_AREA_AVERAGING);
			button.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
 	 	button.setBackground(Color.WHITE);
		button.setForeground(new Color(0,0,0));
		button.setFocusPainted(false);
		button.setFont(new Font(MyFont.MessageFontFamily, Font.BOLD,MyFont.MessageFontSize));
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
	public static JButton getImageButton(String imagePath, int width,int height,int radius)  {
		button = new JButton() {
			 
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
			    if (getModel().isArmed()) {
			        g.setColor(Color.WHITE); 
			        setForeground(Color.BLACK);
			    } else if(!getModel().isArmed()) {
			        g.setColor(getBackground()); 
			        setForeground(getForeground()); 
 			    }
			    
			    Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    setPreferredSize(size);
			    setContentAreaFilled(false);
			    g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
 			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			   // g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
		};
		Image image;
		try {
			image = ImageIO.read(new File(imagePath)).getScaledInstance( width  ,height  ,Image.SCALE_AREA_AVERAGING);
			button.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
 	 	button.setBackground(Color.WHITE);
		button.setForeground(new Color(0,0,0));
		button.setFocusPainted(false);
		button.setFont(new Font(MyFont.MessageFontFamily, Font.BOLD,MyFont.MessageFontSize));
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
	
	public static JButton getImageButton(Icon icon, int width,int height,int radius)  {
		button = new JButton() {
			 
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
			    if (getModel().isArmed()) {
			        g.setColor(Color.WHITE); 
			        setForeground(Color.BLACK);
			    } else if(!getModel().isArmed()) {
			        g.setColor(getBackground()); 
			        setForeground(getForeground()); 
 			    }
			    
			    Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    setPreferredSize(size);
			    setContentAreaFilled(false);
			    g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
 			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			    g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
		};
	 
		button.setIcon(icon);
  	 	button.setBackground(MyColor.BackgroundButtonColor);
		button.setForeground(new Color(0,0,0));
		button.setFocusPainted(false);
		button.setFont(new Font(MyFont.MessageFontFamily, Font.BOLD,MyFont.MessageFontSize));
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
	public static MyImage getImage(String imagePath, int width,int height, int borderRadius ) {
		image= new MyImage();
 		image.setPreferredSize(new Dimension(width,height));
		image.setForeground(new Color(250,250,250));
		if(imagePath!= null) {
			image.setIcon(new ImageIcon(imagePath));
		} else {
			image.setIcon(Values.DEFUALT_IMAGE_PROFILE);
		}
		image.setBorderSize(borderRadius);
		return image;
	}
	
	public static JLabel getMyLabel(String text, int width, int height, int radius) {
		label = new JLabel() {
	   
			@Override
			protected void paintComponent(Graphics g) {
				Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    this.setPreferredSize(size);
			    g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			    g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
			
			@Override
			public boolean isOpaque() {
		        return true;
			}
				};
		label.setText(text);
		label.setFont(MyFont.MESSAGE_FONT);
		label.setPreferredSize(new Dimension(width,height));
		label.setBackground(MyColor.BackgroundBigPanelColor);
		label.setOpaque(true);	
 		return label;
	}
	
	public static JPanel getJPanel(  int width, int height, int radius ) {
		panel = new JPanel() {
			protected void paintComponent(Graphics g) {
 				Dimension arcs = new Dimension(radius,radius);
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
 				g2.setColor(getBackground());
				g2.drawRoundRect(0,0, width -1 ,height -1 ,arcs.width , arcs.height);
 				g2.fillRoundRect(0,0, width -1 ,height -1 ,arcs.width , arcs.height);
				g2.setColor(getForeground());
				 
				super.paintComponent(g2);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			    g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
		};
		Dimension size = panel.getPreferredSize();
	    size.width = size.height = Math.max(size.width, size.height);
	    panel.setPreferredSize(size);
 		panel.setFont(MyFont.MESSAGE_FONT);
	 
		panel.setOpaque(true);	
 		return panel;
	}
	
	public static JLabel getImageMyLabel(String imagePath, int width, int height, int radius) throws IOException {
		label = new JLabel() {
		 
 			@Override
			protected void paintComponent(Graphics g) {
 			    g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
			    Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    this.setPreferredSize(size);
			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			    g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
			
			@Override
			public boolean isOpaque() {
		        return true;
			}
				};
 		label.setFont(MyFont.MESSAGE_FONT);
		label.setPreferredSize(new Dimension(width,height));
		label.setBackground(MyColor.BackgroundBigPanelColor);
		label.setOpaque(true);	
		Image image = ImageIO.read(new File(imagePath));
		label.setIcon(new ImageIcon( image.getScaledInstance(label.getWidth()-10,label.getHeight()-10,Image.SCALE_AREA_AVERAGING)));
 		return label;
	}
	public static JLabel getImageMyLabel(Image imageIcon, int width, int height, int radius) throws IOException {
		label = new JLabel() {
		 
 			@Override
			protected void paintComponent(Graphics g) {
 			    g.fillRoundRect(0, 0, width - 1, height - 1, radius, radius);
			    Dimension size = getPreferredSize();
			    size.width = size.height = Math.max(size.width, size.height);
			    this.setPreferredSize(size);
			    super.paintComponent(g);
			}
			
			@Override
			protected void paintBorder(Graphics g) {
			    g.setColor(getForeground());
			    g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius,radius);
			}
			
			@Override
			public boolean isOpaque() {
		        return true;
			}
				};
 		label.setFont(MyFont.MESSAGE_FONT);
		label.setPreferredSize(new Dimension(width,height));
		label.setBackground(MyColor.BackgroundBigPanelColor);
		label.setOpaque(true);
  		label.setIcon(new ImageIcon( imageIcon ));
 		return label;
	}
	
	public static JLabel getJLabel(String text, int width, int height) {
		jlabel = new JLabel();
		jlabel.setText(text);
		jlabel.setFont(MyFont.MESSAGE_FONT);
		jlabel.setPreferredSize(new Dimension(width,height));
		return jlabel;
	}
	public  static JTextField getTextField(int width, int height,Color backgroundColor) {
		textField = new JTextField();
 		textField.setFont(MyFont.MESSAGE_FONT);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField.setPreferredSize(new Dimension(width,height));
		textField.setBackground(backgroundColor);
		return textField;
	}
 	public  static JPasswordField getPasswordField(int width, int height,Color backgroundColor) {
 		JPasswordField	textField = new JPasswordField();
 		textField.setFont(MyFont.MESSAGE_FONT);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField.setPreferredSize(new Dimension(width,height));
		textField.setBackground(backgroundColor);
		return textField;
	}
	
	
	   
}
