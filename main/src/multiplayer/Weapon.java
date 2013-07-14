package multiplayer;

public class Weapon 
{
	protected int posX=0;
	protected int posY=0;
	protected int direction =0;		//1 Player 1 left to right; 2 Player 2 right to left
	
	public boolean sent=false;

	
	public Weapon(int posX,int posY, int direction) 
	{
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		
	}
}
