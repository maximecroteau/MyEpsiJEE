package fr.epsi.myEpsi.jmx;

public class Premier implements PremierMBean {

    private static String nom = "PremierMBean";
    private int valeur = 100;

    public String getNom() {
        return nom;
    }

    public int getValeur() {
        return valeur;
    }

    public synchronized void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void rafraichir() {
        setValeur(getValeur() + 1);
        System.out.println("Rafraichir les donnees");

    }

    public Premier() {

    }
}
