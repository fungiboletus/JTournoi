package polytech.stock;

public abstract class TupleAvecID
{
    /**
     *L'ID de la personne.
     */
    
    protected int id;
    protected static int nbInstances=1;
   
	public TupleAvecID()
	{
		this.id = nbInstances++;
	}

    /**
     *Accesseur en lecture.
     *@return L'ID de la personne.
     */

    public int getId()
    {
	return id;
    }
    
    /**Mutateur
     *Modifie l'ID de la personne par l'ID donne.
     *@param id Nouvel ID de la personne.
     */

    public void setId(int id)
    {
	this.id = id;
    }

}
