package narutoLabTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import model.Character;
import model.Clan;
import model.ClanManager;
import model.ExceptionNoExiste;
import model.ExceptionNombreIgual;
import model.Technique;

public class CharacterTest {
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
	
	public void addBefore() {
		setupEscenario1();
		
		boolean esperado = false;
		Character character2 = new Character("Prueba2", "Prueba", new Date (2019/01/01), 800);
		clanManagerTest.getClans().get(0).getFirstCharacter().addBefore(character2);
		boolean resultado = false;
		if(clanManagerTest.getClans().get(0).getFirstCharacter().getName().equals(character2.getName())) {
			resultado = true;
		}
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testAddTechnique() {
		setupEscenario1();
		Technique technique1 = new Technique("TecnicaPrueba1", 500);
		clanManagerTest.getClans().get(0).getFirstCharacter().addTechnique(technique1);
		
		boolean esperado = true;
		boolean resultado = false;
		if(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques() != null) {
			resultado = true;
		}
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testShowTechniques() {
		setupEscenario3();
		
		String esperado = "Prueba" + "\n";
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().showTechniques();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testDeleteTechnique() {
		setupEscenario3();
		
		boolean esperado = true;
		boolean resultado = false;
		try {
			clanManagerTest.getClans().get(0).getFirstCharacter().deleteTechnique(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName());
		} catch (ExceptionNoExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques() == null) {
			resultado = true;
		}
		
		assertEquals (esperado, resultado);
	}
	

	@Test
	
	public void testChangeTechniqueName() {
		setupEscenario3();
		
		String esperado = "Cambio";
		clanManagerTest.getClans().get(0).getFirstCharacter().changeTechniqueName(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName(), esperado);
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	
	public void testChangeTechniquePower() {
		setupEscenario3();
		
		int esperado = 1;
		clanManagerTest.getClans().get(0).getFirstCharacter().changeTechniquePower(clanManagerTest.getClans().get(0).getFirstCharacter().getFirstTechniques().getName(), esperado);
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
		
		String resultado = clanManagerTest.getClans().get(0).getFirstCharacter().ordenarNombreTecnicas();
		
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
}
