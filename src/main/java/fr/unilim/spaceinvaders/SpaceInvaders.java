package fr.unilim.spaceinvaders;

import moteurJeu.Commande;
import moteurJeu.Jeu;
import utils.*;

public class SpaceInvaders implements Jeu {
	// ATTRIBUTS
	int longueur;
	int hauteur;
	Vaisseau vaisseau;

	// CONSTRUCTEUR
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	// METHODES

	public void initialiserJeu() {
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		Position positionVaisseau = new Position(this.longueur / 2 - Constante.VAISSEAU_LONGUEUR / 2,
				this.hauteur * 49 / 50);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	}
	
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		Dimension dimensionVaisseau = dimension;

		if (!estDansEspaceJeu(x + dimension.longueur - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau d�borde de l'espace jeu vers la droite � cause de sa longueur");
		if (!estDansEspaceJeu(x, y - dimension.hauteur + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau d�borde de l'espace jeu vers le bas � cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.seDeplacerVersLaDroite();
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.dimension.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.seDeplacerVersLaGauche();
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	@Override
	public void evoluer(Commande commandeUser) {
		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}
		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}
	
	@Override
	public boolean etreFini() {
		return false;
	}

	public boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && aUnVaisseauQuiOccupe(x, y);
	}

	public boolean aUnVaisseauQuiOccupe(int x, int y) {
		return vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}
	
	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}
	
	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

}
