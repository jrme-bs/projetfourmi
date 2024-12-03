package etats;

import java.awt.Color;
import java.awt.Dimension;

import trace.BilanEtat;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Nymphe extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.pink);
		vue.setDimension(new Dimension(5, 5));
	}

	@Override
	public void updateBilanEtat(BilanEtat bilan) {
		bilan.setNbNymphe(bilan.getNbNymphe()+1);
	}
	
	
	
}
