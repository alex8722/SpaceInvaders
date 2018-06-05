package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurJeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurJeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;
	boolean envahisseurVaAGauche;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		envahisseurVaAGauche = true;
	}

	public void initialiserJeu() {
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		Position positionVaisseau = new Position(this.longueur / 2 - Constante.VAISSEAU_LONGUEUR / 2, this.hauteur - 1);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		Position positionEnvahisseur = new Position(this.longueur / 2 - Constante.ENVAHISSEUR_LONGUEUR / 2,
				Constante.ENVAHISSEUR_HAUTEUR - 1);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur,
				Constante.ENVAHISSEUR_VITESSE_HORIZONTAL);
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesseHorizontal) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + dimension.longueur - 1, y))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - dimension.hauteur + 1))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");
		envahisseur = new Envahisseur(dimension, position, vitesseHorizontal);

	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + dimension.longueur - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - dimension.hauteur + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.dimension.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public void tirerUnMissile(Dimension dimension, int vitesse) {
		if ((this.vaisseau.dimension.hauteur() + dimension.hauteur()) > this.hauteur) {
			throw new MissileException("Il n'y a pas assez de hauteur libre pour placer le missile !");
		} else {
			this.missile = this.vaisseau.tirerUnMissile(dimension, vitesse);
		}
	}

	public Missile recupererUnMissile() {
		return this.missile;
	}

	@Override
	public void evoluer(Commande commandeUser) {
		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}
		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
		if (commandeUser.tirer && !this.aUnMissile()) {
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);

		}
		if (this.aUnMissile()) {
			deplacerMissile();
			if (!estDansEspaceJeu(this.missile.abscisseLaPlusAGauche(), this.missile.ordonneeLaPlusBasse())) {
				detruireMissile();
			}
		}
		if (envahisseurVaAGauche) {
			if (envahisseur.abscisseLaPlusAGauche() <= 0) {
				envahisseurVaAGauche = false;
			} else {
				deplacerEnvahisseurVersLaGauche();
			}
		}
		if (!envahisseurVaAGauche) {
			if (envahisseur.abscisseLaPlusADroite() >= Constante.ESPACEJEU_LONGUEUR-1) {
				envahisseurVaAGauche = true;
			} else {
				deplacerEnvahisseurVersLaDroite();
			}
		}
	}

	public void deplacerMissile() {
		missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
	}

	private void detruireMissile() {
		this.missile = null;
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (aUnVaisseauQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_VAISSEAU;
		} else if (aUnMissileQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_MISSILE;
		} else if (aUnEnvahisseurQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_ENVAHISSEUR;
		} else {
			marque = Constante.MARQUE_VIDE;
		}
		return marque;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && aUnMissileQuiOccupe(x, y);
	}

	public boolean aUnMissileQuiOccupe(int x, int y) {
		return this.missile.occupeLaPosition(x, y);
	}

	public boolean aUnMissile() {
		return missile != null;
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

	public boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && aUnEnvahisseurQuiOccupe(x, y);
	}

	public boolean aUnEnvahisseurQuiOccupe(int x, int y) {
		return envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}

	public Envahisseur getEnvahisseur() {
		return this.envahisseur;
	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.dimension.longueur(), envahisseur.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		}
	}

}
