package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Vaisseau extends Sprite {

	public Vaisseau(Dimension dimension, Position origine, int vitesse) {
		super(dimension, origine, vitesse);
	}

	public Missile tirerUnMissile(Dimension dimension, int vitesse) {
		if (dimension.longueur() > this.dimension.longueur()) {
			throw new MissileException("La longueur du missile est superieure a la longueur du vaisseau !");
		}
		Position origineMissile = calculerLaPositionDeTirDuMissile(dimension);
		return new Missile(dimension, origineMissile, vitesse);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimension) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.dimension.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimension.longueur() / 2);

		int ordonneeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		return new Position(abscisseOrigineMissile, ordonneeOrigineMissile);
	}
}