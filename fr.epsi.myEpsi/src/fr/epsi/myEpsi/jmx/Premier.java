package fr.epsi.myEpsi.jmx;

import fr.epsi.myEpsi.beans.logLevel;

public class Premier implements PremierMBean {
	
	private String level;
	
	@Override
	public String getLevel() {
		return level;
	}

	@Override
	public void DEBUG() {
		logLevel.setLevel(logLevel.DEBUG);
		level = "DEBUG";
		System.out.println("Niveau de log changés à DEBUG.");
	}
	
	@Override
	public void INFO() {
		logLevel.setLevel(logLevel.INFO);
		level = "INFO";
		System.out.println("Niveau de log changés à INFO");
	}
	
	@Override
	public void ERROR() {
		logLevel.setLevel(logLevel.ERROR);
		level = "ERROR";
		System.out.println("Niveau de log changés à ERROR");
	}
	
	public Premier(String lvl) {
		level = lvl;
	}

}
