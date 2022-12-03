import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação de uma solução com programação concorrente para o problema do banheiro unissex
 *
 * @author <a href="mailto:rita_lino@outlook.com">Rita Lopes</a>
 */
public class Person implements Runnable {
	/**
	 * Banheiro que a pessoa deseja utilizar
	 */
	private final BathRoom bathRoom;
	
	/**
	 * Nome da pessoa que deseja entrar
	 */
	private final String name;
	/**
	 * Sexo da pessoa
	 */
	private final GenderPerson genderPerson;
	
	/**
	 * Variável indicando que a pessoa precisa entrar no banheiro
	 */
	private boolean needUseBathRoom;
	/**
	 * Variável indicando que a pessoa pode sair do banheiro
	 */
	private boolean wannaGoOut;
	
	/**
	 * Constructor.
	 *
	 * @param bathRoom     Bathroom available to use
	 * @param name         Person name
	 * @param genderPerson Person gender
	 */
	public Person(BathRoom bathRoom, String name, GenderPerson genderPerson) {
		this.bathRoom = bathRoom;
		this.name = name;
		this.genderPerson = genderPerson;
		
		this.needUseBathRoom = true;
		this.wannaGoOut = false;
	}
	
	/**
	 * Mostrar o nome da pessoa
	 *
	 * @return o nome
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Mostrar o sexo da pessoa
	 *
	 * @return sexo
	 */
	public final GenderPerson getGenderPerson() {
		return this.genderPerson;
	}
	/**
	 * Mostrar se a pessoa precisa usar o banheiro
	 *
	 * @return precisa ir ao banheiro
	 */
	public boolean getNeedUseBathRoom() {
		return needUseBathRoom;
	}
	/**
	 * Mostrar se a pessoa quer sair do banheiro
	 *
	 * @return quer sair do banheiro
	 */
	public boolean getWannaGoOut() {
		return wannaGoOut;
	}
	
	/**
	 * Entrar do banheiro
	 * Esse método adiciona a pessoa no banheiro
	 */
	public void enterBathRoom() {
		this.bathRoom.add(this);
		if (this.bathRoom.personIsBathRoom(this)) {
			try {
				TimeUnit.SECONDS.sleep((new Random()).nextInt(1) + 1);
				this.wannaGoOut = true;
				System.out.println(getName() + " usou o banheiro");
			} catch (InterruptedException ex) {
				Logger.getLogger(Person.class.getName())
						.log(Level.SEVERE, null, ex);
			}
			
		}
	}
	
	/**
	 * Sair do banheiro
	 * Esse método remove a pessoa do banheiro
	 */
	public void wannaGoOutOfBathRoom() {
		this.bathRoom.getOffPerson(this);
		this.needUseBathRoom = false;
		this.wannaGoOut = false;
	}
	
	/**
	 * Runs this operation.
	 * TODO Descrever melhor esse método
	 */
	@Override
	public void run() {
		System.out.println("Pessoa: " + this.getName());
		
		while (this.getNeedUseBathRoom()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
			}
			boolean thisGenderEnter = this.bathRoom.getGenderInBathRoom().equals(GenderPerson.EMPTY) || this.getGenderPerson().equals(this.bathRoom.getGenderInBathRoom());
			boolean BRIsFull = this.bathRoom.isFull();
			boolean thisPersonInBR = this.bathRoom.personIsBathRoom(this);
			if ((this.bathRoom.getGenderInBathRoom().equals(this.getGenderPerson())
					|| this.bathRoom.getGenderInBathRoom().equals(GenderPerson.EMPTY))
					&& !this.bathRoom.isFull()
					&& !this.bathRoom.personIsBathRoom(this)) {
				this.enterBathRoom();
			}
			if(this.getWannaGoOut()){
				this.wannaGoOutOfBathRoom();
			}
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Pessoa: \nNome: " + this.getName() + ", Sexo: " + this.getGenderPerson();
	}
}
