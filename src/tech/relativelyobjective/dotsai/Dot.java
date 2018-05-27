package tech.relativelyobjective.dotsai;

import com.jogamp.opengl.GL2;
import com.sun.prism.paint.Color;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class Dot {
	public int x,y;
	
	public Dot() {
		x = 0;
		y = 0;
	}
	
	public void draw(GL2 gl) {
		Color color = getColor();
		gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		gl.glBegin(GL2.GL_QUADS);
		//System.out.println(sprite.displayInfo[w][h].toString());
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + 1, y);
		gl.glVertex2f(x + 1, y + 1);
		gl.glVertex2f(x, y + 1);
		gl.glEnd();
	}
	private Color getColor() {
		//TODO color based on fitness
		return Color.BLACK;
	}
}
