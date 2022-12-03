import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainBathRoom {
	public static void main(String[] args) {
		// BathRoom
		BathRoom bathRoom = BathRoom.getInstance();
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			
			persons.add(new Person(bathRoom, ("H" + i), GenderPerson.MALE));
			
			
		}
		for (int i = 0; i < 10; i++) {
			persons.add(new Person(bathRoom, ("M" + i), GenderPerson.FEMALE));
			
		}
		persons.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
			t.start();
		});
		persons.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
			try {
				t.join();
			} catch (InterruptedException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}
}
