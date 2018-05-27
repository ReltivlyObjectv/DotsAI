/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tech.relativelyobjective.dotsai;

import com.jogamp.opengl.GL2;
import java.awt.Color;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class Objective {
	public static final Color color = Color.BLUE;
	public static final Position pos = new Position(
		(int) (WindowManager.RESOLUTION_WIDTH-100),
		(int) (WindowManager.RESOLUTION_HEIGHT-100)
	);
	public static final int width = 10;
	
	public static void render(GL2 gl) {
		gl.glColor3f((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue());
		gl.glBegin(GL2.GL_QUADS);
		//System.out.println(sprite.displayInfo[w][h].toString());
		gl.glVertex2f(pos.x, pos.y);
		gl.glVertex2f(pos.x + width, pos.y);
		gl.glVertex2f(pos.x + width, pos.y + width);
		gl.glVertex2f(pos.x, pos.y + width);
		gl.glEnd();
	}
	public static boolean isInsideObjective(Dot d) {
		return d.pos.x >= pos.x &&
			d.pos.x < pos.x+width &&
			d.pos.y >= pos.y &&
			d.pos.y < pos.y+width;
		
	}
}
