package terrain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.Dimension;

import etats.Adulte;
import etresVivants.Fourmi;
import fourmiliere.Fourmiliere;
import proie.Proie;
import roles.Reine;
import vue.ContexteDeSimulation;
import zone.Zone;

public class Terrain {
	protected Point pos;
	protected Dimension dim;
	Fourmiliere fourmiliere;
	List<Proie> listeProie = new ArrayList<Proie>();
	int tailleZone = 17;
	// nombre de colonne et de ligne pour mieu récupérer les zones dans la map
	private int nbCol;
	private int nbLigne;
	
	Map<Integer, Zone> mapZone = new HashMap<Integer, Zone>();
	
	public Point getPos() {
		return this.pos;
	}

	public Dimension getDimension() {
		return this.dim;
	}


	public Terrain(Point pos, Dimension dim) {
		this.pos = pos;
		this.dim = dim;
		this.nbCol = dim.width / tailleZone;
		this.nbLigne = dim.height / tailleZone;
	}
	
	public void ajoutProieDansListe(Proie proie) {
		Point point = new Point();
		point = proie.getPosition();
		
		for (Map.Entry<Integer, Zone> entry : mapZone.entrySet()) {
		    Zone z = entry.getValue();
		    boolean inZoneX = (point.x >= z.getPoint().x) && (point.x < z.getPoint().x + z.getDim().width);
			boolean inZoneY = (point.y >= z.getPoint().y) && (point.y < z.getPoint().y + z.getDim().height);
			if (inZoneX && inZoneY)
			{
				z.addProie(proie);
			}
		}
	}
	
	public void ajoutFourmiDansListe(Fourmi fourmi) {
		Point point = new Point();
		point = fourmi.getPos();
		
		for (Map.Entry<Integer, Zone> entry : mapZone.entrySet()) {
		    Zone z = entry.getValue();
		    boolean inZoneX = (point.x >= z.getPoint().x) && (point.x < z.getPoint().x + z.getDim().width);
			boolean inZoneY = (point.y >= z.getPoint().y) && (point.y < z.getPoint().y + z.getDim().height);
			if (inZoneX && inZoneY)
			{
				z.addFourmi(fourmi);
			}
		}
	}
	
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		
		if (this.mapZone.isEmpty()) {
			
			int cpt = 1; // index pour la map de zone

			for(int y = 0 ; y < this.dim.height ; y = y + tailleZone)
			{
				for(int x = 0 ; x < this.dim.width ; x= x + tailleZone )
				{
					Point point = new Point(x,y);
					Dimension dimZone = new Dimension(tailleZone,tailleZone);
					Zone zone = new Zone(point,dimZone);
					//this.listeZone.add(zone);
					this.mapZone.put(cpt, zone);
					contexte.getSimulation().nouvelleZone(zone);
					cpt++; // index des zones dans la map
				}
			}
		}else {
			
			for (Map.Entry<Integer, Zone> entry : mapZone.entrySet()) {
				// affichage contenu map
			    //System.out.println("Clé (index): " + entry.getKey() + ", Valeur (zone): " + entry.getValue());
			    entry.getValue().etapeDeSimulation(contexte);
			}
		}
		
		
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			fourmiliere = new Fourmiliere(p);
			Point posReine = new Point(p.x + 40, p.y + 40);
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
		
		if(listeProie.isEmpty()){
			for(int i = 0 ; i < 10 ; i++)
			{
				int x;
				int y;
				int poids ;
				Proie proie;
				Random rand = new Random();
				x = rand.nextInt(500);
				y = rand.nextInt(500);
				poids = rand.nextInt(130);
				Point p = new Point(x,y);
				proie = new Proie(p,poids);
				listeProie.add(proie);
				
			}
				
		}
		for(Proie p : listeProie)
		{
			contexte.getSimulation().nouvelleProie(p);
			p.etapeDeSimulation(contexte);
			
			this.ajoutProieDansListe(p);
			
		}
		
		for(Fourmi f : this.fourmiliere.getPopulation()) {
			this.ajoutFourmiDansListe(f);
		}
	}
	
	public Map<Integer, Zone> getMapZone() {
		return mapZone;
	}

	public Dimension getDim() {
		return dim;
	}

	public int getTailleZone() {
		return tailleZone;
	}

	public Fourmiliere getFourmiliere() {
		return fourmiliere;
	}

	public List<Proie> getListeProie() {
		return listeProie;
	}

	public int getNbCol() {
		return nbCol;
	}

	public int getNbLigne() {
		return nbLigne;
	}

	
	
}
