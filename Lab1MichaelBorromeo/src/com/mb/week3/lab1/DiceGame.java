package com.mb.week3.lab1;

/**
 * @author michael_borromeo
 * @since 2026-09-24
 * This program makes the user roll a dice. If they get any winning numbers they win. If they get any losing numbers they lose.
 * If they get any number which is out of the list, they need to roll that number again in order to win that round.
 */

import java.security.SecureRandom; // program uses class SecureRandom

public class DiceGame {

	public static void main(String[] args) {
		SecureRandom randomNumbers = new SecureRandom();

		int face, sum = 0, newSum = 0;

		System.out.println("Winning numbers: 7, 11, 15, 21");
		System.out.println("Losing numbers: 10, 12, 19, 20, 22, 23, 24\n");
		
		System.out.print("Rolls: ");
		for (int i = 0; i < 4; i++) {
			face = 1 + randomNumbers.nextInt(6);
			System.out.print(face + " ");
			sum += face;
		}
		System.out.println();
		if (sum == 10 || sum == 12 || sum == 19 || sum == 20 || sum == 22 || sum == 23 || sum == 24) {
			System.out.println("Sorry, your sum came up to " + sum + ", which means you lost!");
		} else if (sum == 7 || sum == 11 || sum == 15 || sum == 21) {
			System.out.println("Congratulations! Your sum came up to " + sum + ", which means you win!");
		} else {
			System.out.println("You did not roll an initial winning number! Try to roll " + sum + " again to win!\n");
			System.out.print("Rolls: ");
			for (int j = 0; j < 4; j++) {
				face = 1 + randomNumbers.nextInt(6);
				System.out.print(face + " ");
				newSum += face;
			}
			System.out.println();
			if (newSum == sum) {
				System.out.println("Congratulations! Your sum came up to " + sum + ", which means you win!");
			} 
			else if (newSum == 13){
				System.out.println("Sorry, your sum came up to " + newSum + ", which means you lost!");
			}
			else {
				System.out.println("Sorry, your sum came up to " + newSum + ", which means you lost!");			}
		}

	}

}
