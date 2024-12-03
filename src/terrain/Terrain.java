package terrain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Dimension;

import etats.Adulte;
import etresVivants.Fourmi;
import fourmiliere.Fourmiliere;
import proie.Proie;
import roles.Reine;
import vue.ContexteDeSimulation;
import vue.VueFourmiliere;


public class Terrain {
	protected Point pos;
	protected Dimension dim;
	Fourmiliere fourmiliere;
	List<Proie> ListeProie = new ArrayList<>();;
	Proie proie;
	
	public Point getPos() {
		return this.pos;
	}

	public Dimension getDimension() {
		return this.dim;
	}


	public Terrain(Point pos, Dimension dim) {
		this.pos = pos;
		this.dim = dim;
	}
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			fourmiliere = new Fourmiliere(p);
			Point posReine = new Point(p.x + 15, p.y + 15);
			Fourmi laReine = new Fourmi(posReine);

			laReine.setAge(30);
			laReine.setDureeDeVie(500);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			contexte.getSimulation().nouvelIndividu(laReine);
			
			fourmiliere.setReine(laReine);
			contexte.getSimulation().nouvelleFourmiliere(fourmiliere);

		}
		fourmiliere.etapeDeSimulation(contexte);
		
		
		
		if(ListeProie.isEmpty()){
			for(int i = 0 ; i < 10 ; i++)
			{
				int x;
				int y;
				int poids ;
				Proie proie;
				Random rand = new Random();
				x = rand.nextInt(700);
				y = rand.nextInt(700);
				poids = rand.nextInt(150);
				Point p = new Point(x,y);
				proie = new Proie(p,poids);
				ListeProie.add(proie);
				
			}
				
		}
		for(Proie p : ListeProie)
		{
			contexte.getSimulation().nouvelleProie(p);
			p.etapeDeSimulation(contexte);
		}
		
		
	}

}
