package graphe;

public class Graphe {
	private int nombreSommet;
	private int nombreArete;
	private Sommet sommets[];
	private Arete aretes[];
	private boolean matriceAdjacence[][];

	public Graphe() {
		super();
		this.sommets = new Sommet[52];
		this.aretes = new Arete[100];
		this.nombreArete = 0;
		this.nombreSommet = 0;
		this.matriceAdjacence = new boolean[52][52];
	}

	public boolean[][] getMatriceAdjacence() {
		return matriceAdjacence;
	}

	public void setMatriceAdjacence(boolean[][] matriceAdjacence) {
		this.matriceAdjacence = matriceAdjacence;
	}

	public int getNombreSommet() {
		return nombreSommet;
	}

	public void setNombreSommet(int nombreSommet) {
		this.nombreSommet = nombreSommet;
	}

	public int getNombreArete() {
		return nombreArete;
	}

	public void setNombreArete(int nombreArete) {
		this.nombreArete = nombreArete;
	}

	public Sommet[] getSommets() {
		return sommets;
	}

	public void setSommets(Sommet[] sommets) {
		this.sommets = sommets;
	}

	public Arete[] getAretes() {
		return aretes;
	}

	public void setAretes(Arete[] aretes) {
		this.aretes = aretes;
	}

	public void addSommet(Sommet sommet) {
		this.sommets[this.nombreSommet] = sommet;
		this.nombreSommet += 1;
	}

	public void addArete(Arete arete) {
		this.aretes[this.nombreArete] = arete;
		this.nombreArete += 1;
		this.majMatrice(arete.getInitial(), arete.getFin());
	}

	public void majMatrice(Sommet initial, Sommet fin) {
		int idInitial = getIdSommetByNom(initial.getNom());
		int idFin = getIdSommetByNom(fin.getNom());

		this.matriceAdjacence[idInitial][idFin] = true;
	}

	public int getIdSommetByNom(String s) {
		for (int i = 0; i < 52; i++) {
			if (this.sommets[i] != null) {
				if (s == sommets[i].getNom()) {
					return i;
				}
			}
		}
		return -1;
	}

	public Sommet getSommetByNom(String s) {
		for (int i = 0; i < 52; i++) {
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
	
	public Sommet[] getVoisinSommetByNom(String nom){
		Sommet s = this.getSommetByNom(nom);
		
	}

}
