package lock;

import java.util.ArrayList;
import java.util.List;

public class MainBathRoom {
	public static void main(String[] args) {
		int capacity = 5;
		if(Integer.parseInt(args[0]) > 0){
			capacity = Integer.parseInt(args[0]);
		}
		BathRoom bathRoom = new BathRoom(capacity);
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
				ex.printStackTrace();
			}
		});
	}
}
