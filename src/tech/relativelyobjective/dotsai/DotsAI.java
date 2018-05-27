package tech.relativelyobjective.dotsai;

import java.util.LinkedList;

public class DotsAI {
	public static final LinkedList<Dot> dots = new LinkedList<>();
	public static final LinkedList<Dot> genePool = new LinkedList<>();
	public static int step = 0;
	
    public static void main(String[] args) {
		for(int i = 0; i < 100; i++) {
			dots.add(new Dot(100,100));
		}
		WindowManager.init();
		while(true) {
			for (Dot d : dots) {
				if (!d.isCompleted()) {
					d.move(step);
				}
			}
			step++;
			WindowManager.redraw();
		}
	}
}

