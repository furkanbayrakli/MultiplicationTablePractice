package oopproje;
import java.util.ArrayList;
import java.util.AbstractList;


import java.io.*;
public class Users implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Child> childeren;
	private Adult admin;
	
	public Users(Adult admin) {
		this.admin=admin;
		childeren= new ArrayList<Child>();
	}
	public Users() {
		childeren= new ArrayList<Child>();
	}
	public ArrayList<Child> getChilderen() {
		return childeren;
	}
	public Adult getAdmin() {
		return admin;
	}
	public void setAdmin(Adult admin) {this.admin = admin;}
	public void saveUsers() {
		try {
			String fileName="Users.dat";
			ObjectOutputStream yazici = new ObjectOutputStream(new FileOutputStream(fileName));
			yazici.writeObject(admin);
			yazici.writeObject(childeren.size());
			for(Child aChild : childeren)
				yazici.writeObject(aChild);
			yazici.close();
			System.out.println("Kullanicilar basari ile kaydedildi!!");	
		}catch(IOException e) {
			System.out.println("Dosyaya kaydederken bir hata olustu!!");
			e.printStackTrace();
		}
	}
	
	public void getUsers() throws ClassNotFoundException {
		childeren.clear();
		try {
			String fileName="Users.dat";
			ObjectInputStream reader= new ObjectInputStream(new FileInputStream(fileName));
			admin =(Adult) reader.readObject();
			int childCount =(int) reader.readObject();
			for(int i=0;i<childCount;i++) {
				childeren.add((Child)reader.readObject());
			}
			reader.close();
			System.out.println("Kullanicilar basari ile dosaydan alindi!!");	
			
		}catch(IOException e){
			System.out.println("Dosyayadan bilgi alinirken bir hata olustu!!");
			e.printStackTrace();
		}
	}
}
