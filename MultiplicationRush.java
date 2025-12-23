//WIP - um "jogo" com o objetivo de auxiliar o aprendizado e prática das tabuadas de multiplicação básicas, ou seja, do 2 ao 9.
//TODO: incluir histórico com as operações respondidas, destacando quais foram corretas ou erradas
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class MultiplicationRush {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int multiplier = 0;
        boolean validMultiplier = false;

        System.out.println("Qual tabuada você deseja praticar?");

        while(!validMultiplier) {
            multiplier = Integer.valueOf(scanner.nextLine());

            if(multiplier >= 2 && multiplier <= 9) {
                validMultiplier = true;
                continue;
            }

            System.out.println("Apenas valores de 2 a 9 serão aceitos. Tente de novo:");
        }

        int correctAnswers = 0;
        int wrongAnswers = 0;

        while(true) {
            int multiplicand = generateRandomInt(2, 9);
            
            System.out.println(multiplicand + " × " + multiplier);
            ArrayList<Integer> alternatives = generateAlternatives(multiplicand, multiplier);
            printAlternatives(alternatives);
            
            int userAnswer = Integer.valueOf(scanner.nextLine());
            
            if(userAnswer == -1) {
                break;
            }

            while(userAnswer < 1 || userAnswer > 4) {
                System.out.println("\nAlternativa inválida. Apenas valores de 1 a 4 serão aceitos.");
                
                printAlternatives(alternatives);
                userAnswer = Integer.valueOf(scanner.nextLine());
            }
            
            int answerIndex = userAnswer - 1;

            if(alternatives.get(answerIndex) == (multiplicand * multiplier)) {
                correctAnswers++;
                continue;
            }

            wrongAnswers++;
        }
        scanner.close();

        System.out.print("Respostas corretas: " + correctAnswers + "\tRespostas erradas: " + wrongAnswers);
    }

    public static int generateRandomInt(int min, int max) {
        Random numberGenerator = new Random();
        return numberGenerator.nextInt(max - min + 1) + min;
    }

    public static void printAlternatives(ArrayList<Integer> alternatives) {
        int alternativePosition = 1;

        for(int alternative: alternatives) {
            System.out.print("Alternativa" + "[" + alternativePosition + "]" + " = " + alternative + "\t\t\t");   
            
            if(alternativePosition % 2 == 0) {
                System.out.println("");
            } 

            alternativePosition++;
        }

        System.out.println("\t\t\tParar" + "[" + -1 + "]");
    }

    public static ArrayList<Integer> generateAlternatives(int multiplicand, int multiplier) {
        ArrayList<Integer> alternatives = new ArrayList<>();

        for(int alternative = 1; alternative <= 4; alternative++) {
            boolean generatedUniqueAlternative = false;
            
            while(!generatedUniqueAlternative) {
                int leastPossibleValue = multiplier * (multiplicand - 2);
                int greatestPossibleValue = multiplier * (multiplicand + 1);
                int generatedNumber = generateRandomInt(leastPossibleValue, greatestPossibleValue);

                if(!alternatives.contains(generatedNumber) && generatedNumber % multiplier == 0) {
                    alternatives.add(generatedNumber);
                    generatedUniqueAlternative = true;
                }
            }
        }
        
        return alternatives;
    }
}
