package graphe;

/**
 * 
 */

/**
 * @author romain
 *
 */
public class Arete {
	private Sommet initial;
	private Sommet fin;
	private int valeur;

	public Arete(Sommet initial, Sommet fin, int valeur) {
		super();
		this.initial = initial;
		this.fin = fin;
		this.valeur = valeur;
	}

	public Sommet getInitial() {
		return initial;
	}

	public void setInitial(Sommet initial) {
		this.initial = initial;
	}

	public Sommet getFin() {
		return fin;
	}

	public void setFin(Sommet fin) {
		this.fin = fin;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

}
