package fourmiliere;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import etresVivants.Fourmi;
import proie.Proie;
import terrain.Terrain;
import trace.BilanEtat;
import trace.BilanRole;
import vue.ContexteDeSimulation;
import vue.Simulation;
import vue.VueIndividu;

public class Fourmiliere {
	private List<Fourmi> population;
	private List<Point> deplacements;
	
	private List<Proie> nourriture;
	private List<Proie> proiesConsommees;
	
	private List<Fourmi> fourmisMortes;
	
	private int nourritureTotale;
	
	private Point pos;
	private Dimension dim;
	private BilanEtat bilanEtat;
	private BilanRole bilanRole;
	
	public Point getPos() {
		return pos;
	}

	public Dimension getDimension() {
		return dim;
	}

	public Fourmiliere(Point pos) {
		this.population = new ArrayList<>();
		this.nourriture = new ArrayList<>();
		this.proiesConsommees = new ArrayList<>();
		this.fourmisMortes = new ArrayList<>();
		this.pos = pos;
		this.dim = new Dimension(80,80);
		this.bilanEtat = new BilanEtat();
		this.bilanRole = new BilanRole();
		
		this.nourritureTotale = 0;
	}

	public void ponte(Fourmi oeuf) {
		this.population.add(oeuf);
	}
	
	public void setReine(Fourmi reine) {
		population.add(reine);
	}
	
	public List<Fourmi> getPopulation() {
		return population;
	}

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Set<Point> s = new HashSet<Point>();

		Fourmi[] mesFourmis = this.population.toArray(new Fourmi[this.population.size()]);
		contexte.setFourmiliere(this);
		for (Fourmi fourmi : mesFourmis) {
			fourmi.editNbEtat(bilanEtat);
			fourmi.editNbRole(bilanRole);
			fourmi.etapeDeSimulation(contexte);
		}
		bilanEtat.afficheNbEtatFourmiliere();
		bilanRole.afficheNbRoleFourmiliere();
		
		ajoutAutoProieNourritureAndConsommee(contexte);
		// Calcule la nourriture actuelle dans la fourmilière
		compteNourritureTotaleInstantannee();
		// Affichage Nourriture actuelle
		System.out.println("Nourriture totale : " + this.nourritureTotale);
	}
	
	
	//Pour ajouter les proies 
	public void addProieListeNourriture(Proie proie) {
		this.nourriture.add(proie);
	}
	
	//Methode pour nettoyer les proies qui sont consommées 
	public void nettoyageProies()
	{
		for(Proie p : nourriture)
		{
			if(p.getPoids() == 0)
			{
				this.proiesConsommees.add(p);
			}
		}
		for(Proie p : proiesConsommees)
		{
			this.nourriture.remove(p);
		}
	}
	
	//Methode pour ajouter les fourmis mortes dans
	//la liste fourmisMortes
	public void nettoyageFourmisMortes()
	{
		for(Fourmi f : this.getPopulation())
		{
			if(f.getEtat().toString().equals("Mort"))
			{
				this.fourmisMortes.add(f);
			}
		}
	}
	
	//Pour consommer la quantité demandée par les fourmis
	public int extraireNourriture(int quantite)
	{
		int reste = 0 ;
		int valeurDemande = quantite;
		int valeurAtteinte = 0;
		
		for(Proie p : nourriture)
		{
			valeurAtteinte += p.diminuerPoids(valeurDemande);
			reste = quantite - valeurAtteinte;
			if(reste > 0 )
			{	
				valeurDemande = reste;
			}
			else
			{
				return valeurAtteinte;
			}
		}
		this.nettoyageProies();
		return valeurAtteinte;
	}
	
	
	//Pour nourrir une fourmi : return true si on asseez de nourriture
	//pour la fourmi et donc elle va survivre
	//on fait return false si l'inverse
	public boolean nourrireFourmi(Fourmi fourmi)
	{
		int quantiteVitale = fourmi.getQuantiteNourriture();
		if(this.extraireNourriture(quantiteVitale) < quantiteVitale)
		{
			fourmi.mange(0);
			return false;
		}
		else
		{
			this.extraireNourriture(quantiteVitale);
			fourmi.mange(quantiteVitale);
			return true;
		}
		
		
	}
	
	// retire une proie de la liste de nourriture
	public void removeProieListeNourriture(Proie proie) {
		this.nourriture.remove(proie);
	}
	
	
	// Check tout le temps et ajoute les proies ayant un poids supérieur à 0
	// sinon les proies sont déplacé dans les proies consommé
	public void ajoutAutoProieNourritureAndConsommee(ContexteDeSimulation contexte) {
		Simulation sim = contexte.getSimulation();
		Terrain ter = sim.getTerrain();
		
		for (Proie p : ter.getListeProie()) {
			if (p.isFood() == true && p.getPoids() > 0 && p.getVivante() == false && !nourriture.contains(p)) {
				this.addProieListeNourriture(p);
			}else if (p.isFood() && p.getVivante() == false && p.getPoids() <= 0){
				this.removeProieListeNourriture(p);
				this.proiesConsommees.add(p);
			}
		}
	}
	
	public void compteNourritureTotaleInstantannee() {
		this.nourritureTotale = 0;
		for (Proie p : this.nourriture) {
			this.nourritureTotale += p.getPoids();
		}
	}
		
}
