package DataBase;

 
import javax.swing.JOptionPane;

import com.HelpfulClass.ConnectState;
import com.ServerAPI.FileType;
import com.ServerAPI.Message;
import com.ServerAPI.Person;

import java.sql.Statement;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class DB {

	   private Connection con = null;
	   private PreparedStatement pst = null;
	   private Statement st = null;
	   private String sql = null;
	   private ResultSet rs = null;
	 
	public DB() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/whatsapp_db","Eyad","1234");
 		}catch(Exception exc) {
			JOptionPane.showMessageDialog(null,"Error Connection","Database",JOptionPane.ERROR_MESSAGE);  	
			} 
	}  
	
	public void close() {
 		 try{
		     con.close();
			 pst.close();
			 st.close();
 			 rs.close();
 			}catch(Exception exc) {
 				JOptionPane.showMessageDialog(null,"Error Clossing: "+exc.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
	 		}
	}
	
 	public Person logIn(String username,String password) throws RemoteException {
  		try {
  			 Person client = new Person();
   			sql ="Select * from person where username=? and password=?";
			pst = con.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,password);
			rs = pst.executeQuery();
  			while(rs.next()) {
			 if(rs.getString(4).equals(username) && rs.getString(5).equals(password)) {
 				 client.setID(rs.getInt(1));
				 client.setName(rs.getString(2));
				 client.setPhoneNumber(rs.getInt(3));
				 client.setUserName(rs.getString(4));
				 client.setPassword(rs.getString(5));
				 client.setImage(rs.getBytes(6));
				 client.setState(rs.getString(7));
	 			return client;
			 }
			 else
				 return null;
			 }
   		}
  		catch(SQLException ex) {
  			JOptionPane.showMessageDialog(null,"Login Exception :"+ex.toString());
   		} 
   		return null;
 	 }
	public void setConnectState(int id ,String state)   {
		 try {
		sql ="Update person set connect_state = ? where person_id = ? ";
		pst = con.prepareStatement(sql);
		pst.setString(1,state);
		pst.setInt(2,id);
 		pst.executeUpdate();
		}
		 catch(SQLException ex) {
			 JOptionPane.showMessageDialog(null,"Error set State: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
 		 } 
  	}
	public boolean addNewClient(Person client) throws RemoteException {
		try {
		sql ="INSERT INTO  person (name ,  phone_number ,  username ,  password ,  Image ,connect_state) VALUES (?,?,?,?,?,?)";
		pst = con.prepareStatement(sql);
		pst.setString(1,client.getName());
		pst.setInt(2, client.getPhoneNumber());
		pst.setString(3, client.getUserName());
		pst.setString(4,client.getPassword());
 		pst.setBytes(5, client.getImage());
 		pst.setString(6, ConnectState.ONLINE);
		if(pst.executeUpdate() > 0 )
			return true;
		else
			return false;
 		}catch(SQLException ex) {
 			JOptionPane.showMessageDialog(null,"Error Proccessing: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE);  
		}
 		return false;
	}
	
	public boolean ModifyProfile(Person client) throws RemoteException {
		try {
		sql ="UPDATE  person  SET "
				+ " name = ?,"
				+ "phone_number = ?,"
				+ "username = ?,"
				+ "password = ?,"
				+ " Image = ? WHERE person_id = ? ";
		
		pst = con.prepareStatement(sql);
		pst.setString(1,client.getName());
		pst.setInt(2, client.getPhoneNumber());
		pst.setString(3, client.getUserName());
		pst.setString(4,client.getPassword());
 		pst.setBytes(5, client.getImage());
 		pst.setInt(6, client.getID());
		if(pst.executeUpdate() > 0 )
			return true;
		else
			return false;
 		}catch(SQLException ex) {
 			JOptionPane.showMessageDialog(null,"Error Proccessing: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE);  		 
  		}
 		return false;
	}
 
	
 	public ArrayList<Person> getPepoleList(int ID) throws RemoteException {
		 ArrayList<Person> client = new ArrayList<Person>();
	try {
	    sql ="select  *  from person as p "
	    		+ "inner join person_chatting_relation as pcr on pcr.people_id = p.person_id "
	    		+ "where pcr.person_id = ? ";
		pst = con.prepareStatement(sql);
		pst.setInt(1,ID);
 		rs = pst.executeQuery();
 	  
		while(rs.next()) {
		Person c = new Person();
		  c.setID(rs.getInt("person_id"));
  		  c.setName(rs.getString("name"));
		  c.setPhoneNumber(rs.getInt("phone_number"));
		  c.setImage(rs.getBytes("image"));
		  c.setMessages(new DB().getMessages(ID,rs.getInt("person_id")));
		  c.setState(rs.getString("connect_state"));
 		  client.add(c);
		 }
	}catch(SQLException ex) {
		JOptionPane.showMessageDialog(null,"Error Proccessing: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE);  
 	}
 	return client;
	}
	
	private ArrayList<Message> getMessages(int senderID,int receiverID) throws RemoteException{
		 ArrayList<Message> msg = new ArrayList<Message>();
			try {
			    sql ="SELECT description , sending_time, file_message_flage, sender_id FROM messages "
			    		+ " where (sender_id = ? and reciver_id = ?) or (sender_id = ? and reciver_id = ?);";
				pst = con.prepareStatement(sql);
				pst.setInt(1,senderID);
				pst.setInt(2,receiverID);
				pst.setInt(3,receiverID);
				pst.setInt(4,senderID);
		 		rs = pst.executeQuery();
		 	  
				while(rs.next()) {
 				   Message m = new Message();
				    switch(rs.getInt("file_message_flage")) {
				    case FileType.MESSAGE :
				    	m.isFile(false);
				    	m.setMessage(rs.getString("description"));
				    	m.setType(FileType.MESSAGE);break;
				    case FileType.DOCUMENT :
				    	m.isFile(true);
				    	m.setFileName(rs.getString("description"));
				    	m.setType(FileType.DOCUMENT); break;
				    case FileType.VIDEO :
				    	m.isFile(true);
				    	m.setFileName(rs.getString("description"));
				    	m.setType(FileType.VIDEO);break;
				    case FileType.IMOGY :
				    	m.isFile(true);
				    	m.setFileName(rs.getString("description"));
				    	m.setType(FileType.IMOGY);break;
				    case FileType.IMAGE :
				    	m.isFile(true);
				    	m.setFileName(rs.getString("description"));
				    	m.setType(FileType.IMAGE);break;
				    }
 		 		   m.setDate(rs.getString("sending_time"));
  		 		   m.setSenderId(rs.getInt("sender_id"));
		 		   msg.add(m);
				 }
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null,"Error Proccessing: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
			}
 			return msg;
	}
	
	public void addNewMessage(Message msg,int senderID,int receiverID) throws RemoteException{
 			try {
			    sql ="INSERT INTO  messages (description , sending_time, file_message_flage,sender_id,reciver_id)"
			    		+ " values(?,?,?,?,?) ";
				pst = con.prepareStatement(sql);
				if(msg.isFile())
					pst.setString(1, msg.getFileName());
				else
					pst.setString(1, msg.getMessage());
				pst.setString(2, msg.getDate());
				pst.setInt(3, msg.getType());
				pst.setInt(4,senderID);
				pst.setInt(5,receiverID);
 		 		pst.executeUpdate();
		 	   
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null,"Error Proccessing: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
 			}
  	}
	
	public Person makeRelation(int id, int phoneNumber) throws RemoteException  {
		
		Person c;
		try {
			c = new DB().getClientByPhoneNo(phoneNumber);
			sql= "INSERT INTO  person_chatting_relation ( person_id ,  people_id ) "
					+ "VALUES (?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, c.getID());
 			if(pst.executeUpdate() > 0)
				return c;
 		} catch(SQLException ex) {
 			JOptionPane.showMessageDialog(null,"Error Proccessing: "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE);	 
		}
		
		return null;
	}
	
	private Person getClientByPhoneNo(int phoneNumber) throws RemoteException {
		
		Person c = new Person();
		
		try {
			sql ="select  person_id, name , phone_number, image from person "
		    		+ "where phone_number = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1,phoneNumber);
			rs = pst.executeQuery();
			while(rs.next()) {
		 		  c.setID(rs.getInt("person_id"));
		  		  c.setName(rs.getString("name"));
				  c.setPhoneNumber(rs.getInt("phone_number"));
				  c.setImage(rs.getBytes("image"));
				  return c;
		 		}
		}  catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Error new Message : "+ex.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
 			}
  		return null;
 	}
	 
	public boolean removePerson(int id) {
 		try {
			sql = "delete from person where person_id = ?";
	 		pst = con.prepareStatement(sql);
	 		pst.setInt(1, id);
	 		if(pst.executeUpdate()>0)
	 			return true;
	 		else 
	 			return false;
	 		 
 		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Error in remove person : "+e.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
 		}
 		return false;
	}

	public boolean removePersonFromList(int id, int personID) {
		try {
			sql = "delete from person_chatting_relation where person_id = ? and people_id = ? ;";
	 		pst = con.prepareStatement(sql);
	 		pst.setInt(1, id);
	 		pst.setInt(2, personID);
	 		if(pst.executeUpdate()>0)
	 			return true;
	 		else 
	 			return false;
	 		 
 		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Error in remove person : "+e.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
 		}
 		return false;
 	}

	public boolean removeMessages(int senderID, int receiverID) {
		try {
			sql = "delete from messages "
				+ "where (sender_id = ? and reciver_id = ?) or (sender_id = ? and reciver_id = ?);";
	 		pst = con.prepareStatement(sql);
	 		pst.setInt(1,senderID);
			pst.setInt(2,receiverID);
			pst.setInt(3,senderID);
			pst.setInt(4,receiverID);
	 		if(pst.executeUpdate()>0)
	 			return true;
	 		else 
	 			return false;
	 		 
 		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Error in remove person : "+e.getMessage(),"Database",JOptionPane.ERROR_MESSAGE); 
 		}
 		return false;
	}
   }

