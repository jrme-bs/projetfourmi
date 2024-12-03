package fourmiliere;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import etresVivants.Fourmi;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Fourmiliere {
	private List<Fourmi> population;
	private List<Point> deplacements;
	private Point pos;
	private Dimension dim;
	
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
	}

	public void ponte(Fourmi oeuf) {
		this.population.add(oeuf);
	}
	
	public void setReine(Fourmi reine) {
		population.add(reine);
	}
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Fourmi[] mesFourmis = this.population.toArray(new Fourmi[this.population.size()]);
		contexte.setFourmiliere(this);
		for (Fourmi fourmi : mesFourmis) {
			contexte.setDeplacement(fourmi.getPos());
			fourmi.etapeDeSimulation(contexte);
		}
	}
		
}
