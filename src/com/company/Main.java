package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printEntering();

        Scanner input = new Scanner(System.in);
        byte choice = input.nextByte();

        int playerNumber;
        int[] playerNumberArray;
        int cows, bulls;
        String cowsString, bullsString;
        byte[] computerOneNumber = generateFourDigitNumberInArray();
        String spaces = "                               ";
        if (choice == 1) {
            StringBuilder playerOneString = new StringBuilder();
            while (true) {
                cows = 0;
                bulls = 0;

                System.out.println("Въведете вашето число:");

                playerNumber = input.nextInt();
                playerNumberArray = putDigitsInArray(playerNumber);

                for (int i = 0; i < playerNumberArray.length - 1; i++) {
                    for (int j = i + 1; j < playerNumberArray.length; j++) {
                        if (playerNumber != 0 && (playerNumberArray[i] == playerNumberArray[j])) {
                            playerNumber = 404;
                            break;
                        }
                    }
                }

                if (playerNumber != 0 && (playerNumber < 1000 || playerNumber > 9999)) {
                    System.out.println("Невалидно число. Въведете " +
                            "четирицифрено число с четири различни цифри!");
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == j && computerOneNumber[i] == playerNumberArray[j]) {
                            bulls++;
                        } else if (computerOneNumber[i] == playerNumberArray[j]) {
                            cows++;
                        }
                    }
                }

                cowsString = cows > 1 || cows == 0 ? "крави" : "крава";
                bullsString = bulls > 1 || bulls == 0 ? "бика" : "бик";

                playerOneString.append("Числото " + playerNumber + " има " + cows + " " +
                        cowsString + " и " + bulls + " " + bullsString + "\n");
                if (playerNumber == 0 || bulls == 4) {
                    System.out.println("Печелите!!!");
                    break;
                }
                System.out.println(playerOneString.toString());
            }
        }else {
            int turn = 1;
            byte[] computerTwoNumber = generateFourDigitNumberInArray();
            byte[] turnNumber;
            StringBuilder playerOneString =
                    new StringBuilder("Играч 1:" + spaces + spaces + "| Играч 2:\n");
            while (true) {
                cows = 0;
                bulls = 0;

                System.out.println("На ред е играч " + turn + ". Въведете следващото си число:");

                playerNumber = input.nextInt();
                if (playerNumber == 0) break;
                playerNumberArray = putDigitsInArray(playerNumber);

                for (int i = 0; i < playerNumberArray.length - 1; i++) {
                    for (int j = i + 1; j < playerNumberArray.length; j++) {
                        if (playerNumberArray[i] == playerNumberArray[j]) {
                            playerNumber = 404;
                            break;
                        }
                    }
                }

                if (playerNumber < 1000 || playerNumber > 9999) {
                    System.out.println("Невалидно число. Въведете " +
                            "четирицифрено число с четири различни цифри!");
                    continue;
                }

                turnNumber = turn==1?computerOneNumber:computerTwoNumber;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == j && turnNumber[i] == playerNumberArray[j]) {
                            bulls++;
                        } else if (turnNumber[i] == playerNumberArray[j]) {
                            cows++;
                        }
                    }
                }

                cowsString = cows > 1 || cows == 0 ? "крави" : "крава";
                bullsString = bulls > 1 || bulls == 0 ? "бика" : "бик";

                if (turn == 1){
                    playerOneString.append("Числото " + playerNumber + " има " + cows + " " +
                            cowsString + " и " + bulls + " " + bullsString + spaces + "      | ");
                } else {
                    playerOneString.append("Числото " + playerNumber + " има " + cows + " " +
                            cowsString + " и " + bulls + " " + bullsString + "\n");
                }
                System.out.println(playerOneString.toString());
                if (bulls == 4) {
                    System.out.println("Играч " + turn + " печели!!!");
                    break;
                }
                turn = turn==1?2:1;
            }
            System.out.println("Exit");
        }

    }

    public static byte[] generateFourDigitNumberInArray() {
        Random rn = new Random();
        byte firstDigit = (byte) (rn.nextInt(9) + 1);
        byte secondDigit = (byte) rn.nextInt(10);
        byte thirdDigit = (byte) rn.nextInt(10);
        byte forthDigit = (byte) rn.nextInt(10);
        while (secondDigit == firstDigit) {
            secondDigit = (byte) rn.nextInt(10);
        }
        while (thirdDigit == secondDigit || thirdDigit == firstDigit) {
            thirdDigit = (byte) rn.nextInt(10);
        }
        while (forthDigit == thirdDigit ||
                forthDigit == secondDigit || forthDigit == firstDigit) {
            forthDigit = (byte) rn.nextInt(10);
        }
        return new byte[]{firstDigit, secondDigit, thirdDigit, forthDigit};
    }

    public static int[] putDigitsInArray(int playerNumberInt) {
        int[] playerNumberArray = new int[4];
        playerNumberArray[0] = playerNumberInt / 1000;
        playerNumberArray[1] = (playerNumberInt % 1000) / 100;
        playerNumberArray[2] = (playerNumberInt / 10) % 10;
        playerNumberArray[3] = playerNumberInt % 10;
        return playerNumberArray;
    }

    public static void printEntering() {
        System.out.println("Това е играта \"Бикове и крави\". Правилата са прости:" +
                "\nКомпютърът си намисля четирицифрено число с различни цифри," +
                " а ние трябва да го познаем,\nкато въвеждаме четирицифрени числа, " +
                "а той ни казва колко крави и колко бика имаме" +
                "\nКрава е цифра от нашето число, която я има в това на противника" +
                "(компютъра),\nно не е на същото място. Бикът е позната цифра и позицията ѝ." +
                "\n\nИзберете брой играчи(1 или 2):");
    }
}
