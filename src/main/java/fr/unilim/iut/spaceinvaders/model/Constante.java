package fr.unilim.iut.spaceinvaders.model;

public class Constante {

	/*// Récupére les dimensions de l'écran
	static java.awt.Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	// Les dimensions du jeu
	public static final int ESPACEJEU_LONGUEUR = (int) dimension.getWidth();
	public static final int ESPACEJEU_HAUTEUR = (int) (dimension.getHeight() * 0.9);*/
	public static final int ESPACEJEU_LONGUEUR = 800;
	public static final int ESPACEJEU_HAUTEUR = 600;

	// Les informations concernant le vaisseau
	public static final int VAISSEAU_LONGUEUR = 30;
	public static final int VAISSEAU_HAUTEUR = 30;
	public static final int VAISSEAU_VITESSE = 5;
	public static final int NOMBRE_MISSILE = 3;

	// Les informations concernant les envahisseurs
	public static final int ENVAHISSEUR_LONGUEUR = 30;
	public static final int ENVAHISSEUR_HAUTEUR = 30;
	public static final int ENVAHISSEUR_VITESSE_HORIZONTAL = 2;

	// Les informations concernant le missile

	public static final int MISSILE_LONGUEUR = 10;
	public static final int MISSILE_HAUTEUR = 20;
	public static final int MISSILE_VITESSE = 10;
	public static final int OrdonneLaPlusBasseAvantNouveauTir = 500;

	// Représentation du jeu en charactére ASCII
	public static final char MARQUE_FIN_LIGNE = '\n';
	public static final char MARQUE_VIDE = '.';
	public static final char MARQUE_VAISSEAU = 'V';
	public static final char MARQUE_MISSILE = 'M';
	public static final char MARQUE_ENVAHISSEUR = 'E';

}
