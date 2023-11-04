package oopproje;

public class Main {
// sistemde 1 admin (furkan şifresi: 1234) ve 2 çocuk (harun,emre) kayıtlıdır.

	public static void main(String[] args) throws ClassNotFoundException {

		Users users=new Users();
		GameModes gameModes= new GameModes();
		gameModes.getGameModes();
		users.getUsers();
		new oopproje.Login(users,gameModes);

	}

}
