package multiplayer;

import java.util.Random;

import main.Main;

public class MultCol implements Runnable
{
	private int count = 150, count2 =200;
	private int countGo = 100, countGo2 = 100;
	
	
	public MultCol()
	{
		System.out.print("MultCol created");
		
	}

	public void run() 
	{
		System.out.println("Coll läuft");
		Damage damage = new Damage();
				
		while(true)
		{	
/*--------------------------------Player-1-----------------------------------------------------------*/			
			for (int i = 0;i<5;i++)
			{
				//hilfsvariablen zum Vergleichen
				int weaponsPosX1;   			
				int weaponsPosY1;
				int characterPosB_X;
				int characterPosB_Y;
				
				weaponsPosX1=MultMasterFrame.weapons.get(i).posX;
				weaponsPosY1=MultMasterFrame.weapons.get(i).posY;
				
				characterPosB_X=MultMasterFrame.posB_X;
				characterPosB_Y=MultMasterFrame.posB_Y;
				
				//poison
				if ((weaponsPosX1 <= MultMasterFrame.poisonX+25) &&
						(weaponsPosX1 >= MultMasterFrame.poisonX-25) &&
							(weaponsPosY1 <= MultMasterFrame.poisonY+25) &&
								(weaponsPosY1 >= MultMasterFrame.poisonY-25))
				{
					damage.suffer(50,1,"poison"); //player 1 suffer - 10 hp
				}
				
				//carrot
				if ((weaponsPosX1 <= MultMasterFrame.carrotX+25) &&
						(weaponsPosX1 >= MultMasterFrame.carrotX-25) &&
							(weaponsPosY1 <= MultMasterFrame.carrotY+25) &&
								(weaponsPosY1 >= MultMasterFrame.carrotY-25))
				{
					damage.heal(10,1,"heal"); //player 1 suffer - 10 hp
				}
				
				
				// Kollisionsabfrage  auf >=x und y(-+9)
				if (weaponsPosX1>=characterPosB_X    &&
					weaponsPosY1>=characterPosB_Y-45  &&
					weaponsPosY1<=characterPosB_Y+45)
					{	//hedgehog hit
						MultMasterFrame.weapons.get(i).used=false;
						damage.suffer(10, 2, "hit");
						
						//reset Fireball position
						weaponsPosX1=MultMasterFrame.weapons.get(i).posX=-100;
						weaponsPosY1=MultMasterFrame.weapons.get(i).posY=-100;
					}
					
			}
			
/*--------------------------------Player-2-----------------------------------------------------------*/	
			
			for (int i = 5;i<10;i++)
			{
				//hilfsvariablen zum Vergleichen
				int weaponsPosX;   			
				int weaponsPosY;
				int characterPosA_X;
				int characterPosA_Y;
				
				weaponsPosX=MultMasterFrame.weapons.get(i).posX;
				weaponsPosY=MultMasterFrame.weapons.get(i).posY;
				
				characterPosA_X=MultMasterFrame.posA_X;
				characterPosA_Y=MultMasterFrame.posA_Y;
				
				//poison
				if ((weaponsPosX <= MultMasterFrame.poisonX+25) &&
						(weaponsPosX >= MultMasterFrame.poisonX-25) &&
							(weaponsPosY <= MultMasterFrame.poisonY+25) &&
								(weaponsPosY >= MultMasterFrame.poisonY-25))
				{
					damage.suffer(50, 2, "poison");
					}
				
				//carrot
				if ((weaponsPosX <= MultMasterFrame.carrotX+25) &&
						(weaponsPosX >= MultMasterFrame.carrotX-25) &&
							(weaponsPosY <= MultMasterFrame.carrotY+25) &&
								(weaponsPosY >= MultMasterFrame.carrotY-25))
				{
					damage.heal(10, 2, "poison");
					}
				
				
				//character => fireball
				if (weaponsPosX<=characterPosA_X    &&
					weaponsPosY>=characterPosA_Y-45  &&
					weaponsPosY<=characterPosA_Y+45)
					{	//bunny hit
					
						MultMasterFrame.weapons.get(i).used=false;
						//reset Fireball position
						weaponsPosX=MultMasterFrame.weapons.get(i).posX=-100;
						weaponsPosY=MultMasterFrame.weapons.get(i).posY=-100;
						damage.suffer(10, 1, "hit");
					}
				}
			
			if (count <=0)
			{
				count = 150;
				if (!MultMasterFrame.poisonSet)
				{
					Random rand = new Random();
					MultMasterFrame.poisonX = rand.nextInt(624)+200;
					MultMasterFrame.poisonY = rand.nextInt(668)+50;
					MultMasterFrame.poisonSet = true;
					//System.out.println("Poison created");
				}
			}
			
			if (MultMasterFrame.poisonSet)
			{
				countGo--;
				if (countGo==0)
				{
					MultMasterFrame.poisonSet = false;
					countGo = 100;
				}
			}
			
			count--;
	/*1 end*/		
			if (count2<=0)
			{	count2 = 200;
			if(!MultMasterFrame.carrot)
			{
				Random rand2 = new Random();
				MultMasterFrame.carrotX = rand2.nextInt(624)+200;
				MultMasterFrame.carrotY = rand2.nextInt(668)+50;
				MultMasterFrame.carrot = true;
			//	System.out.println("Carrot created");
			}}
			if (MultMasterFrame.carrot)
			{
				countGo2--;
				if (countGo2==0)
				{
					MultMasterFrame.carrot = false;
					countGo2 = 100;
				}
			}count2--;
			
			
		
			try {
				Thread.sleep(50);
				} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
	}

}
