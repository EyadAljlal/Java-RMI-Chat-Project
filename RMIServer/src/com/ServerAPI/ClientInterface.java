package com.ServerAPI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
	
	public void recieveMessage(Message message ) throws RemoteException;
  	public void recieveState(String state,int id) throws RemoteException;
  	public void recieveImage(Message message ) throws RemoteException;
    public void recieveFile(Message message ) throws RemoteException;
    public void recieveImogy(Message message ) throws RemoteException;
    public void exit() throws RemoteException;
 
}
