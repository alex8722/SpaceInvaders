package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.model.Constante;
import fr.unilim.iut.spaceinvaders.model.DessinMonJeu;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.moteurJeu.MoteurGraphique;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR,Constante.ESPACEJEU_HAUTEUR);
		DessinMonJeu aff = new DessinMonJeu(jeu);
		jeu.initialiserJeu();

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(jeu, aff);
		moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR,Constante.ESPACEJEU_HAUTEUR);
	}

}
