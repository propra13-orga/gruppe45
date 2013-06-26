package gameobjects;



// todesbaum class
public class Poisonous_Tree extends Figure {
	public Poisonous_Tree (int x, int y) {
		super(2,x,y,local.Pics.poisonous_tree);
//		super(2,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"poisonous_tree.png"));
		this.dmg = 2;
	}
}