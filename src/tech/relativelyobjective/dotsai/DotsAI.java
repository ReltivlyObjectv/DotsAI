package tech.relativelyobjective.dotsai;

import java.util.LinkedList;

public class DotsAI {
	public static final LinkedList<Dot> dots = new LinkedList<>();
	public static final LinkedList<Dot> genePool = new LinkedList<>();
	public static final Position startingPosition = new Position(100,100);
	public static final int populationSize = 2500;
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
			if (i == 0) {
				//Preserve the most fit dot
				Dot identicalChild = new Dot(DotsAI.startingPosition);
				Dot mostFitDot = genePool.getFirst();
				for (int j = 0; j < mostFitDot.dna.movements.length; j++) {
					identicalChild.dna.movements[j] = mostFitDot.dna.movements[j];
				}
				dots.add(identicalChild);
			} else {
				Dot parentA = getRandomDotByFitness();
				Dot parentB = getRandomDotByFitness();
				dots.add(Dot.breed(parentA, parentB));
			}
		}
		genePool.clear();
		step = 0;
		generation++;
	}
	private static Dot getRandomDotByFitness() {
		double top05Chance = .1d;
		double top25Chance = .3d;
		double top50Chance = .75d;
		int index;
		double random = Math.random();
		//System.out.printf("Random Number: %f\n", random);
		if (random < top05Chance) {
			index = (int) ((genePool.size()*.05) * random);
		} else if (random < top25Chance) {
			index = (int) ((genePool.size()*.25) * random);
		} else if (random < top50Chance) {
			index = (int) ((genePool.size()*.50) * random);
		} else {
			index = (int) (genePool.size() * random);
		}
		//System.out.printf("Grabbing at index %d\n", index);
		Dot dot;
		try {
			dot = genePool.get(index);
		} catch (IndexOutOfBoundsException e) {
			dot = genePool.peekLast();
		}
		return dot;
	}
}

