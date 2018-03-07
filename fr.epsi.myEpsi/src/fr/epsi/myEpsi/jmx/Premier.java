package fr.epsi.myEpsi.jmx;

public class Premier implements PremierMBean {

	private static String nom = "PremierMBean";
	private int valeur = 100;
	
	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getValeur() {
		return valeur;
	}

	@Override
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	@Override
	public void rafraichir() {
		setValeur(getValeur() + 1);
		System.out.println("Rafraichir les donnees");
	}
	
	public Premier() {
	
	}

}
