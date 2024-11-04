// Olayinka Olufowoshe
// N01487625
// Assignment 4
import java.util.ArrayList;
import java.util.List;

public class Autocompleter {
    private Trie trie;

    public Autocompleter() {
        trie = new Trie();
    }


    public void insert(String word){
        if (word == null || word.isEmpty() || !word.matches("[a-zA-Z]+")){
            return;
        }
        trie.insert(word);
    }


    public List<String> getSuggestions(String prefix){
        if (prefix == null || prefix.isEmpty()){
            return new ArrayList<>();
        }
        return trie.getWordsStartingWith(prefix);
    }
}
