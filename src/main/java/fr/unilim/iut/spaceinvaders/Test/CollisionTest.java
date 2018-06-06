package fr.unilim.iut.spaceinvaders.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Direction;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.Sprite;
import fr.unilim.iut.spaceinvaders.moteurJeu.Collision;


public class CollisionTest {
	
	private Sprite sprite1;
	private Sprite sprite2;
	
	@After
	public void tearDown() throws Exception{
		sprite1 = null;
		sprite2 = null;
	}
	
	@Test
	public void test_CollisionSprite_MissileToucheBasEnvahisseur(){
		sprite1 = new Missile(new Dimension(2,2), new Position(4,3), 1);
		sprite2 = new Envahisseur(new Dimension(2,2),new Position(4,1),1);
		
		sprite1.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		
		assertTrue(Collision.detecterCollision(sprite1, sprite2));
	}
	
	@Test
	public void test_CollisionSprite_MissileToucheCoteGaucheEnvahisseur(){
		sprite1 = new Missile(new Dimension(2,2), new Position(3,3), 1);
		sprite2 = new Envahisseur(new Dimension(2,2), new Position(4,1), 1);
		
		sprite1.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		sprite2.deplacerHorizontalementVers(Direction.GAUCHE);
		
		assertTrue(Collision.detecterCollision(sprite1, sprite2));
	}
	
	@Test
	public void test_CollisionSprite_MissileToucheCoteDroitEnvahisseur(){
		sprite1 = new Missile(new Dimension(2,2), new Position(5,3),1);
		sprite2 = new Envahisseur(new Dimension(2,2),new Position(4,1), 1);
		
		sprite1.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		sprite2.deplacerHorizontalementVers(Direction.DROITE);
		
		assertTrue(Collision.detecterCollision(sprite1, sprite2));
	}
}
