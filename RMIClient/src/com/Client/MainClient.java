package com.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
 import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import com.ClientForms.LoginForm;
import com.ClientForms.PersonMenuForm;
import com.ClientForms.SginUp;
import com.ServerAPI.Person;
import com.ServerAPI.ServerInterface;

 public class MainClient {
 
	private static ServerInterface server;
	private MainClient() {}
	public static void main(String[] args) {
		try { 
			Registry reg = LocateRegistry.getRegistry();
			server = (ServerInterface) Naming.lookup("rmi://localhost:1098/"+ServerInterface.LOOKINGUP_NAME);
 			LoginForm.main(new String[] {});
   			} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Remote Exception :"+e.toString());
			}
		}
	public static ServerInterface getServer() {
		return server;
	}
}
