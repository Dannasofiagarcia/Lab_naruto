package model;

public class Technique {

	// ATRIBUTOS

	private String name;
	private int power;

	// RELACIONES

	private Technique next;

	// CONSTRUCTOR

	public Technique(String name, int power) {
		this.name = name;
		this.power = power;
	}

	// METODOS

	// Metodos get y set

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Technique getNext() {
		return next;
	}

	public void setNext(Technique next) {
		this.next = next;
	}
	
	public void addBefore(Technique technique) {
		technique.next = next;
		next = technique;
	}
	

}
