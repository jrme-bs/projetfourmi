package etats;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

import trace.BilanEtat;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Mort extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}
	
	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.white);
		vue.setDimension(new Dimension(3, 3));
		vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
	}

	@Override
	public void updateBilanEtat(BilanEtat tan) {
		tan.setNbMort(tan.getNbMort()+1);
	}
	@Override
	public boolean deposePheromone() {
		return false;
	}
	
}
