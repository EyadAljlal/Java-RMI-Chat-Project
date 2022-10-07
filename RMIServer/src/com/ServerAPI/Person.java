package com.ServerAPI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Person implements java.io.Serializable{

 	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int phoneNumber;
	private String userName;
	private String password;
	private String state;
	private byte [] image;
	public ClientInterface receiving;
	private  ArrayList<Message> msg = new ArrayList<Message>();
	  
	
	public ArrayList<Message> getMessages() throws RemoteException {
		 
		return this.msg;
	}

 
	public void setMessages(ArrayList<Message> msg) throws RemoteException {
		 this.msg = msg;
	}

	 
	public int getID() throws RemoteException {
 		return this.id;
	}

 
	public void setID(int id) throws RemoteException {
		this.id = id;
		
	}

 
	public void setName(String name) throws RemoteException {
 		this.name = name;
 	}

	 
	public void setPhoneNumber(int phoneNumber) throws RemoteException {
		this.phoneNumber = phoneNumber;
		
	}

 
	public void setUserName(String userName) throws RemoteException {
		this.userName = userName;
		
	}

	 
	public void setPassword(String password) throws RemoteException {
		this.password = password;
		
	}

	 
	public void setImage(byte[] fileData) throws RemoteException {
	this.image = fileData;
		
	}

 
	public String getState() throws RemoteException {
 		return this.state;
	}

	 
	public void setState(String state) throws RemoteException {
		this.state = state;
	}

	 
	public String getName() throws RemoteException {
		 
		return this.name;
	}

 
	public int getPhoneNumber() throws RemoteException {
		 
		return this.phoneNumber;
	}

	 
	public String getUserName() throws RemoteException {
		 
		return this.userName;
	}

 
	public String getPassword() throws RemoteException {
		 return this.password;
	}

 
	public byte[] getImage() throws RemoteException {
 		return  this.image;
	}
	
  }
