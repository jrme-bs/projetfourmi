package etats;



import trace.BilanEtat;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public abstract class Etat {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract void initialise(VueIndividu vue );
	public abstract void updateBilanEtat(BilanEtat tan);
}
