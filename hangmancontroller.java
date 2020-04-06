import java.util.Scanner;

public class hangmancontroller {
    boolean gameOver = false;
    int currentWordCount = 0;
    int guesscount = 0;
    private int isAlphaPresent[];
    HangmanLexicon wordLex;
    hangmancontroller(){
        wordLex = new HangmanLexicon();
        isAlphaPresent = new int[26];
    }
    private boolean isGameOver() {
        for(int i = 0; i<26;i++)
            if(isAlphaPresent[i]==1)
                return false;
        return true;
    }
    public int run(){
        System.out.println("The game has started.");
        String hiddenWord;
        try {
            hiddenWord = wordLex.getWord(currentWordCount);
        } catch (Exception e) {
            System.out.println("Oops, you have used all words.");
            return 1;
        }
        int hiddenWordLength = hiddenWord.length();

        // isAlphaPresent stores 1 for all characters present in the hiddenWord and 2 for all correctly guessed characters
        for (int i = 0; i<hiddenWordLength; i++){
            isAlphaPresent[hiddenWord.charAt(i) - 65] = 1;
        }

        //main game loop
        while(guesscount<=8 && gameOver==false){
            System.out.println("Guesses left: " + (8-guesscount) + "\nPlease enter your guess. (an alphabet)");
            for (int i = 0; i < hiddenWordLength; i++){
                if(isAlphaPresent[hiddenWord.charAt(i) - 65]==1)
                    System.out.printf("_");
                
                else if(isAlphaPresent[hiddenWord.charAt(i) - 65]==2)
                    System.out.printf("%c", hiddenWord.charAt(i));
            }
            System.out.println(":");
            Scanner reader = new Scanner(System.in);
            char c = reader.next().charAt(0);
            if(isAlphaPresent[c - 65] == 1 ){
                System.out.println("Correct guess!");
                isAlphaPresent[c - 65] = 2;
            }
            else if(isAlphaPresent[c - 65] == 2)
                System.out.println("You have already guessed that character correctly.");
            else{
                System.out.println("Incorrectly guessed :(");
                guesscount++;
            }
            gameOver = isGameOver();
            reader.close();
        }

        if(gameOver)
            System.out.println("Congrats, you have guessed the word correctly: " + hiddenWord);
        else   
            System.out.println("Oops, you loose.");

        return 0;
    }
    public static void main(String[] args) {
        hangmancontroller game = new hangmancontroller();
        game.run();
    }
}