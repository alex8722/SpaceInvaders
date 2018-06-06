package fr.unilim.iut.spaceinvaders.moteurJeu;

import fr.unilim.iut.spaceinvaders.model.Sprite;

public class Collision {
	
	public static boolean detecterCollision(Sprite sprite1, Sprite sprite2){
		if(sprite1.occupeLaPosition(sprite2.abscisseLaPlusAGauche(),sprite2.ordonneeLaPlusBasse())){
			return true;
		}
		if(sprite1.occupeLaPosition(sprite2.abscisseLaPlusAGauche(), sprite2.ordonneeLaPlusHaute())){
			return true;
		}
		if(sprite1.occupeLaPosition(sprite2.abscisseLaPlusADroite(), sprite2.ordonneeLaPlusBasse())){
			return true;
		}
		if(sprite1.occupeLaPosition(sprite2.abscisseLaPlusADroite(), sprite2.ordonneeLaPlusHaute())){
			return true;
		}
		if(sprite2.occupeLaPosition(sprite1.abscisseLaPlusAGauche(), sprite1.ordonneeLaPlusBasse())){
			return true;
		}
		if(sprite2.occupeLaPosition(sprite1.abscisseLaPlusAGauche(), sprite1.ordonneeLaPlusHaute())){
			return true;
		}
		if(sprite2.occupeLaPosition(sprite1.abscisseLaPlusADroite(), sprite1.ordonneeLaPlusBasse())){
			return true;
		}
		if(sprite2.occupeLaPosition(sprite1.abscisseLaPlusADroite(), sprite1.ordonneeLaPlusHaute())){
			return true;
		}
		return false;
	}
}
