package etats;

import java.awt.Color;
import java.awt.Dimension;

import trace.BilanEtat;
import trace.BilanRole;
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
	@Override
	public boolean deposePheromone() {
		return false;
	}

	@Override
	public void updateBilanRole(BilanRole bilan) {		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nymphe";
	}@Override
	public boolean mangeNourriture() {
		// TODO Auto-generated method stub
		return false;
	}	
	
	
}
