package tech.relativelyobjective.dotsai;

import com.jogamp.opengl.GL2;
import com.sun.prism.paint.Color;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class Dot {
	private boolean completed = false;
	private int completedStepsCount = 0;
	private Color color = Color.GREEN;
	public Position pos;
	public DNA dna;
	
	public Dot() {
		pos = new Position();
		dna = new DNA();
	}
	public Dot(Position p) {
		pos = new Position(p);
		dna = new DNA();
	}
	public void draw(GL2 gl) {
		gl.glColor3f((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue());
		gl.glBegin(GL2.GL_QUADS);
		//System.out.println(sprite.displayInfo[w][h].toString());
		gl.glVertex2f(pos.x, pos.y);
		gl.glVertex2f(pos.x + 1, pos.y);
		gl.glVertex2f(pos.x + 1, pos.y + 1);
		gl.glVertex2f(pos.x, pos.y + 1);
		gl.glEnd();
	}
	public void move(int step) {
		Movement mov = dna.movements[step];
		pos.x += mov.xVec;
		pos.y += mov.yVec;
		color = new Color(
			((float) step) / ((float) DNA.movementLength), 
			((float) DNA.movementLength-step) / ((float) DNA.movementLength), 
			0,
			color.getAlpha()
		);
	}
	public static Dot breed(Dot parentA, Dot parentB) {
		Dot childDot = new Dot(DotsAI.startingPosition);
		for (int i = 0; i < DNA.movementLength; i++) {
			if (Math.random() < .5) {
				childDot.dna.movements[i] = parentA.dna.movements[i];
			} else {
				childDot.dna.movements[i] = parentB.dna.movements[i];
			}
		}
		return childDot;
	}
	public Color getColor() {
		return color;
	}
	public void setComplete(int steps) {
		completed = true;
		completedStepsCount = steps;
	}
	public boolean isCompleted() {
		return completed;
	}
	public int getCompletedStepsCount() {
		return completedStepsCount;
	}
}
