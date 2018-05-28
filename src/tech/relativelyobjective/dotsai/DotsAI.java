package tech.relativelyobjective.dotsai;

import java.util.LinkedList;

public class DotsAI {
	public static final LinkedList<Dot> dots = new LinkedList<>();
	public static final LinkedList<Dot> genePool = new LinkedList<>();
	public static final Position startingPosition = new Position(100,100);
	public static final int populationSize = 500;
	public static final int genePoolSize = 50;
	public static int bestStep = DNA.movementLength;
	public static int step = 0;
	public static int generation = 1;
	
    public static void main(String[] args) {
		for(int i = 0; i < populationSize; i++) {
			dots.add(new Dot(startingPosition));
		}
		WindowManager.init();
		while(true) {
			for (Dot d : dots) {
				if (!d.isCompleted()) {
					d.move(step);
					if (Objective.isInsideObjective(d)) {
						if (step < bestStep) {
							bestStep = step;
						}
						d.setComplete(step);
						genePool.add(d);
						if (genePool.size() >= genePoolSize) {
							breedAndRestart();
							break;
						}
					}
				}
			}
			step++;
			if (step >= DNA.movementLength) {
				//Out of moves -- Force breed and restart
				if (genePool.isEmpty()) {
					//No parents to have children, so use entire population
					for (Dot d : dots) {
						genePool.add(d);
					}
				}
				breedAndRestart();
			}
			WindowManager.redraw();
		}
	}
	public static void breedAndRestart() {
		dots.clear();
		for (int i = 0; i < populationSize; i++) {
			int parentAIndex = (int) (Math.random() * genePool.size());
			Dot parentA = genePool.get(parentAIndex);
			int parentBIndex = (int) (Math.random() * genePool.size());
			Dot parentB = genePool.get(parentBIndex);
			dots.add(Dot.breed(parentA, parentB));
		}
		genePool.clear();
		step = 0;
		generation++;
	}
}

