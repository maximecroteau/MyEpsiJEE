package fr.epsi.myEpsi.beans;

public class Status {
	public final static int TEMPORAIRE = 0;
	public final static int PUBLIE = 1;
	public final static int VENDU = 2;
	public final static int ANNULE = 3;

	public static String getStatusString(int status) {
		switch (status) {
		case 0:
			return "TEMPORAIRE";
		case 1:
			return "PUBLIE";
		case 2:
			return "VENDU";
		case 3:
			return "ANNULE";
		default:
			return null;
		}
	}

	public static int getStatusInt(String status) {
		switch (status) {
		case "TEMPORAIRE":
			return 0;
		case "PUBLIE":
			return 1;
		case "VENDU":
			return 2;
		case "ANNULE":
			return 3;
		default:
			return -1;
		}
	}
}
