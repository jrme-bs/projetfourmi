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
import zone.Zone;

public class Terrain {
	protected Point pos;
	protected Dimension dim;
	Fourmiliere fourmiliere;
	List<Proie> listeProie = new ArrayList<Proie>();
	List<Zone> listeZone = new ArrayList<Zone>();
	
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
	
	public void ajoutProieDansListe(Proie proie) {
		Point point = new Point();
		point = proie.getPosition();
		
		for (Zone z : listeZone) {
			boolean inZoneX = point.x > z.getPoint().x && point.x < z.getPoint().x + z.getDim().width;
			boolean inZoneY = point.y > z.getPoint().y && point.y < z.getPoint().y + z.getDim().height;
			if (inZoneX && inZoneY)
			{
				z.addProie(proie);
			}
		}
	}
	
	public void ajoutFourmiDansListe(Fourmi fourmi) {
		Point point = new Point();
		point = fourmi.getPos();
		
		for (Zone z : listeZone) {
			boolean inZoneX = point.x > z.getPoint().x && point.x < z.getPoint().x + z.getDim().width;
			boolean inZoneY = point.y > z.getPoint().y && point.y < z.getPoint().y + z.getDim().height;
			if (inZoneX && inZoneY)
			{
				z.addFourmi(fourmi);
				if (fourmi.getEtat() instanceof Adulte) {
					//System.out.println("Fourmis entre dans la zone : " + point);
				}
			}
		}
	}
	
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		for (Zone z : listeZone) {
			z.etapeDeSimulation(contexte);
		}
		if (listeZone.isEmpty()) {
			for(int x = 0 ; x < this.dim.width ; x= x + 30 )
			{
				for(int y = 0 ; y < this.dim.height ; y = y + 30)
				{
					Point point = new Point(x,y);
					Dimension dimZone = new Dimension(30,30);
					Zone zone = new Zone(point,dimZone);
					this.listeZone.add(zone);
					contexte.getSimulation().nouvelleZone(zone);
				}
			}
		}
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
		
		
		
		if(listeProie.isEmpty()){
			for(int i = 0 ; i < 10 ; i++)
			{
				int x;
				int y;
				int poids ;
				Proie proie;
				Random rand = new Random();
				x = rand.nextInt(700);
				y = rand.nextInt(700);
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

}
