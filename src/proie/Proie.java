package proie;

import java.awt.Point;
import java.awt.Color;
import java.util.Map;
import java.util.Random;

import etats.Adulte;
import etats.Mort;
import vue.ContexteDeSimulation;
import vue.Simulation;
import vue.VueProie;
import zone.Zone;
import etresVivants.Fourmi;
import terrain.Terrain;

public class Proie {
	
	private Fourmi fourmi;
	private Point pos;
	private int poids;
	private boolean vivante;
	private int step = 5;
	private boolean dragged;
	private boolean chasse;
	private boolean isFood;
	private int sante;
	
	private boolean changementCouleurOne = false;
	
	public Proie(Point pos, int poids)
	{
		this.pos = pos;
		this.poids = poids;
		this.vivante = true;
		this.dragged = false;
		this.chasse = false;
		this.sante = 10;
		this.isFood = false;
	}

	public Point getPosition()
	{
		return this.pos;
	}
	
	public void setPosition(Point pos)
	{
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public int getPoids() 
	{
		return poids;
	}
	
	public boolean getVivante()
	{
		return this.vivante;
	}
	
	public boolean isDragged() {
		return dragged;
	}

	public void setDragged(boolean dragged) {
		this.dragged = dragged;
	}

	public boolean getChasse() {
		return chasse;
	}

	public void setChasse(boolean chasse) {
		this.chasse = chasse;
	}

	public void tueProie()
	{
		this.vivante = false;
	}
	public int diminuerPoids(int valeur)
	{
		if (this.poids - valeur < 0) {
			int val = this.poids;
			this.poids = 0;
			return val;
		}
		else {
			this.poids -= valeur;
			return valeur;
		}
		
		
	}
	
	// Fonctionne
	public void trouveFourmiDrag(ContexteDeSimulation contexte)
	{
		
		Simulation sim = contexte.getSimulation();
		Terrain ter = sim.getTerrain();

		Zone curZone = null;

		// parcours de la map de zone
		for (Map.Entry<Integer, Zone> entry : ter.getMapZone().entrySet()) {
			Zone z = entry.getValue();
		    if (z.getListeProie().contains(this)) {
		    	curZone = z;
		    }
		}
		
		if (curZone != null) {

			for (Fourmi f : curZone.getListeFourmi()) {
				if (f.isDragged() == false && f.getEtat() instanceof Adulte) {
					fourmi = f;
				}
			}
		}
	}
	
	// cette fonction est de traîner la proie vers la fourmilière
	public Point dragProie()
	{
		Point p = fourmi.getPos();
		return p;
	}
	
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		contexte.setProie(this);
		int x = this.getPosition().x;
		int y = this.getPosition().y;
		
		if(this.getVivante() && chasse == false)
		{
			
			int pos;
			Random rand = new Random();
			pos = rand.nextInt(4);
			
			switch (pos) {
				case 0: {
					y = y + step;
					break;
				}
				case 1: {
					x = x + step;
					break;
				}
				case 2: {
					y = y - step;
					break;
				}
				case 3: {
					x = x - step;
					break;
				}
			}
		}else if (chasse) {
			// ne bouge plus s'il se fait chasser et baisse la vie de la proie
			if (sante > 0) {
				sante--;
			}else {
				this.chasse = false;
				this.tueProie();
				this.dragged = true;
			}
		}else if (this.fourmi.getEtat() instanceof Mort && this.getVivante() == false){
			this.fourmi.setDragged(false);
			this.setDragged(false);
			this.trouveFourmiDrag(contexte);
			
		}if (isFood) {
			// ne fait plus rien car c'est un garde mangé et la fourmi la dépose
			this.setDragged(false);
			this.fourmi.setDragged(false);
			this.fourmi.setChasse(false);
			
		}else {
			// Fonctionne mais la fourmis et la proie disparaît visuellement
			// cette fourmi porte la proie
			if (dragged) {
				// trouve la fourmi et la met en instance de proie
				this.trouveFourmiDrag(contexte);
				// anti fourmi == null
				if (this.fourmi != null) {
					this.fourmi.setDragged(true);
					
					Point point = this.dragProie();
					this.fourmi.setChasse(false);
					x = point.x;
					y = point.y;
				}
				
			}
		}
		// déplacement de la proie
		this.setPosition(new Point(x,y));
	}
	
	// Changement de couleur de la proie lorsqu'elle meurt (normalement)
	public void initialise(VueProie vue) {
		if (this.getVivante() == false && this.changementCouleurOne == false) {
			vue.setBackground(Color.black);
			this.changementCouleurOne = true;
		}
	}

	//cette fonction récupère la fourmi qui traîne la proie
	public Fourmi getFourmiProie() {
		return fourmi;
	}

	//cette fonction retourne si la proie est devenue une nourriture
	public boolean isFood() {
		return isFood;
	}

	//cette fonction pour rendre la proie une nourriture
	public void setFood(boolean isFood) {
		this.isFood = isFood;
	}
	
	

}
	

