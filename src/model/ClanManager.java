package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClanManager {

	// CONSTANTES

	public final static String SP = File.separator;
	public final static String RUTA_DATOS = "C:" + SP + "Users" + SP + "Danna García" + SP + "Documents" + SP
			+ "narutoLab" + SP + "data";

	// RELACIONES

	private ArrayList<Clan> clans;

	// CONSTRUCTOR

	public ClanManager() {
		clans = new ArrayList<Clan>();
		deserialization();
		
	}

	// METODOS

	// Get y set

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}

	// Metodo para verificar que el nombre de un club no sea el mismo que de un
	// personaje

	public boolean verificarNombrePersonaje(String nameClan, String nameCharacter) {
		boolean nombresIguales = false;
		for (int i = 0; i < clans.size(); i++) {
			if (clans.get(i).getClanName().equals(nameClan)) {
				Clan clan = clans.get(i);
				if (clan.getClanName().equals(nameCharacter)) {
					nombresIguales = true;
				}
			}
		}
		return nombresIguales;
	}

	// Metodo que verifica que el nombre de un clan no sea igual al nombre de otro
	// clan

	public boolean verificarNombreClan(String name) {
		boolean nombresIguales = false;
		for (int i = 0; i < clans.size(); i++) {
			if (clans.get(i).getClanName().equals(name)) {
				nombresIguales = true;
			}
		}
		return nombresIguales;
	}

	// Metodo para añadir los clanes

	public void addClan(Clan clan) throws ExceptionNombreIgual {
		if (clan != null) {
			if (verificarNombreClan(clan.getClanName()) == true) {
				throw new ExceptionNombreIgual(clan.getClanName());
			} else {
				clans.add(clan);
			}
		} // Cierra la condición de que el clan no sea null
	}

	// Metodo para añadir un personaje verificando que este personaje no tiene el
	// mismo nombre que el clan

	public void addCharacter(Character character, String clanName) throws ExceptionNombreIgual {
		boolean agregado = false;
		for (int i = 0; i < clans.size() && !agregado; i++) {
			if (clans.get(i).getClanName().equals(clanName)) {
				if (verificarNombrePersonaje(clanName, character.getName()) == false) {
					clans.get(i).addCharacter(character);
					agregado = true;
				} else {
					throw new ExceptionNombreIgual(character.getName());
				}
			}
		}
	}

	// Metodo para eliminar un clan

	public void delateClan(Clan clan) {
		if (clan != null) {
			for (int i = 0; i < clans.size(); i++) {
				if (clans.get(i).getClanName().equals(clan.getClanName())) {
					Clan clanEliminar = clans.get(i);
					clans.remove(clanEliminar);
				}
			}
		}
	}

	// Metodo para serializar la información del programa

	public void serialization() {
		String nombreArchivo = "Naruto.txt";
		String rutaArchivo = RUTA_DATOS + SP + nombreArchivo;
		File archivoNaruto = new File(rutaArchivo);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoNaruto));
			oos.writeObject(clans);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodo para deserializar la información del programa

	public void deserialization() {
		try {
			String nombreArchivo = "Naruto.txt";
			String rutaArchivo = RUTA_DATOS + SP + nombreArchivo;
			File archivoNaruto = new File(rutaArchivo);

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoNaruto));
			clans = (ArrayList<Clan>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	//Metodo para agregar una tecnica a un personaje
	
	public void addTechnique(Technique technique, String nameCharacter) {
		boolean agregado = false;
		for(int i = 0; i < clans.size() && !agregado; i++) {
			if(clans.get(i).addTechnique(nameCharacter, technique) == true);
			agregado = true;
		}
	}
	
	//Metodo que muestra el nombre de todos los clanes
	
	public String showClans() {
		String msg = "";
		for(int i = 0; i < clans.size(); i++) {
			msg += clans.get(i).getClanName() + "\n";
		}
		return msg;
	}
	
	//Metodo que muestra el nombre de todos los personajes
	
	public String showCharactersName() {
		String msg = "";
		for(int i = 0; i < clans.size(); i++) {
			msg += clans.get(i).showNameCharacters();
		}
		return msg;
	}
	
	//Metodo para eliminar un clan
	
	public String deleteClan(String name) {
		String msg = "";
		boolean deleted = false;
		for(int i = 0; i < clans.size() && !deleted; i++) {
			if(clans.get(i).getClanName().equals(name)) {
				clans.remove(i);
				deleted = true;
			}
		}
		if(deleted = true) {
			msg = "El clan fue eliminado con éxito";
		}
		else { 
			msg = "No fue posible eliminar el clan";
		}
		return msg;
	}
	
	//Metodo para eliminar un personaje
	
	public String deleteCharacter(String name) throws ExceptionNoExiste {
		String msg = "";
		boolean deleted = false;
		for(int i = 0; i < clans.size() && !deleted; i++) {
			if(clans.get(i).deleteCharacter(name) == true) {
				deleted = true;
			}
		}
		if(deleted = true) {
			msg = "El personaje " + name + " fue eliminado con éxito";
		}
		else { 
			msg = "No fue posible eliminar el personaje";
		}
		return msg;
	}
	
	//Metodo para mostrar las tecnicas de un personaje
	
	public String showTechniques(String nameCharacter) {
		String msg = "";
		boolean find = false;
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).findCharacter(nameCharacter) == true) {
				msg = clans.get(i).showTechniques(nameCharacter);
			}
		}
		return msg;
	}
	
	//Metodo para eliminar una tecnica de un personaje
	
	public String deleteTechnique (String nameCharacter, String nameT) {
		String msg = "";
		boolean delete = false;
		for(int i = 0; i < clans.size() && !delete; i++){
			if(clans.get(i).deleteTechnique(nameCharacter, nameT) == true) {
				delete = true;
				msg = "La tecnica " + nameT + " fue eliminada correctamente del personaje " + nameCharacter; 
			}
			else {
				msg = "La tecnica no pudo ser eliminada";
			}
		}
		return msg;
	}
	
	//Metodo para cambiarle el nombre a un clan
	
	public String changeClanName(String nameActual, String newName) {
		String msg = "";
		boolean changed = false;
		for(int i = 0; i < clans.size() && !changed; i++) {
			if(clans.get(i).getClanName().equals(nameActual)) {
				clans.get(i).setClanName(newName);
				changed = true;
			}
		}
		if (changed = true) {
			msg = "El nombre del clan " + nameActual + " fue cambiado exitosamente a " + newName;
		}
		else {
			msg = "El nombre del clan no pudo ser cambiado";
		}
		return msg;
	}
	
	//Metodo para cambiarle el nombre a un personaje
	
	public String changeCharacterName(String actualName, String newName) {
		String msg = "";
		boolean changed = false;
		for(int i = 0; i < clans.size() && !changed; i++) {
			if(clans.get(i).changeCharacterName(actualName, newName) == true) {
				changed = true;
				msg = "El nombre del personaje " + actualName + " fue cambiado con exito a " + newName;
			}
		}
		return msg;
	}
	
	//Metodo para cambiarle el poder a un personaje
	
	public String changeCharacterPower(String actualName, int newPower) {
		String msg = "";
		boolean changed = false;
		for(int i = 0; i < clans.size() && !changed; i++) {
			if(clans.get(i).changeCharacterPower(actualName, newPower) == true) {
				changed = true;
				msg = "El poder del personaje " + actualName + " fue cambiado con exito a " + newPower;
			}
		}
		return msg;
	}
	
	//Metodo para cambiarle el nombre a la tecnica de un personaje
	
	public String changeTechniqueName(String characterName, String techniqueActualName, String techniqueNewName) {
		boolean changed = false;
		String msg = "";
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).changeTechniqueName(characterName, techniqueActualName, techniqueNewName) == true) {
				changed = true;
			}
		}
		if(changed == true) {
			msg = "El nombre de la tecnica fue cambiado con exito";
		}
		else {
			msg = "El nombre de la tecnica no pudo ser cambiado";
		}
		return msg;

	}
	
	//Metodo que cambia el poder de la tecnica de un personaje
	
	public String changeTechniquePower(String characterName, String techniqueActualName, int techniqueNewPower) {
		boolean changed = false;
		String msg = "";
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).changeTechniquePower(characterName, techniqueActualName, techniqueNewPower) == true) {
				changed = true;
			}
		}
		if(changed == true) {
			msg = "El poder de la tecnica fue cambiado con exito";
		}
		else {
			msg = "El poder de la tecnica no pudo ser cambiado";
		}
		return msg;
	}
	
	//Metodo ordenamiento seleccion para ordenar los clanes por nombre
}
