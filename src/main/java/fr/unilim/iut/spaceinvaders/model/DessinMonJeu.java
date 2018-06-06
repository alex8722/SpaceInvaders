package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import org.w3c.dom.css.RGBColor;

import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.moteurJeu.DessinJeu;

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

	private void dessinerMissile(Missile missile, BufferedImage im, Color couleur) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(couleur);
		crayon.fillOval(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.dimension.longueur(),
				missile.dimension.hauteur());
	}

	private void dessinerEnvahisseur(Envahisseur envahisseur, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.BLACK);
		crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(),
				envahisseur.dimension.longueur(), envahisseur.dimension.hauteur());
	}

	public void dessiner(BufferedImage image) {
		if (this.spaceinvaders.aUnVaisseau()) {
			Vaisseau vaisseau = spaceinvaders.getVaisseau();
			this.dessinerVaisseau(vaisseau, image);
		}
		if (this.spaceinvaders.aUnMissile()) {
			Color[] color = { new Color(255, 0, 0), new Color(255, 165, 0), new Color(255, 255, 0), new Color(50, 205, 50),
					new Color(0, 0, 255), new Color(128, 0, 128) };
			List<Missile> listeMissile = this.spaceinvaders.recupererListeMissiles();
			for (int index = 0; index < this.spaceinvaders.nombreDeMissile; index++) {
				Missile missile = listeMissile.get(index);
				this.dessinerMissile(missile, image, color[listeMissile.get(index).nbCouleur]);
			}
		}
		if (this.spaceinvaders.aUnEnvahisseur()) {
			Envahisseur envahisseur = spaceinvaders.getEnvahisseur();
			this.dessinerEnvahisseur(envahisseur, image);
		}
	}

}
