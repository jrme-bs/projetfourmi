package fourmiliere;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import etresVivants.Fourmi;
import trace.BilanEtat;
import trace.BilanRole;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public class Fourmiliere {
	private List<Fourmi> population;
	private List<Point> deplacements;
	private Point pos;
	private Dimension dim;
	private BilanEtat bilanEtat;
	private BilanRole bilanRole;
	
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
		this.bilanEtat = new BilanEtat();
		this.bilanRole = new BilanRole();
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
			fourmi.editNbEtat(bilanEtat);
			fourmi.editNbRole(bilanRole);
			fourmi.etapeDeSimulation(contexte);
		}
		bilanEtat.afficheNbEtatFourmiliere();
		bilanRole.afficheNbRoleFourmiliere();
	}
		
}
