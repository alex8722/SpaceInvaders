package fr.unilim.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.spaceinvaders.SpaceInvaders;
import moteurJeu.DessinJeu;

public class DessinMonJeu implements DessinJeu {

	private SpaceInvaders spaceinvaders;

	public DessinMonJeu(SpaceInvaders j) {
		this.spaceinvaders = j;
	}

	private void dessinerVaisseau(Vaisseau vaisseau, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.gray);
		crayon.fillOval(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.dimension.longueur(),
				vaisseau.dimension.hauteur());
	}

	private void dessinerMissile(Missile missile, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.dimension.longueur(),
				missile.dimension.hauteur());
	}

	public void dessiner(BufferedImage image) {
		if (this.spaceinvaders.aUnVaisseau()) {
			Vaisseau vaisseau = spaceinvaders.getVaisseau();
			this.dessinerVaisseau(vaisseau, image);
		}
		if(this.spaceinvaders.aUnMissile()){
			Missile missile = this.spaceinvaders.recupererUnMissile();
			this.dessinerMissile(missile, image);
		}
	}

}
