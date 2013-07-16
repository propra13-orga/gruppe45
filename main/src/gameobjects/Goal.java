package gameobjects;

import java.awt.Image;

/**
 * A rooms exit
 * @author Andreas Roth
 * @author Martin Knonsalla
 */
public class Goal extends Figure {

	@Override public Image getPic(int direction){
		if(main.Main.level == 2 && main.Main.room == 3)
		{
			this.height = local.Pics.hole.getHeight(null);
			this.width = local.Pics.goal.getWidth(null);
			return local.Pics.hole;
		}
		else return local.Pics.goal;
	}

	/**
	 * Parameterless constructor
	 */
	public Goal () {
		super();
		this.height = local.Pics.goal.getHeight(null);
		this.width = local.Pics.goal.getWidth(null);
	}
}