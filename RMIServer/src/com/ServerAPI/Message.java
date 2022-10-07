package com.ServerAPI;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;

public class Message implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String date;
	private boolean isFile;
	private File file;
	private String fileName;
	private int senderId;
	private int Type;
	
	
	public int getType() {
		return  Type;
	}
	public void setType(int fileType) {
		this.Type = fileType;
	}
	public String getFileName() throws RemoteException {
		return fileName;
	}
	public void setFileName(String fileName)throws RemoteException {
		this.fileName = fileName;
	}
	public int getSenderId() throws RemoteException{
		return senderId;
	}
	public void setSenderId(int senderId) throws RemoteException{
		this.senderId = senderId;
	}
	public File getFile() throws RemoteException{
		return this.file;
	}
	public void setFile(File file )throws RemoteException {
		this.file  = file ;
	}
	public String getMessage() throws RemoteException {
		return message;
	}
	public void setMessage(String message)throws RemoteException {
		this.message = message;
	}
	public String getDate() throws RemoteException{
		return date;
	}
	public void setDate(String date) throws RemoteException{
		this.date = date;
	}
	public boolean isFile()throws RemoteException {
		return isFile;
	}
	public void isFile(boolean isFile) throws RemoteException{
		this.isFile = isFile;
	}
	
	
}
