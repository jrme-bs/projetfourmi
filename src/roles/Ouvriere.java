package roles;

import java.awt.Point;
import java.util.List;
import java.util.Random;


import etresVivants.Fourmi;
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
			//deplacement = this.detectPheromone(contexte, fourmi, x, y, gap);
			deplacement = this.mouvementRandom(x, y, gap);
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
	
	/*public Point detectPheromone(ContexteDeSimulation contexte, Fourmi fourmi, int x, int y, int gap) {
		
		int x1 = x;
		int y1 = y;
		
		Zone haute = null;
		Zone basse = null;
		Zone gauche = null;
		Zone droite = null;
		
		Simulation curSim = contexte.getSimulation();
		Terrain curTerrain = curSim.getTerrain();
		List<Zone> listeZone = curTerrain.getListeZone();
		
		Zone[] listeZ = (Zone[])listeZone.toArray();
		
		int gapColonne = curTerrain.getDimension().height / curTerrain.getTailleZone();
		
		for (int i = 0; i < listeZ.length; i++) {

			Fourmi[] listeF = (Fourmi[]) listeZ[i].getListeFourmi().toArray();
			for (int  j = 0; j < listeF.length; j++) {
				if (listeF[j].equals(fourmi)) {
					haute = listeZ[i+1];
					basse = listeZ[i-1];
					droite = listeZ[i+gapColonne];
					gauche = listeZ[i-gapColonne];
					
					i = listeZ.length;
					j = listeF.length;
				}
			}
		}
		
		int bas = basse.getIntensitePheromones();
		int haut = haute.getIntensitePheromones();
		int g = gauche.getIntensitePheromones();
		int d = droite.getIntensitePheromones();
		
		Random rand = new Random();
		int probaBas = rand.nextInt(bas);
		int probaHaut = rand.nextInt(haut);
		int probaGauche = rand.nextInt(g);
		int probaDroite = rand.nextInt(d);
		
		int max1 = Math.max(probaBas, probaHaut);
		int max2 = Math.max(probaGauche, probaDroite);
		int max = Math.max(max1, max2);
		
		if (max == probaBas) {
		    y1 -= gap;
		} else if (max == probaHaut) {
		    y1 += gap;
		} else if (max == probaGauche) {
		    x1 -= gap;
		} else if (max == probaDroite) {
		    x1 += gap;
		}
		
		return new Point(x1, y1);
	}
	*/
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
