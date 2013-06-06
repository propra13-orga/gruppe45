package spielobjekte;

import java.awt.Image;
import java.awt.Toolkit;

import lokal.Fs;

// hero class
public class Hero extends Objekt {
	public int start_leben_punkte =100;
	public Image leben_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"leben_kopf.png");
	public Hero (int x, int y) {
		super(4,x,y);
		this.start_x=x;
		this.start_y=y;
		this.leben_punkte = this.start_leben_punkte;
		this.anz_leben=2;
		this.geschwindigkeit = 5;
		this.schaden_punkte = 100;
		this.zerstoerbar=true;
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"held.png");
	}
	public Image get_hero_image() {
		return this.image;
	}
	public Image get_lebensbalken_image() {
		if(this.leben_punkte>66) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lebensbalken_gruen.png");
		if(this.leben_punkte>33) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lebensbalken_gelb.png");
		else return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lebensbalken_rot.png");
	}

}