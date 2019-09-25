package narutoLabTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import model.Character;
import model.Clan;
import model.ClanManager;
import model.ExceptionNoExiste;
import model.ExceptionNombreIgual;
import model.Technique;

public class ClanTests {
	
ClanManager clanManagerTest;
	
	private void setupEscenario1(){
		clanManagerTest = new ClanManager();
		Clan clan1 = new Clan ("Clan1");
		clanManagerTest.getClans().removeAll(clanManagerTest.getClans());
		try {
			clanManagerTest.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		Character character1 = new Character("PersonajeTest1", "Prueba", new Date (2019/5/9), 500);
		try {
			clanManagerTest.addCharacter(character1, clanManagerTest.getClans().get(0).getClanName());
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
	}
	
	private void setupEscenario2(){
		clanManagerTest = new ClanManager();
		Clan clan1 = new Clan ("Clan1");
		clanManagerTest.getClans().removeAll(clanManagerTest.getClans());
		try {
			clanManagerTest.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
	}
	
	private void setupEscenario3(){
		clanManagerTest = new ClanManager();
		Clan clan1 = new Clan ("Clan1");
		clanManagerTest.getClans().removeAll(clanManagerTest.getClans());
		try {
			clanManagerTest.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		Character character1 = new Character("PersonajeTest1", "Prueba", new Date (2019/5/9), 500);
		try {
			clanManagerTest.addCharacter(character1, clanManagerTest.getClans().get(0).getClanName());
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		
		Technique technique1 = new Technique ("Prueba", 500);
		clanManagerTest.addTechnique(technique1, clanManagerTest.getClans().get(0).getFirstCharacter().getName());
	}
	
	@Test
	
	public void testAddCharacter() {
		setupEscenario2();
		Character character2 = new Character("PersonajeTest2", "Prueba", new Date (2019/5/9), 600);
		try {
			clanManagerTest.addCharacter(character2, clanManagerTest.getClans().get(0).getClanName());
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		
		boolean esperado = true;
		boolean resultado = false;
		
		if(clanManagerTest.getClans().get(0).getFirstCharacter() != null){
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
		if(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques() != null) {
			resultado = true;
		}
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testShowNameCharacters() {
		setupEscenario1();
		String esperado = "PersonajeTest1" + "\n";
		String resultado = clanManagerTest.getClans().get(0).showNameCharacters();
		
		assertEquals(esperado,resultado);
	}

	@Test
	
	public void testDeleteCharacter() {
		setupEscenario1();
		boolean esperado = true;
		boolean resultado = false;
		try {
			clanManagerTest.getClans().get(0).deleteCharacter(clanManagerTest.getClans().get(0).getFirstCharacter().getName());
		} catch (ExceptionNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(clanManagerTest.getClans().get(0).getFirstCharacter() == null) {
			resultado = true;
		}
		
		assertEquals(esperado, resultado);
		
	}
	
	@Test
	
	public void testFindCharacter() {
		setupEscenario1();
		boolean esperado = true;
		boolean resultado = clanManagerTest.getClans().get(0).findCharacter(clanManagerTest.getClans().get(0).getFirstCharacter().getName());
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testShowTechniques() {
		setupEscenario3();
		
		String esperado = "Prueba" + "\n";
		String resultado = clanManagerTest.getClans().get(0).showTechniques(clanManagerTest.getClans().get(0).getFirstCharacter().getName());
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testDeleteTechnique() {
		setupEscenario3();
		
		boolean esperado = true;
		boolean resultado = false;
		clanManagerTest.getClans().get(0).deleteTechnique(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName());
		
		if(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques() == null) {
			resultado = true;
		}
		
		assertEquals (esperado, resultado);
	}
	
	@Test
	
	public void testChangeCharacterName() {
		setupEscenario1();
		
		String esperado = "Cambio";
		clanManagerTest.getClans().get(0).changeCharacterName(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), esperado);
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getName();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testChangeCharacterPower() {
		setupEscenario1();
			
		int esperado = 1;
		clanManagerTest.getClans().get(0).changeCharacterPower(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), esperado);
		int resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getPower();
			
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testChangeTechniqueName() {
		setupEscenario3();
		
		String esperado = "Cambio";
		clanManagerTest.getClans().get(0).changeTechniqueName(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName(), esperado);
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testChangeTechniquePower() {
		setupEscenario3();
		
		int esperado = 1;
		clanManagerTest.getClans().get(0).changeTechniquePower(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName(), esperado);
		int resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getPower();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testOrdenarTecnicas() {
		setupEscenario3();
		Technique temp2 = new Technique("Prueba1", 1000);
		clanManagerTest.addTechnique(temp2, clanManagerTest.getClans().get(0).getFirstCharacter().getName());
		Technique temp = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques();
		String esperado = "     " + temp.getName() + "     " + "     " + temp.getPower() + "     " + "\n";
		esperado += "     " + temp2.getName() + "     " + "     " + temp2.getPower() + "     " + "\n";
		
		String resultado = clanManagerTest.getClans().get(0).ordenarTecnicas();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testOrdenarNombreCharacter() {
		setupEscenario1();
		
		Character temp2 = new Character("PersonajeTest2", "Prueba", new Date (2019/03/03), 23);
		Character temp1 = clanManagerTest.getClans().get(0).getFirstCharacter();
		clanManagerTest.getClans().get(0).addCharacter(temp2);
		
		String esperado = "     " + temp2.getName() + "     " + "     " + temp2.getPersonality() + "     " + "     "
				+ temp2.getPower() + "     " + "\n";
		
		esperado += "     " + temp1.getName() + "     " + "     " + temp1.getPersonality() + "     " + "     "
		+ temp1.getPower() + "     " + "\n";
		
		String resultado = clanManagerTest.getClans().get(0).ordenarNombreCharacter();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testSearchTechnique() {
		setupEscenario3();
		Technique temp = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques();
		String esperado = "La informacion de la tecnica buscado es la siguiente \n" + "El nombre de la tecnica es " + temp.getName() + "\n" + "El poder de la tecnia es " + temp.getPower() + "\n";
		String resultado = clanManagerTest.getClans().get(0).searchTechnique(clanManagerTest.getClans().get(0).getFirstCharacter().getName(), temp.getName());
		
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
		String resultado = clanManagerTest.getClans().get(0).searchCharacter(temp.getName());
		
		assertEquals(esperado, resultado);
	}

}
