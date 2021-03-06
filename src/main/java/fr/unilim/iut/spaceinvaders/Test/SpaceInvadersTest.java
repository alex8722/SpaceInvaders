package fr.unilim.iut.spaceinvaders.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.*;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.*;

public class SpaceInvadersTest {

	private SpaceInvaders spaceinvaders;

	// POSITIONNER UN NOUVEAU VAISSEAU
	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + ".......V.......\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropADroite_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(15, 9), 1);
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropEnBas_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, 10), 1);
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropAGauche_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(15, 11), 1);
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropEnHaut_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(16, 10), 1);
	}

	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(7, 9), 1);
	}

	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(15, 9), 1);
			fail("Position trop � droite : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(-1, 9), 1);
			fail("Position trop � gauche : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, 10), 1);
			fail("Position trop en bas : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, -1), 1);
			fail("Position trop � haut : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}

	// POSITIONNEMENT DANS UN ESPACE DE JEU + DIMENSIONS
	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".......VVV.....\n" + ".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// TEST DEBORDEMENT VAISSEAU DANS L'ESPACE JEU
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9, 2), new Position(7, 9), 1);
			fail("D�passement du vaisseau � droite en raison de sa longueur trop importante : devrait d�clencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 4), new Position(7, 1), 1);
			fail("D�passement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait d�clencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

	}

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(12, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "............VVV\n" + "............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void VaisseauAvance_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "......VVV......\n" + "......VVV......\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(0, 9), 1);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "VVV............\n" + "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// Test deplacement avec vitesse
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "..........VVV..\n" + "..........VVV..\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(10, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "............VVV\n" + "............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "....VVV........\n" + "....VVV........\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(0, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "VVV............\n" + "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(1, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "VVV............\n" + "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// Test de tir d'un missile
	@Test
	public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + ".......MMM.....\n" + ".......MMM.....\n"
				+ ".....VVVVVVV...\n" + ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// Deplacer un missile
	@Test
	public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);

		spaceinvaders.deplacerMissile(0);

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".......MMM.....\n" + ".......MMM.....\n" + "...............\n" + "...............\n"
				+ ".....VVVVVVV...\n" + ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	// Avoir Plusieurs Missiles � l'�cran
	@Test
	public void test_DeuxMissile() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);
		spaceinvaders.deplacerMissile(0);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".......MMM.....\n" + ".......MMM.....\n" + ".......MMM.....\n" + ".......MMM.....\n"
				+ ".....VVVVVVV...\n" + ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_AvoirDeLaPlacePourDeuxMissile() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + ".......MMM.....\n" + ".......MMM.....\n"
				+ ".....VVVVVVV...\n" + ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 1);
		for (int i = 0; i <= 6; i++) {
			spaceinvaders.deplacerMissile(0);
		}

		spaceinvaders.deplacerMissile(0);

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".....VVVVVVV...\n" + ".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// Positionner un nouveau Envahisseur
	@Test
	public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {
		SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + ".......E.......\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauEnvahisseurEstPositionneHorsEspaceJeuTropADroite_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(15, 9), 1);
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauEnvahisseurEstPositionneHorsEspaceJeuTropEnBas_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(14, 10), 1);
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauEnvahisseurEstPositionneHorsEspaceJeuTropAGauche_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(15, 11), 1);
	}

	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauEnvahisseurEstPositionneHorsEspaceJeuTropEnHaut_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(16, 10), 1);
	}

	@Test
	public void test_UnNouveauEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(15, 9), 1);
			fail("Position trop � droite : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(-1, 9), 1);
			fail("Position trop � gauche : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(14, 10), 1);
			fail("Position trop en bas : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1, 1), new Position(14, -1), 1);
			fail("Position trop � haut : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}

	// POSITIONNEMENT DANS UN ESPACE DE JEU + DIMENSIONS
	@Test
	public void test_unNouveauEnvahisseurAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".......EEE.....\n" + ".......EEE.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// TEST DEBORDEMENT ENVAHISSEUR DANS L'ESPACE JEU
	@Test
	public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(9, 2), new Position(7, 9), 1);
			fail("D�passement du Envahisseur � droite en raison de sa longueur trop importante : devrait d�clencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 4), new Position(7, 1), 1);
			fail("D�passement du Envahisseur vers le haut en raison de sa hauteur trop importante : devrait d�clencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

	}

	@Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(12, 9), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "............EEE\n" + "............EEE\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 9), 1);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "......EEE......\n" + "......EEE......\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(0, 9), 1);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "EEE............\n" + "EEE............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	// Test deplacement avec vitesse
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "..........EEE..\n" + "..........EEE..\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaDroite() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(10, 9), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "............EEE\n" + "............EEE\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "....EEE........\n" + "....EEE........\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(0, 9), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "EEE............\n" + "EEE............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaGauche() {

		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(1, 9), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "EEE............\n" + "EEE............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

}