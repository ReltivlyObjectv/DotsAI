/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tech.relativelyobjective.dotsai;

/**
 *
 * @author Christian Russell (me@relativelyobjective.tech)
 */
public class DNA {
	public static final int movementLength = 2500;
	
	public Movement[] movements;
	
	public DNA() {
		movements = new Movement[movementLength];
		for (int i = 0; i < movementLength; i++) {
			movements[i] = Movement.randomMovement();
		}
	}
}
