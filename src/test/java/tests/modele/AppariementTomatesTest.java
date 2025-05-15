package tests.modele;

import static org.junit.Assert.*;

import adaptateur.OutilsBaseDonneesTomates;
import modele.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppariementTomatesTest {
	
	private static Tomates tomates;
	
	@BeforeClass
	public static void setUp() {
		tomates = OutilsBaseDonneesTomates.générationBaseDeTomates("src/main/resources/data/tomates.json");
	}

	@Test
	public void testAjoutTomateDéjàExistante() {
		Tomate brandywine = tomates.getTomate("Tomate Brandywine");
		brandywine.addTomateApparentée(tomates.getTomate("Tomate Bonne Fée"));
		brandywine.addTomateApparentée(tomates.getTomate("Tomate Bonne Fée"));
		assertEquals(4+1, brandywine.getTomatesApparentées().size());
	}

	@Test
	public void testAjoutTomateApparentéeAElleMême() {
		Tomate brandywine = tomates.getTomate("Tomate Brandywine");
		brandywine.addTomateApparentée(tomates.getTomate("Tomate Brandywine"));
		assertEquals(4, brandywine.getTomatesApparentées().size());
	}
	
	@Test
	public void testAjoutTomateNull() {
		Tomate brandywine = tomates.getTomate("Tomate Brandywine");
		brandywine.addTomateApparentée(null);
		assertEquals(4, brandywine.getTomatesApparentées().size());
	}
	
	@Test
	public void testAjoutTomateNonDeMêmeType() {
		Tomate brandywine = tomates.getTomate("Tomate Brandywine");
		brandywine.addTomateApparentée(tomates.getTomate("Tomate Orange Bourgois"));
		assertEquals(4, brandywine.getTomatesApparentées().size());
	}
	
}
