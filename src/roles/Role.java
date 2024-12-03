package roles;

import trace.BilanRole;
import vue.ContexteDeSimulation;

public abstract class Role {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract void updateBilanRole(BilanRole bilan);
}
