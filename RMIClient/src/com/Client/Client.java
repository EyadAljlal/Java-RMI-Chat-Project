package com.Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import com.ClientForms.LoginForm;
import com.ServerAPI.ClientInterface;
import com.ServerAPI.Message;
import com.ServerAPI.ServerInterface;

public class Client implements ClientInterface {

	ServerInterface server;
	public Client() {
 		getTheService();
        try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null,"UnicastRemoteObject exportObject :"+e.toString());
		}
 	}
	private void getTheService() {
		try { 
			Registry reg = LocateRegistry.getRegistry();
			server = (ServerInterface) Naming.lookup("rmi://localhost:1098/"+ServerInterface.LOOKINGUP_NAME);
 			LoginForm.main(new String[] {});
			
  			} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Remote Exception :"+e.toString());
			}
	}
	@Override
	public void recieveMessage(Message message) throws RemoteException {
 		
	}
 
	@Override
	public void recieveFile(Message message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void recieveState(String state, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void recieveImage(Message message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void recieveImogy(Message message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
