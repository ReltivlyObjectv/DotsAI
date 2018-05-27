package tech.relativelyobjective.dotsai;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class WindowManager {
	private static GLWindow window = null;
	
	public static final float RESOLUTION_WIDTH = 240;
	public static final float RESOLUTION_HEIGHT = 160;
	public static final float TILE_WIDTH = 16;

	public static void init() {
		GLProfile.initSingleton();
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(profile);
		window = GLWindow.create(caps);
		setDefaultResolution();
		window.addKeyListener(new KeyboardListener());
		window.addGLEventListener(new RenderListener());
		window.setTitle("DotsAI");
		window.setVisible(true);
	}
	private static void setDefaultResolution() {
		int w = 0, h = 0;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		do {
			w += RESOLUTION_WIDTH;
			h += RESOLUTION_HEIGHT;
		} while (
			(w+RESOLUTION_WIDTH)<screenSize.width && 
			(h+RESOLUTION_HEIGHT)<screenSize.height
		);
		window.setSize(w, h);
	}
	public static void redraw() {
		window.display();
	}
}
