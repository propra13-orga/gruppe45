package local;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
//please do no changes!!! still in progress
public class Pics 
{
	public static Image icon1, hedgehog, bunny_r, back, tree, fox_r, cave, mole, shop_inactive, poisonous_tree, sign, healthbar_green, healthbar_yellow, healthbar_red, lives_img, fox_l, hero2, icon2, life, weapon, bug, blob, fireball, peace, attention, flower, boss_l;
	
	public static void loadPics(){
	
	try
	{
		icon1 = ImageIO.read(new File(local.Fs.img_pfad+"icon_bunny.png"));
	//	weapon = ImageIO.read(new File(local.Fs.img_pfad+"icon_fireball.png"));
		icon2 = ImageIO.read(new File(local.Fs.img_pfad+"bunny_l.png"));
		bunny_r = ImageIO.read(new File (local.Fs.img_pfad+"bunny_r.png"));
		fireball = ImageIO.read(new File (local.Fs.img_pfad+"icon_fireball.png"));
		life = ImageIO.read(new File (local.Fs.img_pfad+"heart.png"));
		blob = ImageIO.read(new File (local.Fs.img_pfad+"blob.png"));
		peace = ImageIO.read(new File (local.Fs.img_pfad+"peace.jpg"));
		attention = ImageIO.read(new File (local.Fs.img_pfad+"attention.png"));
		flower = ImageIO.read(new File (local.Fs.img_pfad+"flower_purple.png"));
		boss_l = ImageIO.read(new File (local.Fs.img_pfad+"boss_l.png"));
		hero2 = ImageIO.read(new File (local.Fs.img_pfad+"bg_menue.png"));
		fox_l = ImageIO.read(new File (local.Fs.img_pfad+"fox_l.png"));
		fox_r = ImageIO.read(new File (local.Fs.img_pfad+"fox_r.png"));
		lives_img = ImageIO.read(new File (local.Fs.img_pfad+"lives_bunny.png"));
		healthbar_green = ImageIO.read(new File (local.Fs.img_pfad+"healthbar_green.png"));
		healthbar_yellow = ImageIO.read(new File (local.Fs.img_pfad+"healthbar_yellow.png"));
		healthbar_red = ImageIO.read(new File (local.Fs.img_pfad+"healthbar_red.png"));
		poisonous_tree = ImageIO.read(new File (local.Fs.img_pfad+"poisonous_tree.png"));
		sign = ImageIO.read(new File (local.Fs.img_pfad+"sign.png"));
		shop_inactive = ImageIO.read(new File (local.Fs.img_pfad+"shop_inactive.png"));
		mole = ImageIO.read(new File (local.Fs.img_pfad+"shop_active.png"));
		cave = ImageIO.read(new File(local.Fs.img_pfad+"cave.jpg"));		
		tree = ImageIO.read(new File(local.Fs.img_pfad+"wall_1.png"));
		back = ImageIO.read(new File (local.Fs.img_pfad+"bg_1_1.png"));
		hedgehog = ImageIO.read(new File (local.Fs.img_pfad+"hedgehog_l.png"));
		bug = ImageIO.read(new File (local.Fs.img_pfad+"bug.png"));}
		
	catch(IOException e)
		{	//TODO Auto-generated block		}
		System.out.println("Bild aus pic.loadImage() kann nicht eingelesen werden");
		}
	}
	
}

