package etats;

import java.awt.Color;
import java.awt.Dimension;

import trace.BilanEtat;
import trace.BilanRole;
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
	public void updateBilanEtat(BilanEtat bilan) {
		bilan.setNbOeuf(bilan.getNbOeuf()+1);
	}
	@Override
	public boolean deposePheromone() {
		return false;
	}

	@Override
	public void updateBilanRole(BilanRole bilan) {
	}
	
}
