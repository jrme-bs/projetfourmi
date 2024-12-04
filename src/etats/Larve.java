package etats;


import java.awt.Color;
import java.awt.Dimension;

import trace.BilanEtat;
import trace.BilanRole;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Larve extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.yellow);
		vue.setDimension(new Dimension(5, 5));
	}

	@Override
	public void updateBilanEtat(BilanEtat bilan) {
		bilan.setNbLarve(bilan.getNbLarve()+1);
	}

	@Override
	public boolean deposePheromone() {
		return false;
	}

	@Override
	public void updateBilanRole(BilanRole bilan) {		
	}
}
