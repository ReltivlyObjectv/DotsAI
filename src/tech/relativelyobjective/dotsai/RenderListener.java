package tech.relativelyobjective.dotsai;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class RenderListener implements GLEventListener {
	private static float extraWidth=0, extraHeight=0, scale=1;
	@Override
	public void init(GLAutoDrawable glad) {
		GL2 gl = glad.getGL().getGL2();
		gl.glClearColor(0, 0, 0, 1);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void dispose(GLAutoDrawable glad) {
		System.exit(0);
	}

	@Override
	public void display(GLAutoDrawable glad) {
		GL2 gl = glad.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		//Draw white background
		gl.glColor3f(1, 1, 1);
		gl.glBegin(GL2.GL_QUADS);
		//System.out.println(sprite.displayInfo[w][h].toString());
		gl.glVertex2f(0, 0);
		gl.glVertex2f(WindowManager.RESOLUTION_WIDTH, 0);
		gl.glVertex2f(WindowManager.RESOLUTION_WIDTH, WindowManager.RESOLUTION_HEIGHT);
		gl.glVertex2f(0, WindowManager.RESOLUTION_HEIGHT);
		gl.glEnd();
		//TODO Draw Dots
		Dot dot = new Dot();
		dot.draw(gl);
	}

	@Override
	public void reshape(GLAutoDrawable glad, int x, int y, int w, int h) {
		float renderWidth = 0;
		float renderHeight = 0;
		//Assume resolution width is greater than height
		float widthHeightRatio = WindowManager.RESOLUTION_WIDTH / WindowManager.RESOLUTION_HEIGHT;
		do {
			renderWidth += widthHeightRatio;
			renderHeight += 1;
		} while (
			renderWidth+widthHeightRatio < w &&
			renderHeight+1 < h
		);
		extraWidth = w - renderWidth;
		extraHeight = h - renderHeight;
		//Convert extra space to standard pixels
		scale = renderWidth / WindowManager.RESOLUTION_WIDTH;
		extraWidth /= scale;
		extraHeight /= scale;
		GL2 gl = glad.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(
			-(extraWidth / 2),
			WindowManager.RESOLUTION_WIDTH + (extraWidth / 2),
			-(extraHeight / 2),
			WindowManager.RESOLUTION_HEIGHT + (extraHeight / 2), 
			-1,
			1
		);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	public static float getExtraWidth() {
		return extraWidth;
	}
	public static float getExtraHeight() {
		return extraHeight;
	}
	public static float getScale() {
		return scale;
	}
}
