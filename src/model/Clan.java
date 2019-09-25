package model;

import java.io.*;
import java.util.*;

public class Clan implements Serializable {

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

	public void setClanName(String clanName) {
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

	// Metodo para añadir una tecnica a un personaje

	public boolean addTechnique(String nameCharacter, Technique technique) {
		boolean agregado = false;
		Character actual = firstCharacter;
		while (actual != null && !agregado) {
			if (actual.getName().equals(nameCharacter)) {
				actual.addTechnique(technique);
				agregado = true;
			} else {
				actual = actual.getNext();
			}
		}
		return agregado;
	}

	// Metodo para mostrar el nombre de los personajes

	public String showNameCharacters() {
		String msg = "";
		Character actual = firstCharacter;
		while (actual != null) {
			msg += actual.getName() + "\n";
			actual = actual.getNext();
		}
		return msg;
	}

	// Metodo para eliminar un personaje

	public boolean deleteCharacter(String name) throws ExceptionNoExiste {
		boolean deleted = false;
		// Verifica que la lista no este vacia
		if (firstCharacter == null)
			throw new ExceptionNoExiste();
		// Verifica si el primer personaje es el que se busca eliminar
		if (firstCharacter.getName().equals(name)) {
			// El primer personaje ahora es el siguiente a ese
			firstCharacter = firstCharacter.getNext();
			deleted = true;
		}
		// El personaje que se necesita eliminar no es el primero de la lista
		else {
			// El personaje actual es el que le sigue al primero porque ya verificamos que
			// el primero no es
			Character actual = firstCharacter.getNext();
			while (actual != null) {
				if (actual.getName().equals(name)) {
					Character beforeActual = actual.getBefore();
					// el metodo le dice al personaje que invoca el metodo que el personaje
					// siguiente a el
					// es el siguiente siguiente por lo tanto elimina el personaje actual
					beforeActual.deleteNextOne();
					deleted = true;
				} else {
					actual = actual.getNext();
				}
			}
		}
		return deleted;
	}

	// Metodo para saber si un personaje pertence a un clan
	public boolean findCharacter(String nameCharacter) {
		Character actual = firstCharacter;
		boolean encontrado = false;
		while (actual != null && !encontrado) {
			if (actual.getName().equals(nameCharacter)) {
				encontrado = true;
			} else {
				actual = actual.getNext();
			}
		}
		return encontrado;
	}

	// Metodo para mostrar las tecnicas de un personaje en especifico
	public String showTechniques(String nameCharacter) {
		Character actual = firstCharacter;
		boolean encontrado = false;
		String msg = "";
		while (actual != null && !encontrado) {
			if (actual.getName().equals(nameCharacter)) {
				msg = actual.showTechniques();
				encontrado = true;
			} else {
				actual = actual.getNext();
			}
		}
		if (encontrado == false) {
			msg = "No fue posible encontrar el personaje";
		}
		return msg;
	}

	// Metodo para eliminar una tecnica de un personaje en especifico

	public boolean deleteTechnique(String nameCharacter, String nameT) {
		boolean delete = false;
		Character actual = firstCharacter;
		while (actual != null && !delete) {
			if (actual.getName().equals(nameCharacter)) {
				try {
					actual.deleteTechnique(nameT);
					delete = true;
				} catch (ExceptionNoExiste e) {
					e.printStackTrace();
				}
			} else {
				actual = actual.getNext();
			}
		}

		return delete;
	}

	// Metodo para modificar el nombre de un personaje

	public boolean changeCharacterName(String actualName, String newName) {
		boolean changed = false;
		Character actual = firstCharacter;
		while (actual != null && !changed) {
			if (actual.getName().equals(actualName)) {
				actual.setName(newName);
				changed = true;
			} else {
				actual = actual.getNext();
			}
		}
		return changed;
	}

	// Metodo para cambiar el poder de un personaje

	public boolean changeCharacterPower(String actualName, int newPower) {
		boolean changed = false;
		Character actual = firstCharacter;
		while (actual != null && !changed) {
			if (actual.getName().equals(actualName)) {
				actual.setPower(newPower);
				changed = true;
			} else {
				actual = actual.getNext();
			}
		}
		return changed;
	}

	// Metodo para cambiarle el nombre a una tecnica de un personaje

	public boolean changeTechniqueName(String characterName, String techniqueActualName, String techniqueNewName) {
		boolean changed = false;
		Character actual = firstCharacter;
		while (actual != null && !changed) {
			if (actual.getName().equals(characterName)) {
				actual.changeTechniqueName(techniqueActualName, techniqueNewName);
				changed = true;
			} else {
				actual = actual.getNext();
			}
		}
		return changed;
	}

	// Metodo para cambiar el poder de la tecnica de un personaje

	public boolean changeTechniquePower(String characterName, String techniqueActualName, int techniqueNewPower) {
		boolean changed = false;
		Character actual = firstCharacter;
		while (actual != null && !changed) {
			if (actual.getName().equals(characterName)) {
				actual.changeTechniquePower(techniqueActualName, techniqueNewPower);
				changed = true;
			} else {
				actual = actual.getNext();
			}
		}
		return changed;
	}

	// Metodo para ordenar las tecnicas

	public String ordenarTecnicas() {
		String msg = "";
		Character actual = firstCharacter;
		while (actual != null) {
			msg += actual.ordenarNombreTecnicas();
			actual = actual.getNext();
		}
		return msg;
	}

	// Metodo para ordenar los personajes por poder utilizando el metodo burbuja

	public String ordenarNombreCharacter() {
		String msg = "";
		if (firstCharacter != null) {
			if (firstCharacter.getNext() != null) {
				// Variable temporal
				Character temp = null;
				// Actual es el personaje donde debemos poner el menor
				Character actual = firstCharacter;
				// int j
				Character primero = firstCharacter;
				// int j+1
				Character segundo = firstCharacter.getNext();
				Character menor = primero;
				// Mientras que el personaje que le sigue al que recorre no sea null, de ser
				// null quiere decir que es el ultimo elemento
				while (primero.getNext() == null) {
					// Segundo nunca sera null porque primero nunca llegara a la ultima posicion
					while (segundo != null) {
						// Verificamos si el primero es mayor que el segundo
						if (primero.compareTo(segundo) > 0) {
							menor = segundo;
							// Se encarga de dar avance, como el segundo se mueve solo debemos modificar el
							// segundo
							segundo = primero.getNext();
						} else {
							// Seria el avance en caso de que el primero no sea mayor que el segundo
							primero = primero.getNext();
							segundo = primero.getNext();
						}
					} // Cierra el primer while que es el encargado de encontrar el menor

					temp = menor.getNext();
					actual.getNext().setBefore(menor);
					menor.getBefore().setBefore(actual);
					menor.setNext(actual.getNext());
					actual.setNext(temp);

					temp = menor.getBefore();
					menor.setBefore(actual.getBefore());
					actual.setBefore(temp);

					actual = actual.getNext();

				} // Cierra el segundo while
			}
		}
		return msg = showCharacterInformation();
	}

	// Metodo que muestra la informacion de los personajes

	public String showCharacterInformation() {
		String msg = "";
		Character actual = firstCharacter;
		while (actual != null) {
			msg += "     " + actual.getName() + "     " + "     " + actual.getPersonality() + "     " + "     "
					+ actual.getPower() + "     " + "\n";
			actual = actual.getNext();
		}
		return msg;
	}

	// Metodo para buscar una tecnica

	public String searchTechnique(String characterName, String techniqueName) {
		Character actual = firstCharacter;
		boolean encontrado = false;
		String msg = "";
		while (actual != null && !encontrado) {
			if (actual.getName().equals(characterName)) {
				msg = actual.searchTechnique(techniqueName);
				encontrado = true;
			} else {
				actual = actual.getNext();
			}
		}
		if (encontrado == false) {
			msg = "No existe ningun personaje con el nombre ingresado";
		}
		return msg;
	}

	// Metodo para buscar un personaje

	public String searchCharacter(String nameCharacter) {
		Character actual = firstCharacter;
		boolean encontrado = false;
		String msg = "";
		while (actual != null && !encontrado) {
			if (actual.getName().contentEquals(nameCharacter)) {
				msg = "La informacion del personaje buscado es la siguiente \n" + "El nombre del personaje es "
						+ actual.getName() + "\n" + "El poder del personaje es " + actual.getPower() + "\n"
						+ "La personalidad del personaje es " + actual.getPersonality() + "\n"
						+ "La fecha de creacion del personaje es " + actual.getDateCreation();
					encontrado = true;
			} else {
				actual = actual.getNext();
			}
		}
		if (encontrado == false) {
			msg = "No existe personaje con el nombre ingresado";
		}
		return msg;
	}

//	segundo.getNext().setBefore(primero);
//	primero.setNext(segundo.getNext());
//	segundo.setBefore(primero.getBefore());
//	primero.setBefore(segundo);
//	segundo.setNext(primero);

} // Cierra la clase
