package narutoLabTest;

import java.util.Date;

import org.junit.Test;
import model.*;
import model.Character;

public class ClanTest {
	Clan clanPrueba;
	
	private void setupEscenario1(){
		clanPrueba = new Clan("Clan prueba");
		Clan clan1 = new Clan ("Clan1");
		try {
			clan.addClan(clan1);
		} catch (ExceptionNombreIgual e) {
			e.printStackTrace();
		}
		Character character1 = new Character("PersonajeTest1", "Prueba", (java.sql.Date) new Date (2019/5/9), 500);
	}
	
	@Test
	
	public void testVerificarNombrePersonaje() {
		setupEscenario1();
		boolean esperado = false;
		boolean resultado = clan.verificarNombrePersonaje(clanPrueba.getClans().get(0).getClanName(), clanPrueba.getClans().get(0).getFirstCharacter())
		
	}

}
