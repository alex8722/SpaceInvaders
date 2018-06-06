package fr.unilim.iut.spaceinvaders.model;

public class Missile extends Sprite {
	int nbCouleur;

	public Missile(Dimension dimension, Position origine, int vitesse) {
		super(dimension, origine, vitesse);
		this.nbCouleur = (int) (Math.random() * 6);
	}

}
