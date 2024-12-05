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
