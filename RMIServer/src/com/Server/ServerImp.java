package com.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HelpfulClass.ConnectState;
import com.ServerAPI.ClientInterface;
import com.ServerAPI.FileType;
import com.ServerAPI.Message;
import com.ServerAPI.Person;
import com.ServerAPI.ServerInterface;

import DataBase.DB;

public class ServerImp extends UnicastRemoteObject implements ServerInterface {
	  
	private static final long serialVersionUID = 1L;
 	private ArrayList<Person> onlineClients = new ArrayList<Person>() ;
	private ArrayList<Person> myClients ;
	private Person currentPerson = null;
	protected ServerImp() throws RemoteException {
		super();
 	}

	@Override
	public Person logIn(ClientInterface c,String username,String password) throws RemoteException {
		DB db = new DB(); 
		for(Person oc :onlineClients){
			if(oc.getUserName() == username && oc.getPassword() == password) 
				 return null;
		} 
		Person per = db.logIn(username,password);
 		if( per != null){
 			 db.setConnectState(per.getID(),ConnectState.ONLINE);
 			 per.setState(ConnectState.ONLINE);
 			 per.receiving = c;
  			 onlineClients.add(per);
 			 currentPerson = per;
 			 MainServer.setClient(per.getName()+" : is online ...");
 			 if(!onlineClients.isEmpty())
 			   onlineClients.forEach((client)->{
 				try {
 					System.setProperty("java.rmi.server.hostname","127.0.0.1");
					client.receiving.recieveState(ConnectState.ONLINE,per.getID());
				} catch (RemoteException e) {
 					e.printStackTrace();
				}
 			 });
     		 return per;
		}
 		else {
  			return null;
 		}
 	}
	 
	@Override
	public boolean sginUp(Person client) throws RemoteException {
		DB db = new DB(); 
		boolean flag = db.addNewClient(client);
 		return flag;
 	}

	@Override
	public boolean modifyProfile(Person client) throws RemoteException {
		DB db = new DB(); 
		boolean flag = db.ModifyProfile(client);
 		return flag;
	}

	@Override
	public ArrayList<Person> getPeopleList(int id) throws RemoteException {
		 DB db = new DB();
 		 myClients = db.getPepoleList(id);
  		 if(myClients.isEmpty()) {
  			return null;
 		 }
  		myClients.forEach((n)->{
  			try {
				n.setState(ConnectState.OFFLINE);
			} catch (RemoteException e) {
 				e.printStackTrace();
			}
  		});
     
   	 return myClients; 
 	}
  
	@Override
	public void sendMessage(Message message,  int receiverID) throws RemoteException {
		DB db = new DB(); 
		if(onlineClients.isEmpty()) {
			db.addNewMessage(message,message.getSenderId(), receiverID);
			return;
		}
 		onlineClients.forEach((c)->{
			try {
				System.setProperty("java.rmi.server.hostname","127.0.0.1");
					if(c.getID() == receiverID ) {
 					switch(message.getType()) {
						case FileType.MESSAGE: c.receiving.recieveMessage(message); break;
						case FileType.IMAGE: c.receiving.recieveImage(message); break;
						case FileType.DOCUMENT: c.receiving.recieveFile(message); break;
						case FileType.VIDEO:  c.receiving.recieveFile(message); break;
						case FileType.IMOGY: c.receiving.recieveImogy(message); break;
						default :
							c.receiving.recieveMessage(message); break;
					}
  					}
 				} catch (RemoteException e) {
 					e.printStackTrace();
				}
 		});
 		db.addNewMessage(message,message.getSenderId(), receiverID);
 	}
   
	@Override
	public void sendMessageToAll(Message message, int receiverID) throws RemoteException {
		DB db = new DB(); 
		if(onlineClients.isEmpty()) {
			db.addNewMessage(message,message.getSenderId(), receiverID);
			return;
		}
		onlineClients.forEach((c)->{
			try {
				System.setProperty("java.rmi.server.hostname","127.0.0.1");
				if(myClients.contains(c)) {
					switch(message.getType()) {
					case FileType.MESSAGE:c.receiving.recieveMessage(message); break;
					case FileType.IMAGE: c.receiving.recieveImage(message); break;
					case FileType.DOCUMENT:
					case FileType.VIDEO:  c.receiving.recieveFile(message); break;
					case FileType.IMOGY: c.receiving.recieveImogy(message); break;
					default :
					    c.receiving.recieveMessage(message); break;
					}
  				    db.addNewMessage(message, message.getSenderId(),c.getID());
 				}
   			} catch (RemoteException e) {
 				e.printStackTrace();
			}
		});
 	}

 
	@Override
	public void logOut(int id) throws RemoteException {
		DB db = new DB();
   		onlineClients.forEach((cl) -> {
			try {
				 System.setProperty("java.rmi.server.hostname","127.0.0.1");
				 cl.receiving.recieveState(ConnectState.OFFLINE,id);
				 if(cl.getID() == id) {
 				 db.setConnectState(id, ConnectState.OFFLINE);
 				 MainServer.removeLabels(cl.getName());
 				 onlineClients.remove(cl);
 				}
			} catch (RemoteException e) {
 				e.printStackTrace();
			}
		});
 	}

	@Override
	public Person addNewPersonToList(int id, int phoneNumber) throws RemoteException  {
		DB db = new DB();
		Person p = db.makeRelation(id, phoneNumber);
		if(p != null) {
 			return p;
		} else {
 			return null;
 		}
	}

	@Override
	public boolean removeAccount() throws RemoteException {
		DB db = new DB();
		boolean flag = db.removePerson(currentPerson.getID());
 		return flag;
 	}

	@Override
	public boolean removePersonFromList(int id , int personID) throws RemoteException {
		DB db = new DB();
		boolean flag = db.removePersonFromList( id ,  personID);
   		return flag;
	}

	@Override
	public boolean removeAllMessages(int senderID, int receiverID) throws RemoteException {
        DB  db = new DB();
    	boolean flag = db.removeMessages( senderID ,  receiverID);
     	return flag;
	}
	
	@Override
	public void refreshState() {
		onlineClients.forEach(oc->{
 				 try {
 					System.setProperty("java.rmi.server.hostname","127.0.0.1");
					oc.receiving.recieveState(ConnectState.ONLINE, oc.getID());
				} catch (RemoteException e) {
 					e.printStackTrace();
				}
  		 });
	}

	@Override
	public void sendState(String state, int senderID,int receiverID ) throws RemoteException {
		onlineClients.forEach((c)->{
  			try {
  			if(c.getID() == receiverID) {
  				c.receiving.recieveState(state, senderID);
  			}
  			}catch (RemoteException e) {
					e.printStackTrace();
			}
  		});
	}
  
}
