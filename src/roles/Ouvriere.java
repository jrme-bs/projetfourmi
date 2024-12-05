package roles;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Random;


import etresVivants.Fourmi;
import fourmiliere.Fourmiliere;
import proie.Proie;
import terrain.Terrain;
import trace.BilanRole;
import vue.ContexteDeSimulation;
import vue.Simulation;
import zone.Zone;

public class Ouvriere extends Role {

	public Ouvriere() {
	}

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		
		Fourmi fourmi = (Fourmi) contexte.getIndividu();
		int gap = 10;
		int x = fourmi.getPos().x;
		int y = fourmi.getPos().y;
		
		Point deplacement = new Point(x,y);
		
		if (fourmi.isDragged() && fourmi.isChasse() == false) {
			deplacement = rameneProiePheromone(contexte, x, y, gap, fourmi);
			//deplacement = rameneProieSansPheromone(contexte, x, y, gap, fourmi);
		}else if (fourmi.isChasse() && fourmi.isDragged() == false){
			
		}else {
			deplacement = this.mouvementRandom(x, y, gap);
		}
		fourmi.setPos(deplacement);
	}

	@Override
	public void updateBilanRole(BilanRole bilan) {
		bilan.setNbOuvriere(bilan.getNbOuvriere()+1);
	}
	
	public Point rameneProiePheromone(ContexteDeSimulation contexte, int x, int y, int gap, Fourmi fourmi) {
		Simulation sim = contexte.getSimulation();
		Terrain ter = sim.getTerrain();
		
		Fourmiliere fourmiliere = ter.getFourmiliere();
		
		Point pointFourmiliere = fourmiliere.getPos();
		
		int indexZoneFourmi = 0;
		
		Map<Integer, Zone> mapZ = ter.getMapZone();
		
		for (Map.Entry<Integer, Zone> entry : mapZ.entrySet()) {
			Zone z = entry.getValue();
			
			if (z.getListeFourmi().contains(fourmi)) {
				indexZoneFourmi = entry.getKey();
			}
		}
		
		Zone droite = mapZ.get(indexZoneFourmi + 1);
		Zone gauche = mapZ.get(indexZoneFourmi - 1);
		Zone haut = mapZ.get(indexZoneFourmi - ter.getNbLigne()-1);
		Zone bas = mapZ.get(indexZoneFourmi + ter.getNbLigne()+1);
		
		System.out.println("===============================");
		System.out.println("zone D : " + droite);
		System.out.println("zone G : " + gauche);
		System.out.println("zone H : " + haut);
		System.out.println("zone B : " + bas);
		System.out.println("===============================");
		
		int pheroD = droite.getIntensitePheromones();
		int pheroG = gauche.getIntensitePheromones();
		int pheroH = haut.getIntensitePheromones();
		int pheroB = bas.getIntensitePheromones();
		
		int totalPhero = pheroD + pheroG + pheroH + pheroB;
		int x1 = x;
		int y1 = y;
		
		Random rand = new Random();
        int randomValue = rand.nextInt(totalPhero);
        
        if (randomValue < pheroD) {
        	// droite
            x1 += gap;
        } else if (randomValue < pheroD + pheroG) {
        	// gauche
            x1 -= gap;
        } else if (randomValue < pheroD + pheroG + pheroH) {
        	// haut
            y1 -= gap;
        } else {
        	// bas
            y1 += gap;
        }
        
        int pointFourmX = pointFourmiliere.x + 40;
		int pointFourmY = pointFourmiliere.y + 40;
		
		int tailleCarre = 20; // Taille du carré (par exemple 50x50 pixels)
	    int limiteMinX = pointFourmiliere.x - tailleCarre / 2;
	    int limiteMaxX = pointFourmiliere.x + tailleCarre / 2;
	    int limiteMinY = pointFourmiliere.y - tailleCarre / 2;
	    int limiteMaxY = pointFourmiliere.y + tailleCarre / 2;

	    // Vérifier si la nouvelle position (x1, y1) est dans le carré
	    if (x1 >= limiteMinX && x1 <= limiteMaxX && y1 >= limiteMinY && y1 <= limiteMaxY) {
	    	for (Proie p : ter.getListeProie()) {
				if (p.getFourmiProie() == fourmi) {
					p.setFood(true);
				}
			}
	        System.out.println("Dans la zone de mangeoire fourmis");
	        return new Point(x, y); // Rester sur place
	    }
		
		return new Point(x1,y1);
	}
	
	public Point rameneProieSansPheromone(ContexteDeSimulation contexte, int x, int y, int gap, Fourmi fourmi) {
		Simulation sim = contexte.getSimulation();
		Terrain ter = sim.getTerrain();
		
		Fourmiliere fourmiliere = ter.getFourmiliere();
		
		Point pointFourmiliere = fourmiliere.getPos();
		
		int cpt = 0;
		
		int pointFourmX = pointFourmiliere.x + 40;
		int pointFourmY = pointFourmiliere.y + 40;
		
		int x1 = x;
		int y1 = y;
		
		if (x < pointFourmX) {
			x1 = x + gap;
		}else {
			cpt++;
		}
		
		if (x > pointFourmX) {
			x1 = x - gap;
		}else {
			cpt++;
		}
		
		if (y < pointFourmY) {
			y1 = y + gap;
		}else {
			cpt++;
		}
		
		if (y > pointFourmY) {
			y1 = y - gap;
		}else {
			cpt++;
		}
		
		if (cpt == 4) {
			for (Proie p : ter.getListeProie()) {
				if (p.getFourmiProie() == fourmi) {
					p.setFood(true);
				}
			}
		}
		
		return new Point(x1,y1);
		
	}
	
	public Point mouvementRandom(int x, int y, int gap) {
		int x1 = x, y1 = y;
		
		int pos;
		Random rand = new Random();
		pos = rand.nextInt(4);
		switch (pos) {
			case 0: {
				y1 = y + gap;
				break;
			}
			case 1: {
				x1 = x + gap;
				break;
			}
			case 2: {
				y1 = y - gap;
				break;
			}
			case 3: {
				x1 = x - gap;
				break;
			}
			
		}
		
		return new Point(x1,y1);
	}
	
	
	@Override
	public boolean deposePheromone() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Ouvriere";
	}
}
