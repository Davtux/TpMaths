package graphe;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Graphe {
	static final int MAX_SOMMET = 52;
	private @Getter int nombreSommet;
	private @Getter int nombreArete;
	private @Getter final Sommet sommets[];
	private @Getter final Arete aretes[];

	public Graphe() {
		super();
		this.sommets = new Sommet[MAX_SOMMET];
		this.aretes = new Arete[100];
		this.nombreArete = 0;
		this.nombreSommet = 0;
	}

	public void addSommet(Sommet sommet) {
		this.sommets[this.nombreSommet] = sommet;
		this.nombreSommet += 1;
	}

	public void addArete(Arete arete) {
		this.aretes[this.nombreArete] = arete;
		this.nombreArete += 1;
	}

	public int getIdSommetByNom(String s) {
		for (int i = 0; i < MAX_SOMMET; i++) {
			if (this.sommets[i] != null) {
				if (s == sommets[i].getNom()) {
					return i;
				}
			}
		}
		return -1;
	}

	public Sommet getSommetByNom(String s) {
		for (int i = 0; i < MAX_SOMMET; i++) {
			if (this.sommets[i] != null) {
				if (this.sommets[i].getNom().equals(s)) {
					return this.sommets[i];
				}
			}
		}
		return null;
	}

	public List<Arete> getAreteBySommet(Sommet sommet) {
		List<Arete> aretes_sommet = new ArrayList<Arete>();

		for (int i = 0; i < this.nombreArete; i++) {
			if (this.aretes[i].getInitial() == sommet
					|| this.aretes[i].getFin() == sommet) {
				aretes_sommet.add(this.aretes[i]);
			}
		}

		return aretes_sommet;
	}

	public List<Sommet> getVoisinBySommet(Sommet sommet) {
		List<Sommet> voisins = new ArrayList<Sommet>();

		for (int i = 0; i < this.nombreArete; i++) {
			Arete arete = this.aretes[i];
			if (arete.getInitial() == sommet)
				voisins.add(arete.getFin());
			if (arete.getFin() == sommet)
				voisins.add(arete.getInitial());
		}

		return voisins;
	}

	public String sommetsToString() {
		StringBuilder str = new StringBuilder("Sommets = [");
		for (int i = 0; i < this.nombreSommet; i++) {
			str.append(this.sommets[i].toString() + ", ");
		}
		str.append("FIN]");
		return str.toString();
	}

	public String aretesToString() {
		StringBuilder str = new StringBuilder("Aretes = [");
		for (int i = 0; i < this.nombreArete; i++) {
			str.append(this.aretes[i].toString() + ", ");
		}
		str.append("FIN]");
		return str.toString();
	}

	public void resetMarquage() {
		for (int i = 0; i < this.nombreSommet; i++) {
			this.sommets[i].setMarque(false);
		}
	}
}
