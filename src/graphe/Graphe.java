package graphe;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Graphe {
	static final int MAX_SOMMET = 52;
	private int nombreSommet;
	private int nombreArete;
	private @Getter final Sommet sommets[];
	private @Getter final Arete aretes[];
	private @Getter final boolean matriceAdjacence[][];

	public Graphe() {
		super();
		this.sommets = new Sommet[MAX_SOMMET];
		this.aretes = new Arete[100];
		this.nombreArete = 0;
		this.nombreSommet = 0;
		this.matriceAdjacence = new boolean[MAX_SOMMET][MAX_SOMMET];
	}

	public void addSommet(Sommet sommet) {
		this.sommets[this.nombreSommet] = sommet;
		this.nombreSommet += 1;
	}

	public void addArete(Arete arete) {
		this.aretes[this.nombreArete] = arete;
		this.nombreArete += 1;
		this.majMatrice(arete.getInitial(), arete.getFin());
		this.majMatrice(arete.getFin(), arete.getInitial());
	}

	public void majMatrice(Sommet initial, Sommet fin) {
		int idInitial = getIdSommetByNom(initial.getNom());
		int idFin = getIdSommetByNom(fin.getNom());

		this.matriceAdjacence[idInitial][idFin] = true;
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

	public Arete[] getAreteBySommet(Sommet s) {
		int taille = 0;
		for (int i = 0; i < this.aretes.length; i++) {
			if (this.aretes[i].getInitial().equals(s)) {
				taille++;
			}
		}
		Arete arete[] = new Arete[taille];
		taille = 0;

		for (int i = 0; i < this.aretes.length; i++) {
			if (this.aretes[i].getInitial().equals(s)) {
				arete[taille] = this.aretes[i];
				taille++;
			}
		}
		return arete;
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

	public List<Sommet> parcoursEnProfondeur(Sommet sommet) {
		sommet.setMarque(true);
		List<Sommet> listeProfondeur = new ArrayList<Sommet>();
		listeProfondeur.add(sommet);

		for (Sommet s : this.getVoisinBySommet(sommet)) {
			if (!s.isMarque()) {
				listeProfondeur.addAll(this.parcoursEnProfondeur(s));
			}
		}
		return listeProfondeur;
	}

	public List<Sommet> parcoursEnLargeur(Sommet sommet) {
		List<Sommet> listeLargeur = new ArrayList<Sommet>();

		if (!sommet.isMarque()) {
			sommet.setMarque(true);
			listeLargeur.add(sommet);
		}

		for (int i = 0; i < listeLargeur.size(); i++) {
			for (Sommet s : this.getVoisinBySommet(listeLargeur.get(i))) {
				if (!s.isMarque()) {
					s.setMarque(true);
					listeLargeur.add(s);
				}
			}
		}

		return listeLargeur;
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
		StringBuilder str = new StringBuilder("Sommets = [");
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
