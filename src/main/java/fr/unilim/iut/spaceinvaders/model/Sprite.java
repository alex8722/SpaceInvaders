package fr.unilim.iut.spaceinvaders.model;

public abstract class Sprite {

	protected Position origine;
	protected Dimension dimension;
	protected int vitesseHorizontal;

	public Sprite(Dimension dimension, Position origine, int vitesse) {
		super();
		this.dimension = dimension;
		this.origine = origine;
		this.vitesseHorizontal = vitesse;
	}

	public void deplacerVerticalementVers(Direction direction) {
		this.origine.changerOrdonnee(this.origine.ordonnee() + direction.valeur() * vitesseHorizontal);
	}

	public void deplacerHorizontalementVers(Direction direction) {
		this.origine.changerAbscisse(this.origine.abscisse() + direction.valeur() * vitesseHorizontal);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
	}

	public boolean occupeLaPosition(int x, int y) {
		return (estAbscisseCouverte(x) && ordonneeEstCouverte(y));
	}

	public boolean ordonneeEstCouverte(int y) {
		return (ordonneeLaPlusBasse() <= y) && (y <= ordonneeLaPlusHaute());
	}

	public boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	public int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}

	public int ordonneeLaPlusBasse() {
		return ordonneeLaPlusHaute() - this.dimension.hauteur() + 1;
	}

	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}

	public int abscisseLaPlusADroite() {
		return abscisseLaPlusAGauche() + this.dimension.longueur() - 1;
	}

	public int abscisse() {
		return origine.x;
	}

}