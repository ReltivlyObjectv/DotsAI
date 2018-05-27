package tech.relativelyobjective.dotsai;

import com.jogamp.opengl.GL2;
import com.sun.prism.paint.Color;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class Dot {
	public Position pos;
	
	public Dot() {
		pos = new Position();
	}
	public Dot(int x, int y) {
		pos = new Position(x, y);
	}
	public void draw(GL2 gl) {
		Color color = getColor();
		gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		gl.glBegin(GL2.GL_QUADS);
		//System.out.println(sprite.displayInfo[w][h].toString());
		gl.glVertex2f(pos.x, pos.y);
		gl.glVertex2f(pos.x + 1, pos.y);
		gl.glVertex2f(pos.x + 1, pos.y + 1);
		gl.glVertex2f(pos.x, pos.y + 1);
		gl.glEnd();
	}
	private Color getColor() {
		//TODO color based on fitness
		return Color.BLACK;
	}
}
