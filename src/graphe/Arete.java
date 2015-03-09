package graphe;

import lombok.AllArgsConstructor;
import lombok.Getter;

public @AllArgsConstructor class Arete {
	private @Getter final Sommet initial;
	private @Getter final Sommet fin;
	private @Getter final int valeur;

	@Override
	public String toString() {
		return "Arete [" + initial.getNom() + " - " + fin.getNom() + " : "
				+ valeur + "]";
	}

}
