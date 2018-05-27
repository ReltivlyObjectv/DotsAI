/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tech.relativelyobjective.dotsai;

import java.util.Random;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class Movement {
	int xVec;
	int yVec;
	
	public Movement() {
		xVec = 0;
		yVec = 0;
	}
	public Movement(int x, int y) {
		xVec = x;
		yVec = y;
	}
	public static Movement randomMovement() {
		Movement newMovement = new Movement();
		Random rand = new Random();
		newMovement.xVec = rand.nextInt(3)-1;
		newMovement.yVec = rand.nextInt(3)-1;
		return newMovement;
	}
}
