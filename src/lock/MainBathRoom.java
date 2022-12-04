package lock;

import java.util.ArrayList;
import java.util.List;

public class MainBathRoom {
	public static void main(String[] args) {
		int capacity = 5;
		int numberMan = 5;
		int numberWoman = 5;
		if (args.length > 1 && (Integer.parseInt(args[0]) > 0)) {
			capacity = Integer.parseInt(args[0]);
		}
		if (args.length > 1 && Integer.parseInt(args[1]) > 0) {
			numberMan = Integer.parseInt(args[1]);
		}
		if (args.length > 2 && Integer.parseInt(args[2]) > 0) {
			numberWoman = Integer.parseInt(args[2]);
		}
		BathRoom bathRoom = new BathRoom(capacity);
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < numberMan; i++) {
			persons.add(new Person(bathRoom, ("H" + i), GenderPerson.MALE));
		}
		for (int i = 0; i < numberWoman; i++) {
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
