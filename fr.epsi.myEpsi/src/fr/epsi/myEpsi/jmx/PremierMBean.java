package fr.epsi.myEpsi.jmx;

public interface PremierMBean {

    public String getNom();
    public int getValeur();
    public void setValeur(int valeur);
    public void rafraichir();

}
