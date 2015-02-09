package graphe;

import lombok.Getter;
import lombok.Setter;

public class Sommet {
	private @Getter @Setter String nom;
	private @Getter @Setter boolean marque;

	public Sommet() {
		this.nom = "x";
		this.marque = false;
	}

	public Sommet(String nom) {
		this.nom = nom;
		this.marque = false;
	}

	@Override
	public String toString() {
		return "Sommet [" + nom + ", " + marque + "]";
	}

}
