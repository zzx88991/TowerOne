package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class ShadowPanelView extends JPanel{
	private Point p;
	private boolean rangeshowable;

	public ShadowPanelView (){
		p = new Point();
		rangeshowable=false;

	}
	public  void setPoint(Point p){
		this.p = p;
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.BLUE);
		g.drawRect(p.x, p.y, 50, 50);
	}

}
