package etresVivants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;


import etats.Adulte;
import etats.Etat;
import etats.Larve;
import etats.Mort;
import etats.Nymphe;
import etats.Oeuf;
import trace.BilanEtat;
import trace.BilanRole;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


public class Fourmi extends Individu {
	private int dureeDeVie;
	private Etat etat;
	private int age;
	private int niveauSatiete = 100;
	private boolean chasse;
	private boolean dragged;
	
	public Fourmi(Point point) {
		this.setAge(0);
		this.setEtat(new Oeuf());
		this.setPos(point);
		this.chasse = false;
		this.dragged = false;
	}
	
	
	public boolean isChasse() {
		return chasse;
	}

	public void setChasse(boolean chasse) {
		this.chasse = chasse;
	}

	public boolean isDragged() {
		return dragged;
	}

	public void setDragged(boolean dragged) {
		this.dragged = dragged;
	}



	public int getDureeDeVie() {
		return dureeDeVie;
	}

	public void setDureeDeVie(int dureeDeVie) {
		this.dureeDeVie = dureeDeVie;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void evolution() {
		this.age ++;
		switch (this.age) {
			case 3: 
				this.etat = new Larve();
				this.setPoids(6);
				break;
			case 10: 
				this.etat = new Nymphe();
				this.setPoids(0);
				break;
			case 20: 
				this.etat = new Adulte();
				this.setPoids(2);
				break;
		}
		
		if (this.age == this.dureeDeVie) {
			this.etat = new Mort();
			this.setPoids(0);
		}
	}
	
	public void initialise(VueIndividu vue) {
		this.etat.initialise(vue);
	}

	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		super.etapeDeSimulation(contexte);
		this.evolution();
		this.etat.etapeDeSimulation(contexte);
	}
	
	public void editNbEtat(BilanEtat bilan) {
		etat.updateBilanEtat(bilan);
	}
	
	public void editNbRole(BilanRole bilan) {
		this.getEtat().updateBilanRole(bilan);
	}
	
	//C'est pour que la fourmi mange
	//Si il y a assez de nourriture elle reste en vie 
	//Sinon elle meurt
	public void mange(int quantite)
	{
		if(this.getEtat().mangeNourriture())
		{
			if(this.getQuantiteNourriture() > quantite)
			{
				this.niveauSatiete = 0 ;
				this.etat = new Mort();
				this.setPoids(0);
				
			}
			else
			{
				this.niveauSatiete = 100;
			}
		}
	}
	
	//Pour recuperer la quantite que chaque fourmi doit manger
	public int getQuantiteNourriture()
	{
		Etat etat = this.getEtat();
		if(etat instanceof Larve)
		{
			return (int) this.getPoids();
		}
		return (int) this.getPoids() * 1/3;
		
	}

}
