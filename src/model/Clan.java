package model;

import java.io.*;
import java.util.*;

public class Clan {

	// ATRIBUTOS

	private String clanName;

	// RELACIONES

	// Es la relacion con la lista de personajes
	private Character firstCharacter;

	// CONSTRUCTOR

	public Clan(String clanName) {
		this.clanName = clanName;
	}

	// METODOS

	// Get y set

	public Character getFirstCharacter() {
		return firstCharacter;
	}

	public void setFirstCharacter(Character firstCharacter) {
		this.firstCharacter = firstCharacter;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String ClanName) {
		this.clanName = clanName;
	}

	// Metodo toString

	@Override
	public String toString() {
		return "Clan [name=" + clanName + ", character=" + firstCharacter + "]";
	}

	// Metodo para añadir un personaje ordenandolos por poder

	public void addCharacter(Character character) {
		if (firstCharacter == null)
			firstCharacter = character;
		else if (firstCharacter.getPower() >= character.getPower()) {
			firstCharacter.addBefore(character);
			firstCharacter = character;
		} else {
			Character characterTemp0 = null;
			Character characterTemp1 = firstCharacter;
			while (characterTemp1 != null && characterTemp1.getPower() < character.getPower()) {
				characterTemp0 = characterTemp1;
				characterTemp1 = characterTemp1.getNext();
			}
			characterTemp0.addBefore(character);
		}
	}
	
	//Metodo para añadir una tecnica a un personaje
	
	public boolean addTechnique(String nameCharacter, Technique technique) {
		boolean agregado = false;
		Character actual = firstCharacter;
		while(actual != null && !agregado) {
			if(actual.getName().equals(nameCharacter)) {
				actual.addTechnique(technique);
				agregado = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return agregado;
	}
	
	//Metodo para mostrar el nombre de los personajes
	
	public String showNameCharacters() {
		String msg = "";
		Character actual = firstCharacter;
		while(actual != null) {
			msg += actual.getName() + "\n";
			actual = actual.getNext();
		}
		return msg;
	}
	
	//Metodo para eliminar un personaje
	
	public boolean deleteCharacter(String name) throws ExceptionNoExiste {
		boolean deleted = false;
		//Verifica que la lista no este vacia
		if(firstCharacter == null)
			throw new ExceptionNoExiste();
		//Verifica si el primer personaje es el que se busca eliminar
		if(firstCharacter.getName().equals(name)) {
			//El primer personaje ahora es el siguiente a ese
			firstCharacter = firstCharacter.getNext();
			deleted = true;
		}
		//El personaje que se necesita eliminar no es el primero de la lista
		else {
			//El personaje actual es el que le sigue al primero porque ya verificamos que el primero no es
			Character actual = firstCharacter.getNext();
			while(actual != null) {
				if(actual.getName().equals(name)) {
					Character beforeActual = actual.getBefore();
					//el metodo le dice al personaje que invoca el metodo que el personaje siguiente a el
					//es el siguiente siguiente por lo tanto elimina el personaje actual
					beforeActual.deleteNextOne();
					deleted = true;
				}
				else {
					actual = actual.getNext();
				}
			}
		}
		return deleted;
	}
	
	//Metodo para saber si un personaje pertence a un clan
	public boolean findCharacter(String nameCharacter) {
		Character actual = firstCharacter;
		boolean encontrado = false;
		while(actual != null && !encontrado) {
			if(actual.getName().equals(nameCharacter)) {
				encontrado = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return encontrado;
	}
	
	
	//Metodo para mostrar las tecnicas de un personaje en especifico
	public String showTechniques(String nameCharacter) {
		Character actual = firstCharacter;
		boolean encontrado = false;
		String msg = "";
		while(actual != null && !encontrado) {
			if(actual.getName().equals(nameCharacter)) {
				msg = actual.showTechniques();
				encontrado = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		if(encontrado == false) {
			msg = "No fue posible encontrar el personaje";
		}
		return msg;
	}
	
	//Metodo para eliminar una tecnica de un personaje en especifico
	
	public boolean deleteTechnique (String nameCharacter, String nameT) {
		boolean delete = false;
		Character actual = firstCharacter;
		while(actual != null && !delete) {
			if(actual.getName().equals(nameCharacter)) {
				try {
					actual.deleteTechnique(nameT);
					delete = true;
				} catch (ExceptionNoExiste e) {
					e.printStackTrace();
				}
			}
			else {
				actual = actual.getNext();
			}
		}
		
		return delete;
	}
	
	//Metodo para modificar el nombre de un personaje
	
	public boolean changeCharacterName(String actualName, String newName) {
		boolean changed = false;
		Character actual = firstCharacter;
		while(actual != null && !changed) {
			if (actual.getName().equals(actualName)) {
				actual.setName(newName);
				changed = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return changed;
	}
	
	//Metodo para cambiar el poder de un personaje
	
	public boolean changeCharacterPower(String actualName, int newPower) {
		boolean changed = false;
		Character actual = firstCharacter;
		while(actual != null && !changed) {
			if (actual.getName().equals(actualName)) {
				actual.setPower(newPower);
				changed = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return changed;
	}
	
	//Metodo para cambiarle el nombre a una tecnica de un personaje
	
	public boolean changeTechniqueName(String characterName, String techniqueActualName, String techniqueNewName) {
		boolean changed = false;
		Character actual = firstCharacter;
		while(actual != null && !changed) {
			if(actual.getName().equals(characterName)) {
				actual.changeTechniqueName(techniqueActualName, techniqueNewName);
				changed = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return changed;
	}
	
	//Metodo para cambiar el poder de la tecnica de un personaje
	
	public boolean changeTechniquePower(String characterName, String techniqueActualName, int techniqueNewPower) {
		boolean changed = false;
		Character actual = firstCharacter;
		while(actual != null && !changed) {
			if(actual.getName().equals(characterName)) {
				actual.changeTechniquePower(techniqueActualName, techniqueNewPower);
				changed = true;
			}
			else {
				actual = actual.getNext();
			}
		}
		return changed;
	}

} // Cierra la clase
