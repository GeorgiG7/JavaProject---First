package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        byte[] computerNumber = generateFourDigitNumberInArray();
        Scanner input = new Scanner(System.in);
        int playerNumber;
        int[] playerNumberArray = new int[4];
        System.out.println(Arrays.toString(computerNumber));
        int cows, bulls;
        System.out.println("Компютърът е готов с числото. Въведете вашето: ");
        while (true){
            cows = 0;
            bulls = 0;

            playerNumber = input.nextInt();

            playerNumberArray[0] = playerNumber/1000;
            playerNumberArray[1] = (playerNumber%1000)/100;
            playerNumberArray[2] = (playerNumber/10)%10;
            playerNumberArray[3] = playerNumber%10;
            System.out.println(Arrays.toString(playerNumberArray));

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == j && computerNumber[i] == playerNumberArray[j]){
                        bulls++;
                    } else if (computerNumber[i] == playerNumberArray[j]){
                        cows++;
                    }
                }
            }

            System.out.println(playerNumber + " " + cows + " крави и " + bulls + " бикове");
            if (playerNumber == 0) break;
        }
    }

    public static byte[] generateFourDigitNumberInArray(){
        Random rn = new Random();
        byte firstDigit = (byte) (rn.nextInt(9) + 1);
        byte secondDigit = (byte) rn.nextInt(10);
        byte thirdDigit = (byte) rn.nextInt(10);
        byte forthDigit = (byte) rn.nextInt(10);
        while (secondDigit == firstDigit){
            secondDigit = (byte) rn.nextInt(10);
        }
        while (thirdDigit == secondDigit || thirdDigit == firstDigit){
            thirdDigit = (byte) rn.nextInt(10);
        }
        while (forthDigit == thirdDigit ||
                forthDigit == secondDigit || forthDigit == firstDigit){
            forthDigit = (byte) rn.nextInt(10);
        }
        return new byte[]{firstDigit, secondDigit, thirdDigit, forthDigit};
    }
}
