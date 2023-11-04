package oopproje;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class GameModes implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Map<String,Game> gameModes;
	
	public GameModes() {
		gameModes= new HashMap<String,Game>();
	}
	public String getKey(Game aGame) {
		for (Entry<String, Game> m : gameModes.entrySet()) {
			if(m.getValue()==aGame)
				return m.getKey();
		}
		return null;
	}
	
	public void addMode(String name,Game game) {
		if(!gameModes.containsKey(name)) {
			gameModes.put(name, game);
			System.out.println(name+ " adli oyun modu eklendi.");
		}
		else
			System.out.println(name+ " adli oyun modu zaten ekli.");
	}
	public Map<String,Game> returnGameModes(){
		return gameModes;
	}

	public void saveGameModes() {
		try {
			String fileName="GameModes.dat";
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName));
			writer.writeObject(gameModes.size());
			for (Entry<String, Game> aGame : gameModes.entrySet()) {
				writer.writeObject(aGame.getKey());
				writer.writeObject(aGame.getValue());
			}
			writer.close();
			System.out.println("Oyun ayarlari basari ile kaydedildi!!");
			
		}catch(IOException e) {
			System.out.println("Dosyaya kaydederken bir hata olustu!!");
			e.printStackTrace();
		}
	}
	public void getGameModes() throws ClassNotFoundException {
		try {
			String fileName="GameModes.dat";
			ObjectInputStream reader= new ObjectInputStream(new FileInputStream(fileName));
			int modeCount=(int)reader.readObject();
			for(int i=0;i<modeCount;i++) 
				gameModes.put((String)reader.readObject(),(Game)reader.readObject());
			reader.close();
			System.out.println("Oyun ayarlari basari ile dosyadan okundu!!");
			
		}catch(IOException e){
			System.out.println("Dosyayadan bilgi alinirken bir hata olustu!!");
			e.printStackTrace();
		}
	}
	
}
