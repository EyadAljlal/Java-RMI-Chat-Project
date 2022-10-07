package com.ServerAPI;

 
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{
	
	public final static String  LOOKINGUP_NAME = "WhatsApp";
	public Person logIn(ClientInterface c,String username,String password) throws RemoteException;
	public boolean sginUp(Person client) throws RemoteException;
	public boolean modifyProfile(Person client) throws RemoteException;
	public ArrayList<Person> getPeopleList(int phoneNumber) throws RemoteException;
	public Person addNewPersonToList(int id, int phoneNumber) throws RemoteException, SQLException;
  	public void sendMessage(Message message,int receiverID) throws RemoteException;
  	public void sendMessageToAll(Message message,int senderID) throws RemoteException;
 	public boolean removeAccount() throws RemoteException;
 	public boolean removePersonFromList(int id , int personID) throws RemoteException;
 	public boolean removeAllMessages(int senderID, int receiverID) throws RemoteException;
 	public void logOut(int id) throws RemoteException;
 	public void refreshState()  throws RemoteException;
 	public void sendState(String state,int senderID,int receiverID) throws RemoteException;
}
