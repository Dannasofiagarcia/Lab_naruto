package narutoLabTest;

import java.util.Date;

import model.Character;
import model.Clan;
import model.ClanManager;
import model.ExceptionNoExiste;
import model.ExceptionNombreIgual;
import model.Technique;
import static org.junit.Assert.*;
import org.junit.Test;

public class ClanManagerTest {

	ClanManager clanManagerTest;

	private void setupEscenario1() {
		clanManagerTest = new ClanManager();
		Clan clan1 = new Clan("Clan1");
		clanManagerTest.getClans().removeAll(clanManagerTest.getClans());
		try {
			clanManagerTest.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		Character character1 = new Character("PersonajeTest1", "Prueba", new Date(2019 / 5 / 9), 500);
		try {
			clanManagerTest.addCharacter(character1, clanManagerTest.getClans().get(0).getClanName());
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
	}

	private void setupEscenario2() {
		clanManagerTest = new ClanManager();
		Clan clan1 = new Clan("Clan1");
		clanManagerTest.getClans().removeAll(clanManagerTest.getClans());
		try {
			clanManagerTest.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
	}

	private void setupEscenario3() {
		clanManagerTest = new ClanManager();
		Clan clan1 = new Clan("Clan1");
		clanManagerTest.getClans().removeAll(clanManagerTest.getClans());
		try {
			clanManagerTest.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		Character character1 = new Character("PersonajeTest1", "Prueba", new Date(2019 / 5 / 9), 500);
		try {
			clanManagerTest.addCharacter(character1, clanManagerTest.getClans().get(0).getClanName());
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}

		Technique technique1 = new Technique("Prueba", 500);
		clanManagerTest.addTechnique(technique1, clanManagerTest.getClans().get(0).getFirstCharacter().getName());
	}

	private void setupEscenario4() {
		clanManagerTest = new ClanManager();
	}

	@Test

	public void testVerificarNombre() {
		setupEscenario1();

		boolean esperado = true;
		boolean resultado = clanManagerTest.verificarNombreClan(clanManagerTest.getClans().get(0).getClanName());
		assertEquals(esperado, resultado);
	}

	@Test

	public void testVerificarNombrePersonaje() {
		setupEscenario1();

		boolean esperado = false;
		boolean resultado = clanManagerTest.verificarNombrePersonaje(clanManagerTest.getClans().get(0).getClanName(),
				clanManagerTest.getClans().get(0).getFirstCharacter().getName());

		assertEquals(esperado, resultado);
	}

	@Test

	public void testAddClan() {
		setupEscenario4();
		Clan clan = new Clan("Prueba");
		try {
			clanManagerTest.addClan(clan);
		} catch (ExceptionNombreIgual e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean esperado = true;
		boolean resultado = false;
		if (clanManagerTest.getClans() != null) {
			resultado = true;
		}
		assertEquals(esperado, resultado);
	}

	@Test

	public void addCharacter() {
		setupEscenario2();
		Character character2 = new Character("PersonajeTest2", "Prueba", new Date(2019 / 5 / 9), 600);
		try {
			clanManagerTest.addCharacter(character2, clanManagerTest.getClans().get(0).getClanName());
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}

		boolean esperado = true;
		boolean resultado = false;

		if (clanManagerTest.getClans().get(0).getFirstCharacter() != null) {
			resultado = true;
		}

		assertEquals(esperado, resultado);
	}

	@Test

	public void testDeleteClan() {
		setupEscenario1();

		boolean esperado = false;
		clanManagerTest.deleteClan(clanManagerTest.getClans().get(0).getClanName());
		boolean resultado = false;
		if (clanManagerTest.getClans() == null) {
			resultado = true;
		}
		assertEquals(esperado, resultado);
	}

	@Test

	public void testAddTechnique() {
		setupEscenario1();
		Technique technique1 = new Technique("TecnicaPrueba1", 500);
		clanManagerTest.addTechnique(technique1, clanManagerTest.getClans().get(0).getFirstCharacter().getName());

		boolean esperado = true;
		boolean resultado = false;
		if (clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques() != null) {
			resultado = true;
		}

		assertEquals(esperado, resultado);
	}

	@Test

	public void testDeleteCharacter() {
		setupEscenario1();
		boolean esperado = true;
		boolean resultado = false;
		try {
			clanManagerTest.deleteCharacter(clanManagerTest.getClans().get(0).getFirstCharacter().getName());
		} catch (ExceptionNoExiste e) {
			e.printStackTrace();
		}

		if (clanManagerTest.getClans().get(0).getFirstCharacter() == null) {
			resultado = true;
		}

		assertEquals(esperado, resultado);
	}

	@Test

	public void testShowTechniques() {
		setupEscenario3();

		String esperado = "Prueba" + "\n";
		String resultado = clanManagerTest
				.showTechniques(clanManagerTest.getClans().get(0).getFirstCharacter().getName());

		assertEquals(esperado, resultado);
	}

	@Test

	public void testDeleteTechnque() {
		setupEscenario3();

		boolean esperado = true;
		boolean resultado = false;
		clanManagerTest.deleteTechnique(clanManagerTest.getClans().get(0).getFirstCharacter().getName(),
				clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName());

		if (clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques() == null) {
			resultado = true;
		}

		assertEquals(esperado, resultado);
	}

	@Test

	public void testChangeClanName() {
		setupEscenario2();

		String esperado = "Cambio";
		clanManagerTest.changeClanName(clanManagerTest.getClans().get(0).getClanName(), esperado);
		String resultado = clanManagerTest.getClans().get(0).getClanName();

		assertEquals(esperado, resultado);
	}

	@Test

	public void changeCharacterName() {
		setupEscenario1();

		String esperado = "Cambio";
		clanManagerTest.changeCharacterName(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), esperado);
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getName();

		assertEquals(esperado, resultado);
	}

	@Test

	public void changeCharacterPower() {
		setupEscenario1();

		int esperado = 1;
		clanManagerTest.changeCharacterPower(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), esperado);
		int resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getPower();

		assertEquals(esperado, resultado);
	}

	@Test

	public void testChangeTechniqueName() {
		setupEscenario3();

		String esperado = "Cambio";
		clanManagerTest.changeTechniqueName(clanManagerTest.getClans().get(0).getFirstCharacter().getName(),
				clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName(), esperado);
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName();

		assertEquals(esperado, resultado);

	}

	@Test

	public void testChangeTechniquePower() {
		setupEscenario3();

		int esperado = 1;
		clanManagerTest.changeTechniquePower(clanManagerTest.getClans().get(0).getFirstCharacter().getName(),
				clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName(), esperado);
		int resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getPower();

		assertEquals(esperado, resultado);
	}

	@Test

	public void testOrdenarTecnicas() {
		setupEscenario3();
		Technique temp2 = new Technique("Prueba1", 1000);
		clanManagerTest.addTechnique(temp2, clanManagerTest.getClans().get(0).getFirstCharacter().getName());

		Technique temp = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques();
		String esperado = "          " + "TECNICAS ORDENADAS POR NOMBRE" + "          " + "\n";
		esperado += "     " + "Nombre de la tecnica" + "     " + "     " + "Poder de la tecnica" + "     " + "\n";
		esperado += "     " + temp.getName() + "     " + "     " + temp.getPower() + "     " + "\n";
		esperado += "     " + temp2.getName() + "     " + "     " + temp2.getPower() + "     " + "\n";

		String resultado = clanManagerTest.ordenarTechniques();

		assertEquals(esperado, resultado);
	}

	@Test

	public void testOrdenarNombreCharacter() {
		setupEscenario1();

		Character temp2 = new Character("PersonajeTest2", "Prueba", new Date(2019 / 03 / 03), 23);
		Character temp1 = clanManagerTest.getClans().get(0).getFirstCharacter();
		clanManagerTest.getClans().get(0).addCharacter(temp2);

		String esperado = "         " + "PERSONAJES ORDENADOS POR NOMBRE" + "          ";
		esperado += "     " + "Nombre del personaje" + "     " + "     " + "Personalidad del personaje" + "     "
				+ "     " + "Poder del personaje" + "     " + "\n";
		esperado += "     " + temp2.getName() + "     " + "     " + temp2.getPersonality() + "     " + "     "
				+ temp2.getPower() + "     " + "\n";
		esperado += "     " + temp1.getName() + "     " + "     " + temp1.getPersonality() + "     " + "     "
				+ temp1.getPower() + "     " + "\n";

		String resultado = clanManagerTest.ordenarPersonajeNombre();

		assertEquals(esperado, resultado);
	}

	@Test

	public void ordenarClanNombre() {
		setupEscenario2();
		Clan temp2 = new Clan("Clan2");
		try {
			clanManagerTest.addClan(temp2);
		} catch (ExceptionNombreIgual e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Clan temp1 = clanManagerTest.getClans().get(0);
		String esperado = "          " + "CLANES ORDENADOS POR NOMBRE" + "          " + "\n";
		esperado += "     " + "Nombre del clan" + "     " + "\n";
		esperado += "     " + temp1.getClanName() + "     " + "\n";
		esperado += "     " + temp2.getClanName() + "     " + "\n";

		String resultado = clanManagerTest.ordenarClanNombre();

		assertEquals(esperado, resultado);
	}

	@Test

	public void testSearchTechnique() {
		setupEscenario3();
		Technique temp = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques();
		String esperado = "La informacion de la tecnica buscado es la siguiente \n" + "El nombre de la tecnica es "
				+ temp.getName() + "\n" + "El poder de la tecnia es " + temp.getPower() + "\n";
		String resultado = clanManagerTest.searchTechnique(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), temp.getName());

		assertEquals(esperado, resultado);
	}

	@Test

	public void testSearchCharacter() {
		setupEscenario3();
		Character temp = clanManagerTest.getClans().get(0).getFirstCharacter();
		String esperado = "La informacion del personaje buscado es la siguiente \n" + "El nombre del personaje es "
				+ temp.getName() + "\n" + "El poder del personaje es " + temp.getPower() + "\n"
				+ "La personalidad del personaje es " + temp.getPersonality() + "\n"
				+ "La fecha de creacion del personaje es " + temp.getDateCreation();
		String resultado = clanManagerTest.searchCharacter(temp.getName());

		assertEquals(esperado, resultado);
	}

}
