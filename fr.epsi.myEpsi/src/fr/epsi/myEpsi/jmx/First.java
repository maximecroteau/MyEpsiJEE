package fr.epsi.myEpsi.jmx;

public class First implements FirstMBean {

    private static String nom = "FirstMBean";
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

    public First() {

    }
}
