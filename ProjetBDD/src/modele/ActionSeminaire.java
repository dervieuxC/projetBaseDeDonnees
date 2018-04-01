package modele;

import java.sql.Connection;

/**
 * 
 * @author Corentin Dervieux
 * @author Thibaut Masselin
 *
 */
public abstract class ActionSeminaire {

	public abstract void action(Connection conn);
	
}
