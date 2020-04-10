import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// to modified to read from file
public class HangmanLexicon {
    ArrayList<String> words;
    /** Returns the number of words in the lexicon. */
    HangmanLexicon(){
        words = new ArrayList<String>();
        try {
            readWordFile("wordlist.txt");
        } catch (Exception e) {
            System.out.println("Can't read from file!");
            System.exit(0);
        }
    }
    public int getWordCount() {
        return words.size();
    }

    /* Returns the word at the specified index. */
    private int readWordFile(String filename) throws Exception{
        BufferedReader wordReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String word;
        while ((word = wordReader.readLine()) != null) {
            words.add(word);
        }
        wordReader.close();
        return 0;
    }
    public String getWord(int index) throws Exception {
        if (index < getWordCount()){
            return words.get(index);
        }
        throw new Exception();
    };
}