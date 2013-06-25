package local;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
//please do no changes!!! still 
public class Pics 
{
	public static Image icon1, icon2, life, weapon, bug, blob, fireball, peace, attention, flower;
	
	public static void loadPics(){
	
	try
	{
		icon1 = ImageIO.read(new File(local.Fs.img_pfad+"icon_bunny.png"));
	//	weapon = ImageIO.read(new File(local.Fs.img_pfad+"icon_fireball.png"));
		icon2 = ImageIO.read(new File(local.Fs.img_pfad+"bunny_l.png"));
		fireball = ImageIO.read(new File (local.Fs.img_pfad+"icon_fireball.png"));
		life = ImageIO.read(new File (local.Fs.img_pfad+"heart.png"));
		blob = ImageIO.read(new File (local.Fs.img_pfad+"blob.png"));
		peace = ImageIO.read(new File (local.Fs.img_pfad+"peace.jpg"));
		attention = ImageIO.read(new File (local.Fs.img_pfad+"attention.png"));
		flower = ImageIO.read(new File (local.Fs.img_pfad+"flower_purple.png"));
		bug = ImageIO.read(new File (local.Fs.img_pfad+"bug.png"));}
		
	catch(IOException e)
		{	//TODO Auto-generated block		}
		System.out.println("Bild aus pic.loadImage() kann nicht eingelesen werden");
		}
	}
	
}

