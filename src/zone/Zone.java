package zone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import java.util.ArrayList;

import etresVivants.Fourmi;
import proie.Proie;
import vue.ContexteDeSimulation;
import vue.VueZone;

public class Zone {
	protected Point point;
	protected Dimension dim;
	List<Proie> listeProie ;
	List<Fourmi> listeFourmi;
	int intensitePheromones = 0;
	int jours = 0;
	int x;
	int y;
	
	public Zone(Point point, Dimension dim)
	{
		this.point = point ;
		this.dim = dim;
		this.listeProie = new ArrayList<Proie>();
		this.listeFourmi = new ArrayList<Fourmi>();
	}
	
	public Point getPoint() {
		return point;
	}
	

	public void setPoint(Point point) {
		this.point = point;
	}


	public Dimension getDim() {
		return dim;
	}


	public void setDim(Dimension dim) {
		this.dim = dim;
	}


	public List<Proie> getListeProie() {
		return listeProie;
	}

	public void setListeProie(List<Proie> listeProie) {
		this.listeProie = listeProie;
	}

	public List<Fourmi> getListeFourmi() {
		return listeFourmi;
	}

	public void setListeFourmi(List<Fourmi> listeFourmi) {
		this.listeFourmi = listeFourmi;
	}

	public void addProie(Proie proie)
	{
		this.listeProie.add(proie);
	}
	
	public void addFourmi(Fourmi fourmi)
	{
		if (!this.listeFourmi.contains(fourmi)) {
			this.listeFourmi.add(fourmi);
		}
		if(fourmi.getEtat().deposePheromone() && this.intensitePheromones < 20) {
			this.intensitePheromones++;
		}
	}
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		jours++;
		// stop le mouvement des proies et fourmis dans une même zone et les considères en mode chasse
		boolean test = this.chasseEnCours();
		if (!test) {
			this.clearListZone();
		}
		this.updateIntensite();
		
	}
	
	public void clearListZone() {
		getListeFourmi().clear();
		getListeProie().clear();
	}
	
	public void updateIntensite() {
		// tout les 5 jours on reduit l'intensit�
		if(jours % 5 == 0 && this.intensitePheromones > 0) {
			this.intensitePheromones--;
		}
	}
	
	public void initialise(VueZone vue) {
		vue.setBackground(new Color(255, 0, 0, this.intensitePheromones*8));
	}

	public int getIntensitePheromones() {
		return intensitePheromones;
	}
	
	// défini si une zone est en mode chasse
	public boolean chasseEnCours() {
		
		if (listeProie.isEmpty() || listeFourmi.isEmpty()) {
	        return false;
	    }
		
		int cpt = 0;
		
		for (Proie p : listeProie) {
    		if (p.isFood() == false) {
    			p.setChasse(true);
    		}else {
    			cpt++;
    		}
	    }
		
		if (cpt >= listeProie.size()) {
			return false;
		}
		
		for (Fourmi f : listeFourmi) {
            if (!f.isDragged() && !(f.getEtat().toString() == "Mort")) {
                f.setChasse(true);
            }
        }

	    return true;
		
	}

	@Override
	public String toString() {
		return "Zone [point=" + point + ", dim=" + dim + ", listeProie=" + listeProie + ", listeFourmi=" + listeFourmi
				+ ", intensitePheromones=" + intensitePheromones + "]";
	}

	
}
