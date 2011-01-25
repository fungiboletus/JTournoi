package polytech.stock;

import java.util.List;

import polytech.personnes.Joueur;

public class JoueurXML extends GestionXML
{

	protected List<Joueur> joueurs;

	@Override
	public void chargerStock()
	{
		joueurs = GestionXML.chargerFichierXml("joueurs", this);
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

	@Override
	public Object construireDepuisStock(Object element)
	{
		// TODO
		return null;
	}

	@Override
	public Object construirePourStock(Object element)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
