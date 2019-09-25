package model;

import java.io.Serializable;
import java.util.*;

public class Character implements Serializable, Comparable<Character> {

	// ATRIBUTOS

	private String name;
	private String personality;
	private Date dateCreation;
	private int power;

	// RELACIONES

	// Relacion con la lista simple de tecnicas
	private Technique firstTechniques;

	// Relacion con el elemento siguiente
	private Character next;
	// Relacion con el elemento anterior
	private Character before;

	// CONSTRUCTOR

	public Character(String name, String personality, Date dateCreation, int power) {
		this.name = name;
		this.personality = personality;
		this.dateCreation = dateCreation;
		this.power = power;
	}

	// METODOS

	// Get y set

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Technique getFirstTechniques() {
		return firstTechniques;
	}

	public void setFirstTechniques(Technique techniques) {
		this.firstTechniques = techniques;
	}

	public Character getNext() {
		return next;
	}

	public void setNext(Character next) {
		this.next = next;
	}

	public Character getBefore() {
		return before;
	}

	public void setBefore(Character before) {
		this.before = before;
	}

	// Metodo para añadir antes del actual

	public void addBefore(Character character) {
		if (before != null)
			// Se agrega el personaje del parametro despues del anterior del personaje que
			// invoca el metodo
			before.next = character;

		// El personaje antes del personaje por parametro es null, a eso le asignamos el
		// personaje anterior del personaje que invoca el metodo
		character.before = before;
		// El personaje despues del personaje por paramaetro es null, a eso le asignamos
		// el personaje que invoca el metodo
		character.next = this;
		// Al personaje anterior del personaje que invoca el metodo le asignamos el
		// personaje por parametro
		before = character;
	}

	// Metodo para añadir despues del actual

	public void addAfter(Character character) {
		// El siguiente del personaje por parametro es el siguiente del personaje que
		// invoca el metodo
		character.next = next;
		if (next != null)
			// El anterior del personaje siguiente del personaje que invoca el metodo es el
			// personaje por parametro
			next.before = character;

		// El personaje anterior del personaje por parametro es el personaje que invoca
		// el metodo
		character.before = this;
		// El personaje siguiente del personaje que invoca el metodo es el personaje por
		// parametro
		next = character;
	}

	// Metodo para añadir una tecnica a un personaje

	public void addTechnique(Technique technique) {
		// Si no hay existe una primera tecnica, la tecnica que se desea agregar queda
		// de primera
		if (firstTechniques == null)
			firstTechniques = technique;

		else {
			// Busca la ultima tecnica de la lista
			Technique lastTechnique = findLastOne();
			// Agrega la tecnica despues de la ultima tecnica
			lastTechnique.addBefore(technique);
		}
	}

	// Metodo que busca la ultima tecnica de la lista

	public Technique findLastOne() {
		Technique actual = firstTechniques;

		if (actual != null) {
			// Se hace el while hasta que el dar siguiente sea distinto de null, si dar
			// siguiente es null quiere
			// decir que no hay más elementos por lo tanto el actual es el último
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
		}
		return actual;
	}

	// Metodo que muestra las tecnicas de un personaje

	public String showTechniques() {
		String msg = "";
		Technique actual = firstTechniques;

		while (actual != null) {
			msg += actual.getName() + "\n";
			actual = actual.getNext();
		}
		return msg;
	}

	// Metodo para eliminar el personaje siguiente
	public void deleteNextOne() {
		next.next.setBefore(this);
		next = next.next;
	}

	// Metodo que elimina una tecnica en especifico

	public void deleteTechnique(String nameT) throws ExceptionNoExiste {
		if (firstTechniques == null)
			throw new ExceptionNoExiste();

		// Verifica si la tecnica a eliminar es la primera de la lista
		if (firstTechniques.getName().equals(nameT)) {
			// la primera tecnica pasa a ser la siguiente
			firstTechniques = firstTechniques.getNext();
		}
		// La tecnica no fue la primera de la lista, por lo tanto debe ser una de medio
		else {
			// Actual es el siguiente al primero porque ya se verifico que el primero no
			// coincide con el nombre por parametro
			Technique actual = firstTechniques.getNext();
			while (actual != null) {
				if (actual.getName().equals(nameT)) {
					Technique before = findBeforeOne(nameT);
					before.setNext(actual.getNext());
				} else {
					actual = actual.getNext();
				}
			}
		}
	}

	// Este metodo encuentra el anterior

	public Technique findBeforeOne(String name) {
		Technique anterior = null;
		Technique actual = firstTechniques;
		// El metodo recorre mientras que el nombre de actual y el nombre por parametro
		// no sean iguales
		// si son iguales no entra al recorrido porque ya no seria el anterior
		while (actual != null && actual.getName() != name) {
			anterior = actual;
			// Este metodo se encarga de recorrer
			actual = actual.getNext();
		}

		return anterior;
	}
	
	//El metodo se encarga de cambiarle el nombre a una tecnica en especifico
	
	public boolean changeTechniqueName(String techniqueActualName, String techniqueNewName) {
		boolean changed = false;
		Technique actual = firstTechniques;
		while(actual != null && !changed) {
			if(actual.getName().equals(techniqueActualName)) {
				actual.setName(techniqueNewName);
				changed = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return changed;
	}
	
	//El metodo se encarga de cambiarle el poder a una tecnica en especifico
	
	public boolean changeTechniquePower(String techniqueActualName, int techniqueNewPower) {
		boolean changed = false;
		Technique actual = firstTechniques;
		while(actual != null && !changed) {
			if(actual.getName().equals(techniqueActualName)) {
				actual.setPower(techniqueNewPower);
				changed = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return changed;
	}
	
	//Ordenamiento seleccion para ordenar el nombre de las tecnicas
	
	public String ordenarNombreTecnicas() {
		String msg = "";
		
		Technique primera = firstTechniques;
		Technique segunda = firstTechniques.getNext();
		Technique menor = primera;
		while(primera != null && segunda != null) {
			//Verificamos si primera es mayor que segunda
			if(primera.compare(primera, segunda) > 0) {
				//Si primera es mayor que segunda quiere decir que el menor es segunda
				primera.setNext(segunda.getNext());
				segunda.setNext(primera);
				segunda = primera.getNext();
			}
			else {
				primera = primera.getNext();
				segunda = primera.getNext();
			}
		}
		return msg = showTechnique();
	}
	
	//Metodo para mostrar la informacion de las tecnicas ordenadas
	
	public String showTechnique() {
		String msg = "";
		Technique actual = firstTechniques;
		while(actual != null) {
			msg += "     " + actual.getName() + "     " + "     " + actual.getPower() + "     " + "\n";
			actual = actual.getNext();
		}
		return msg;
	}
	
	//Metodo para mostrar la información de una tecnica en especifico
	
	public String searchTechnique(String techniqueName) {
		boolean encontrado = false;
		String msg = "";
		Technique actual = firstTechniques;
		while(actual != null && !encontrado) {
			if(actual.getName().equals(techniqueName)) {
				msg = "La informacion de la tecnica buscado es la siguiente \n" + "El nombre de la tecnica es " + actual.getName() + "\n" + "El poder de la tecnia es " + actual.getPower() + "\n";
				encontrado = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		if(encontrado == false) {
			msg = "No existe personaje con el nombre ingresado";
		}
		return msg;	
	}
	

//	//Poderes
//	@Override
//	public int compare(Character primero, Character segundo) {
//		return primero.getPower();
//	}

	//Nombre
	@Override
	public int compareTo(Character character) {
		return name.compareToIgnoreCase(character.getName());
	}
	
} // Cierra la clase
