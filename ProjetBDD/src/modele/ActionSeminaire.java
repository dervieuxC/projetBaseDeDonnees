package modele;

import java.sql.Connection;

import requete.Requetes;

/**
 * 
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 *
 */
public abstract class ActionSeminaire {

	public abstract void action(Connection conn);
	
	protected Requetes req;
	
}
