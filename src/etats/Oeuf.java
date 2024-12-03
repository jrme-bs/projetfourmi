package etats;

import java.awt.Color;
import java.awt.Dimension;

import trace.Bilan;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Oeuf extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.white);
		vue.setDimension(new Dimension(3, 3));
	}

	@Override
	public void updateBilan(Bilan bilan) {
		bilan.setNbOeuf(bilan.getNbOeuf()+1);
	}
	
	
}
