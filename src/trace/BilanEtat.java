package trace;

public class BilanEtat {
	private int nbOeuf = 0;
	private int nbLarve = 0;
	private int nbNymphe = 0;
	private int nbAdulte = 0;
	private int nbMort = 0;
	
	public BilanEtat() {
	
	}
	
	public void clear() {
		nbOeuf = 0;
		nbLarve = 0;
		nbNymphe = 0;
		nbAdulte = 0;
		nbMort = 0;
	}
	
	public void afficheNbEtatFourmiliere() {
		
		/*System.out.println("============================================");
		System.out.println("Nombre d'oeuf : " + nbOeuf);
		System.out.println("Nombre de larve : " + nbLarve);
		System.out.println("Nombre de nymphe : " + nbNymphe);
		System.out.println("Nombre de fourmis adulte : " + nbAdulte);
		System.out.println("Nombre de fourmis morte : " + nbMort);
		System.out.println("============================================");*/
		
		clear();
	}

	public int getNbOeuf() {
		return nbOeuf;
	}

	public void setNbOeuf(int nbOeuf) {
		this.nbOeuf = nbOeuf;
	}

	public int getNbLarve() {
		return nbLarve;
	}

	public void setNbLarve(int nbLarve) {
		this.nbLarve = nbLarve;
	}

	public int getNbNymphe() {
		return nbNymphe;
	}

	public void setNbNymphe(int nbNymphe) {
		this.nbNymphe = nbNymphe;
	}

	public int getNbAdulte() {
		return nbAdulte;
	}

	public void setNbAdulte(int nbAdulte) {
		this.nbAdulte = nbAdulte;
	}

	public int getNbMort() {
		return nbMort;
	}

	public void setNbMort(int nbMort) {
		this.nbMort = nbMort;
	}
	
}
