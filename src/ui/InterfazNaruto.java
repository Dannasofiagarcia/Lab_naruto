package ui;

import java.io.*;
import java.text.*;
import java.util.*;

import model.*;
import model.Character;

public class InterfazNaruto {

	// ATRIBUTOS

	// Relación con el modelo
	private ClanManager clanManager;
	private Scanner lector;

	// CONSTRUCTOR

	public InterfazNaruto() {
		clanManager = new ClanManager();
		lector = new Scanner(System.in);
	}

	public void showMenu() {
		int userInput = 0;

		while (userInput != 13) {
			showOptions();
			userInput = lector.nextInt();
			lector.nextLine();

			switch (userInput) {

			case 1:
				try {
					System.out.println("Ingrese el nombre del nuevo clan");
					String clanName = lector.nextLine();

					Clan clan = new Clan(clanName);
					try {
						clanManager.addClan(clan);
					} catch (ExceptionNombreIgual e1) {
						e1.getMessage();
					}

					System.out.println("Desea agregar un jugador al clan creado? \n1. Si\n2. No");
					int decision = lector.nextInt();
					lector.nextLine();

					if (decision == 1) {
						System.out.println("Ingrese el nombre del jugador que desea agregar");
						String name = lector.nextLine();

						System.out.println("Ingrese la personalidad del jugador que desea agregar");
						String personality = lector.nextLine();

						System.out.println(
								"Ingrese la fecha de creacion del personaje en el siguiente formato año/mes/día");
						String date = lector.nextLine();
						DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
						Date dateCreation = formato.parse(date);

						System.out.println("Ingrese el poder del personaje");
						int power = lector.nextInt();
						lector.nextLine();
						Character character = new Character(name, personality, dateCreation, power);
						clanManager.addCharacter(character, clan.getClanName());

						System.out.println("Desea agregarle una tecnica al jugador agregado? \n1. Si\n2. No");
						int decisionTecnica = lector.nextInt();
						lector.nextLine();

						if (decisionTecnica == 1) {
							System.out.println("Ingrese el nombre de la técnica");
							String name1 = lector.nextLine();

							System.out.println("Ingrese el factor que influye en la tecnica del personaje");
							int power1 = lector.nextInt();
							lector.nextLine();

							Technique technique = new Technique(name1, power1);
							clanManager.addTechnique(technique, name);
						} // Cierra el if de agregar tecnica
					} // Cierra el if de agregar jugador
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (ExceptionNombreIgual e) {
					e.getMessage();
				}

				break;

			case 2:
				try {

					System.out.println("Ingrese el nombre del jugador que desea agregar");
					String name = lector.nextLine();

					System.out.println("Ingrese la personalidad del jugador que desea agregar");
					String personality = lector.nextLine();

					System.out
							.println("Ingrese la fecha de creacion del personaje en el siguiente formato año/mes/día");
					String date = lector.nextLine();
					DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
					Date dateCreation = formato.parse(date);

					System.out.println("Ingrese el poder del personaje");
					int power = lector.nextInt();
					lector.nextLine();
					Character character = new Character(name, personality, dateCreation, power);

					System.out.println("Seleccione a cual de los siguientes clanes desea agregar el jugador creado");
					System.out.println(clanManager.showClans());
					String clanName = lector.nextLine();

					clanManager.addCharacter(character, clanName);

					System.out.println("Desea agregarle una tecnica al jugador agregado? \n1. Si\n2. No");
					int decisionTecnica = lector.nextInt();
					lector.nextLine();

					if (decisionTecnica == 1) {
						System.out.println("Ingrese el nombre de la técnica");
						String name1 = lector.nextLine();

						System.out.println("Ingrese el factor que influye en la tecnica del personaje");
						int power1 = lector.nextInt();
						lector.nextLine();

						Technique technique = new Technique(name1, power1);
						clanManager.addTechnique(technique, name);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (ExceptionNombreIgual e) {
					e.getMessage();
				}
				break;

			case 3:
				System.out.println("Ingrese el nombre de la técnica");
				String name1 = lector.nextLine();

				System.out.println("Ingrese el factor que influye en la tecnica del personaje");
				int power1 = lector.nextInt();
				lector.nextLine();

				Technique technique = new Technique(name1, power1);

				System.out.println("Seleccione a cual de los siguientes jugadores desea agregar la tecnica creada");
				System.out.println(clanManager.showClans());
				String characterName = lector.nextLine();

				clanManager.addTechnique(technique, characterName);
				break;

			case 4:
				System.out.println("Seleccione cual de los siguientes clanes desea eliminar");
				System.out.println(clanManager.showClans());
				String clanNameDelete = lector.nextLine();

				System.out.println(clanManager.deleteClan(clanNameDelete));
				break;

			case 5:
				System.out.println("Seleccione cual de los siguientes personajes desea eliminar");
				System.out.println(clanManager.showCharactersName());
				String nameCharacterDelete = lector.nextLine();

				try {
					System.out.println(clanManager.deleteCharacter(nameCharacterDelete));
				} catch (ExceptionNoExiste e) {
					e.printStackTrace();
				}
				break;

			case 6:
				System.out.println("Seleccione en cual de los siguientes personajes desea eliminar la tecnica");
				System.out.println(clanManager.showCharactersName());
				String nameCharacterTD = lector.nextLine();

				System.out.println("Seleccione cual de los siguientes tecnicas del personaje desea eliminar");
				System.out.println(clanManager.showTechniques(nameCharacterTD));
				String nameTechniqueDelete = lector.nextLine();

				System.out.println(clanManager.deleteTechnique(nameCharacterTD, nameTechniqueDelete));
				break;

			case 7:
				System.out.println("Seleccione cual de los siguientes clanes desea cambiarle el nombre");
				System.out.println(clanManager.showClans());
				String clanActualName = lector.nextLine();

				System.out.println("Ingrese el nuevo nombre del clan");
				String clanNewName = lector.nextLine();

				System.out.println(clanManager.changeClanName(clanActualName, clanNewName));
				break;

			case 8:
				System.out.println("Seleccione cual de los siguientes personajes desea cambiarle el nombre");
				System.out.println(clanManager.showCharactersName());
				String characterActualName = lector.nextLine();

				System.out.println("Ingrese el nuevo nombre del personaje");
				String characterNewName = lector.nextLine();

				System.out.println(clanManager.changeCharacterName(characterActualName, characterNewName));
				break;

			case 9:
				System.out.println("Seleccione cual de los siguientes personajes desea cambiarle el poder");
				System.out.println(clanManager.showCharactersName());
				String characterChangeName = lector.nextLine();

				System.out.println("Ingrese el nuevo nombre del personaje");
				int characterNewPower = lector.nextInt();
				lector.nextLine();

				System.out.println(clanManager.changeCharacterPower(characterChangeName, characterNewPower));
				break;

			case 10:
				System.out.println(
						"Seleccione en cual de los siguientes personajes desea cambiarle el nombre a una tecnica");
				System.out.println(clanManager.showCharactersName());
				String nameCharacterTC = lector.nextLine();

				System.out
						.println("Seleccione cual de los siguientes tecnicas del personaje desea cambiarle el nombre");
				System.out.println(clanManager.showTechniques(nameCharacterTC));
				String nameTechniqueActual = lector.nextLine();

				System.out.println("Ingrese el nuevo nombre de la tecnica seleccionada");
				String newNameTechnique = lector.nextLine();

				System.out.println(
						clanManager.changeTechniqueName(nameCharacterTC, nameTechniqueActual, newNameTechnique));
				break;

			case 11:
				System.out.println(
						"Seleccione en cual de los siguientes personajes desea cambiarle el poder a una tecnica");
				System.out.println(clanManager.showCharactersName());
				String nameCharacterTCP = lector.nextLine();

				System.out.println("Seleccione cual de los siguientes tecnicas del personaje desea cambiarle el poder");
				System.out.println(clanManager.showTechniques(nameCharacterTCP));
				String nameTechniqueActualP = lector.nextLine();

				System.out.println("Ingrese el nuevo poder de la tecnica seleccionada");
				int newPowerTechnique = lector.nextInt();
				lector.nextLine();

				System.out.println(
						clanManager.changeTechniquePower(nameCharacterTCP, nameTechniqueActualP, newPowerTechnique));
				break;

			case 12:
				System.out.println("Seleccione que clase desea ordenar \n1. Clanes\n2. Personajes\n3. Tecnicas");
				int decisionOrdenamiento = lector.nextInt();
				lector.nextLine();

				if (decisionOrdenamiento == 1) {

				} else if (decisionOrdenamiento == 2) {

				} else if (decisionOrdenamiento == 3) {

				}

			case 16:
				clanManager.serialization();
				break;

			}// Cierra el switch

			if (userInput == 17) {
				// Serializamos al momento de que el usuario salga del programa
				clanManager.serialization();
			}
		} // Cierra el while
	}// Cierra el metodo

	public void showOptions() {
		System.out.println("Bienvenido. Seleccione la opcion que desea");
		System.out.println("1. Agregar un nuevo clan");
		System.out.println("2. Agregar un nuevo personaje");
		System.out.println("3. Agregar una nueva tecnica");
		System.out.println("4. Eliminar un clan");
		System.out.println("5. Eliminar un personaje");
		System.out.println("6. Eliminar una tecnica");
		System.out.println("7. Modificar el nombre de un clan");
		System.out.println("8. Modificar el nombre de un personaje");
		System.out.println("9. Modificar el poder de un personaje");
		System.out.println("10. Modificar el nombre de una tecnica");
		System.out.println("11. Modificar el poder de una tecnica");
		System.out.println("12. Ordenar los datos");
		System.out.println("13. Para buscar un clan por el nombre");
		System.out.println("14. Para buscar un personaje por el nombre");
		System.out.println("15. Para ver como una tecnica afecta el poder de un personaje");
		System.out.println("16. Para guardar la información del programa");
		System.out.println("17. Salir");
	}

	public static void main(String[] args) {
		InterfazNaruto interfaz = new InterfazNaruto();
	}

}
