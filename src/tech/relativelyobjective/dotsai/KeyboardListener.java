package tech.relativelyobjective.dotsai;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import java.util.HashMap;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class KeyboardListener implements KeyListener {
	public static final HashMap<Short, Boolean> PRESSED_KEYS = new HashMap<Short, Boolean>();
	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.isAutoRepeat()) {
			return;
		}
		PRESSED_KEYS.put(ke.getKeyCode(), true);
		System.out.println(ke.getKeyChar()+" pressed!");
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		if (ke.isAutoRepeat()) {
			return;
		}
		PRESSED_KEYS.put(ke.getKeyCode(), false);
		System.out.println(ke.getKeyChar()+" released!");
	}
}
