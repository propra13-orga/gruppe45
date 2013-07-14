package local;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;

public class Pics{		public static Image	abyss, attention, back, bg_1_1, bg_2_1, blob, book, boss_l, boss_r, bug, 
							bunny_l, bunny_l_motion, bunny_r, bunny_r_motion, bush, carrot, cave, 
							fireball, fireball_d, fireball_l, fireball_r, fireball_u, flower, fox_l, fox_r, goal, 
							healthbar_green, healthbar_yellow, healthbar_red, hedgehog_l, hedgehog_r, hero2, hp, hole, 
							icon1, icon2, life, lives_img, matrix, mole, mp, 
							peace, poisonous_tree, rock, sign, snail, shop_active, shop_inactive, tree;
	
	public static void loadPics(){
	
	try
	{
		icon1 = ImageIO.read(new File (local.Fs.img_pfad+"icon_bunny.png"));
		icon2 = ImageIO.read(new File (local.Fs.img_pfad+"bunny_l.png"));
		bunny_l = ImageIO.read(new File (local.Fs.img_pfad+"bunny_l.png"));
		bunny_r = ImageIO.read(new File (local.Fs.img_pfad+"bunny_r.png"));
		bunny_l_motion = ImageIO.read(new File(local.Fs.img_pfad+"bunny_l_motion.png"));
		bunny_r_motion = ImageIO.read(new File(local.Fs.img_pfad+"bunny_r_motion.png"));
		fireball = ImageIO.read(new File (local.Fs.img_pfad+"icon_fireball.png"));
		fireball_u = ImageIO.read(new File (local.Fs.img_pfad+"fireball_u.png"));
		fireball_r = ImageIO.read(new File (local.Fs.img_pfad+"fireball_r.png"));
		fireball_d = ImageIO.read(new File (local.Fs.img_pfad+"fireball_d.png"));
		fireball_l = ImageIO.read(new File (local.Fs.img_pfad+"fireball_l.png"));
		life = ImageIO.read(new File (local.Fs.img_pfad+"heart.png"));
		blob = ImageIO.read(new File (local.Fs.img_pfad+"blob.png"));
		peace = ImageIO.read(new File (local.Fs.img_pfad+"peace.jpg"));
		attention = ImageIO.read(new File (local.Fs.img_pfad+"attention.png"));
		flower = ImageIO.read(new File (local.Fs.img_pfad+"flower_purple.png"));
		book = ImageIO.read(new File (local.Fs.img_pfad+"book.png"));
		boss_l = ImageIO.read(new File (local.Fs.img_pfad+"boss_l.png"));
		boss_r = ImageIO.read(new File (local.Fs.img_pfad+"boss_r.png"));
		hero2 = ImageIO.read(new File (local.Fs.img_pfad+"pixel_gray.png"));
		fox_l = ImageIO.read(new File (local.Fs.img_pfad+"fox_l.png"));
		fox_r = ImageIO.read(new File (local.Fs.img_pfad+"fox_r.png"));
		lives_img = ImageIO.read(new File (local.Fs.img_pfad+"lives_bunny.png"));
		healthbar_green = ImageIO.read(new File (local.Fs.img_pfad+"healthbar_green.png"));
		healthbar_yellow = ImageIO.read(new File (local.Fs.img_pfad+"healthbar_yellow.png"));
		healthbar_red = ImageIO.read(new File (local.Fs.img_pfad+"healthbar_red.png"));
		poisonous_tree = ImageIO.read(new File (local.Fs.img_pfad+"poisonous_tree.png"));
		sign = ImageIO.read(new File (local.Fs.img_pfad+"sign.png"));
		snail = ImageIO.read(new File (local.Fs.img_pfad+"snail.png"));
		shop_active = ImageIO.read(new File (local.Fs.img_pfad+"shop_active.png"));
		shop_inactive = ImageIO.read(new File (local.Fs.img_pfad+"shop_inactive.png"));
		mole = ImageIO.read(new File (local.Fs.img_pfad+"shop_active.png"));
		carrot = ImageIO.read(new File (local.Fs.img_pfad+"carrot_tree.png"));
		cave = ImageIO.read(new File (local.Fs.img_pfad+"cave.jpg"));		
		tree = ImageIO.read(new File (local.Fs.img_pfad+"wall_1.png"));
		bush = ImageIO.read(new File (local.Fs.img_pfad+"wall_2.png"));
		rock = ImageIO.read(new File (local.Fs.img_pfad+"wall_3.png"));
		back = ImageIO.read(new File (local.Fs.img_pfad+"bg_1_1.png"));
		hedgehog_l = ImageIO.read(new File (local.Fs.img_pfad+"hedgehog_l.png"));
		hedgehog_r = ImageIO.read(new File (local.Fs.img_pfad+"hedgehog_r.png"));
		bug = ImageIO.read(new File (local.Fs.img_pfad+"bug.png"));
		goal = ImageIO.read(new File (local.Fs.img_pfad+"goal.png"));
		hole = ImageIO.read(new File (local.Fs.img_pfad+"goal_2_3.png"));
		hp = ImageIO.read(new File (local.Fs.img_pfad+"medipack.png"));
		mp = ImageIO.read(new File (local.Fs.img_pfad+"candy_blue.png"));
		bg_1_1 = ImageIO.read(new File (local.Fs.img_pfad+"bg_1_1.png"));
		bg_2_1  = ImageIO.read(new File (local.Fs.img_pfad+"bg_2_1.png"));
		abyss  = ImageIO.read(new File (local.Fs.img_pfad+"rabbit_abyss.jpg"));
		matrix = ImageIO.read(new File (local.Fs.img_pfad+"matrix.jpg"));
	}
		
	catch(IOException e)
		{	//TODO Auto-generated block		}
		System.out.println("Bild aus pic.loadImage() kann nicht eingelesen werden");
		}
	}
	
}

