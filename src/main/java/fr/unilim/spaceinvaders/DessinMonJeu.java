package fr.unilim.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.spaceinvaders.SpaceInvaders;
import moteurJeu.DessinJeu;

public class DessinMonJeu implements DessinJeu {

	private SpaceInvaders jeu;

	public DessinMonJeu(SpaceInvaders j) {
		this.jeu = j;
	}

	private void dessinerObjet(Vaisseau vaisseau, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillOval(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.dimension.longueur(),
				vaisseau.dimension.hauteur());

	}

	public void dessiner(BufferedImage image) {
		if (this.jeu.aUnVaisseau()) {
			Vaisseau vaisseau = jeu.getVaisseau();
			this.dessinerObjet(vaisseau, image);
		}
	}

}
