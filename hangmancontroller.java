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
        System.out.println("The round has started.");
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
        Scanner reader = new Scanner(System.in);
        //main game loop
        while(guesscount<8 && isGameOver()==false){
            System.out.println("Guesses left: " + (8-guesscount));
            for (int i = 0; i < hiddenWordLength; i++){
                if(isAlphaPresent[hiddenWord.charAt(i) - 65]==1)
                    System.out.printf("_ ");
                
                else if(isAlphaPresent[hiddenWord.charAt(i) - 65]==2)
                    System.out.printf("%c ", hiddenWord.charAt(i));
            }
            System.out.println("Please enter your guess. (an alphabet):");
            
            char c = reader.next().charAt(0); 
            c = Character.toUpperCase(c);
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
            System.out.println("\n\n");
        }
        if(gameOver)
            System.out.println("Congrats, you have guessed the word correctly: " + hiddenWord);
        else   
            System.out.println("Oops, you loose.");

        return 0;
    }
    public void setForNewRound() {
        for(int i = 0; i<26; i++)
            this.isAlphaPresent[i] = 0;
        guesscount = 0;
    }
    public static void main(String[] args) {
        hangmancontroller game = new hangmancontroller();
        Scanner read = new Scanner(System.in);
        System.out.println("The game has started.");
        while(game.currentWordCount < game.wordLex.getWordCount()){
            game.run();
            System.out.println("Do you want to play another round?('y' to answer yes)");
            char c = read.next().charAt(0); 
            // c = Character.toUpperCase(c);
            
            if(c != 'y')
                break;
            game.currentWordCount++;
            game.setForNewRound();
        }
        read.close();
    }
}