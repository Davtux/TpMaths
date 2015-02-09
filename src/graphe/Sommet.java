package graphe;

public class Sommet {
	private String nom;
	private int marquage;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getMarquage() {
		return marquage;
	}

	public void setMarquage(int marquage) {
		this.marquage = marquage;
	}

	public Sommet() {
		super();
		this.nom = "x";
		this.marquage = 0;
	}

	public Sommet(String nom) {
		super();
		this.nom = nom;
		this.marquage = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + marquage;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sommet other = (Sommet) obj;
		if (marquage != other.marquage)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
