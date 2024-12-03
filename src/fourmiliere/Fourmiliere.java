package fourmiliere;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import etresVivants.Fourmi;
import trace.Bilan;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Fourmiliere {
	private List<Fourmi> population;
	private List<Point> deplacements;
	private Point pos;
	private Dimension dim;
	private Bilan bilan;
	
	public Point getPos() {
		return pos;
	}

	public Dimension getDimension() {
		return dim;
	}

	public Fourmiliere(Point pos) {
		this.population = new ArrayList<>();
		this.pos = pos;
		this.dim = new Dimension(80,80);
		this.bilan = new Bilan();
	}

	public void ponte(Fourmi oeuf) {
		this.population.add(oeuf);
	}
	
	public void setReine(Fourmi reine) {
		population.add(reine);
	}
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Set<Point> s = new HashSet<Point>();

		Fourmi[] mesFourmis = this.population.toArray(new Fourmi[this.population.size()]);
		contexte.setFourmiliere(this);
		for (Fourmi fourmi : mesFourmis) {
			if(!s.contains(fourmi.getPos())) {
				contexte.setDeplacement(fourmi.getPos());
				s.add(fourmi.getPos());
			}
			fourmi.editNbEtat(bilan);
			fourmi.etapeDeSimulation(contexte);
		}
		bilan.afficheNbEtatFourmiliere();
	}
		
}
