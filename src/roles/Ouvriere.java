package roles;

import java.awt.Point;
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
			//deplacement = rameneProiePheromone(contexte, x, y, gap, fourmi);
			deplacement = rameneProieSansPheromone(contexte, x, y, gap, fourmi);
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
		
		int pheroD = 0;
		if (droite != null) {
			pheroD = droite.getIntensitePheromones();
		}
		
		int pheroG = 0;
		if (gauche != null) {
			pheroG = gauche.getIntensitePheromones();
		}
		int pheroH = 0;
		if (haut != null) {
			pheroH = haut.getIntensitePheromones();
		}
		int pheroB = 0;
		if (bas != null) {
			pheroB = bas.getIntensitePheromones();
		}
		
		if (pheroD == 0 && pheroG == 0 && pheroH == 0 && pheroB == 0) {
            pheroD = pheroG = pheroH = pheroB = 1;
        }
		
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
        } else if (randomValue < pheroD + pheroG + pheroH + pheroB) {
        	// bas
            y1 += gap;
        }else {
        	System.out.println("Impossible ICI OUVRIERE PHERO");
        }
        
        int pointFourmX = pointFourmiliere.x + 40;
		int pointFourmY = pointFourmiliere.y + 40;
		
		int tailleCarre = 30;
	    int limiteMinX = pointFourmX - tailleCarre / 2;
	    int limiteMaxX = pointFourmX + tailleCarre / 2;
	    int limiteMinY = pointFourmY - tailleCarre / 2;
	    int limiteMaxY = pointFourmY + tailleCarre / 2;

	    // vérifie si la fourmi qui porte la proie est dans la fourmilière
	    if (x1 >= limiteMinX && x1 <= limiteMaxX && y1 >= limiteMinY && y1 <= limiteMaxY) {
	    	for (Proie p : ter.getListeProie()) {
				if (p.getFourmiProie() == fourmi) {
					p.setFood(true);
				}
			}
	        System.out.println("Dans la zone de mangeoire fourmis");
	        // la fourmi repart avec ses mouvement de aléatoire
	        fourmi.setDragged(false);
	        return new Point(x, y);
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
		
		int tailleCarre = 20;
	    int limiteMinX = pointFourmX - tailleCarre / 2;
	    int limiteMaxX = pointFourmX + tailleCarre / 2;
	    int limiteMinY = pointFourmY - tailleCarre / 2;
	    int limiteMaxY = pointFourmY + tailleCarre / 2;

	    // vérifie si la fourmi qui porte la proie est dans la fourmilière
	    if (x1 >= limiteMinX && x1 <= limiteMaxX && y1 >= limiteMinY && y1 <= limiteMaxY) {
	    	for (Proie p : ter.getListeProie()) {
				if (p.getFourmiProie() == fourmi) {
					p.setFood(true);
				}
			}
	        System.out.println("Dans la zone de mangeoire fourmis");
	        // la fourmi repart avec ses mouvement de aléatoire
	        fourmi.setDragged(false);
	        return new Point(x, y);
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
	
}
