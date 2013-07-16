package multiplayer;

public class Damage 
{
	public static boolean done = false;
	
	public Damage()
	{
		
	}
	
	public void suffer(int value, int player, String typ)
	{
		
			MultMasterFrame.hit = true;
			
			if (typ.equals("poison"))
			{
				MultMasterFrame.poisonSet = false;
			}
			
			
			
			
			if (player ==1)
			{
				MultMasterFrame.hp1 -= value;
				System.out.println("Boom1");
				if (MultMasterFrame.hp1<0)
				{
					MultMasterFrame.lives1 -= 1;
					MultMasterFrame.hp1 =100;
					if (MultMasterFrame.lives1 <0)
					{
						MultMasterFrame.lost1 = true;
					}
				}
				
			}
			
			if (player ==2)
			{
				MultMasterFrame.hp2 -= value;
				System.out.println("Boom2");
				if (MultMasterFrame.hp2<0)
				{
					MultMasterFrame.lives2 -= 1;
					MultMasterFrame.hp2 =100;
					if (MultMasterFrame.lives2 <0)
					{
						MultMasterFrame.lost2 = true;
					}
				}
			}
			
			
		
		
		
	}
	public void heal(int value, int player, String typ)
	{
		MultMasterFrame.carrot = false;
		if (player ==1)
		{
			MultMasterFrame.hp1 +=value;
		}
		if (player ==2)
		{
			MultMasterFrame.hp2 +=value;
		}
		
	}

}
