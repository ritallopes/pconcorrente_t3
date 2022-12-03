package lock;

import java.util.LinkedHashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BathRoom {
	private static final int MAXPERSON = 5;
	
	private static BathRoom instance = new BathRoom(MAXPERSON);
	private final int capacity;
	private GenderPerson genderInBathRoom;
	private final LinkedHashSet<Person> persons;
	private Lock lock = new ReentrantLock();
	
	
	/**
	 * Construtor da classe Banheiro.
	 *
	 * @param maxperson capacidade máxima de pessoas no banheiro
	 */
	public BathRoom(int maxperson) {
		this.capacity = maxperson;
		this.genderInBathRoom = GenderPerson.EMPTY;
		this.persons = new LinkedHashSet<>();
	}
	
	/**
	 * Instancias da classe monitor.BathRoom
	 *
	 * @return Uma instância da classe
	 */
	public static BathRoom getInstance() {
		return instance;
	}
	/**
	 * Mostrar o sexo das pessoas que estão dentro do banheiro
	 *
	 * @return o sexo da pessoa que está dentro do banheiro
	 */
	public GenderPerson getGenderInBathRoom() {
		return genderInBathRoom;
	}
	
	/**
	 * Adicionar uma pessoa dentro do banheiro
	 *
	 * @param person A pessoa a entrar
	 */
	public void add(Person person) {
		this.lock.lock();
		try {
			if (this.isEmpty()) {
				this.genderInBathRoom = person.getGenderPerson();
			}
			if (!this.isFull() && this.getGenderInBathRoom().equals(person.getGenderPerson()) &&
					!this.persons.contains(person)) {
				if (this.persons.add(person)) {
					System.out.println(person.getName() + " entrou no banheiro");
				}
				if (this.isFull()) {
					System.out.println("Agora o banheiro encheu");
				}
			}
		} finally {
			this.lock.unlock();
		}
		
	}
	/**
	 * Remover uma pessoa dentro do banheiro
	 *
	 * @param person A pessoa a ser removida
	 */
	public void getOffPerson(Person person) {
		this.lock.lock();
		
		try {
			if (!this.isEmpty()) {
				if(this.persons.remove(person)){
					System.out.println(person.getName()+" saiu!");
				}
			}
			if(this.isEmpty()){
				System.out.println("O Banheiro está vazio");
				this.genderInBathRoom = GenderPerson.EMPTY;
			}
		} finally {
			this.lock.unlock();
		}
	}
	
	
	/**
	 * Saber se o banheiro está vazio
	 *
	 * @return um booleano true se estiver vazio
	 * e false se houver pessoas no banheiro
	 */
	public boolean isEmpty() {
		return this.persons.isEmpty();
	}
	
	/**
	 * Saber se o banheiro está cheio
	 *
	 * @return um booleano true se estiver cheio
	 * e false se ainda houver espaço no banheiro
	 */
	public boolean isFull() {
		return this.persons.size() == capacity;
	}
	/**
	 * Saber se determinado pessoa está no banheiro
	 *
	 * @param person quem é a pessoa sobre a qual quer saber se está no banheiro
	 * @return um booleano true se person estiver no banheiro
	 * e false se a pessoa não estiver
	 */
	public boolean personIsBathRoom(Person person) {
		return this.persons.contains(person);
	}
	
	
}
