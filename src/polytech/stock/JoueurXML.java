package polytech.stock;

import java.util.List;

import polytech.personnes.Joueur;

public class JoueurXML extends GestionXML
{

	protected List<Joueur> joueurs;

	@Override
	public void chargerStock()
	{
		joueurs = GestionXML.chargerFichierXml(Joueur.class);
	}

	@Override
	public void sauvegarderStock()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterReference(Object reference)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerReference(Object reference)
	{
		// TODO Auto-generated method stub

	}

}
