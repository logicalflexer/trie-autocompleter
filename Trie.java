// Olayinka Olufowoshe
// N01487625
// Assignment 4
import java.util.List;
import java.util.ArrayList;

class TrieNode {
    boolean isEndOfWord;
    TrieNode[] children;

    public TrieNode(){
        isEndOfWord = false;
        children = new TrieNode[62]; // Array to store 62 possible children (a-z, A-Z, 0-9)

    }
}

class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    private int getIndex(char c){
        if(c >= 'a' && c <= 'z'){
            return c - 'a';
        } else if(c >= 'A' && c <= 'Z'){
            return c - 'A' + 26;
        } else if(c >= '0' && c <= '9'){
            return c - '0' + 52;
        } else {
            throw new IllegalArgumentException("Invalid character: " + c);
        }
    }

    public void insert(String word){
        if (word == null || word.isEmpty()){
            return;
        }
        TrieNode current = root;
        for(char c : word.toCharArray()){
            int index = getIndex(c);
            if(current.children[index] == null){
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word){
        if (word == null || word.isEmpty()){
            return false;
        }
        TrieNode current = root;
        for(char c : word.toCharArray()){
            int index = getIndex(c);
            if(current.children[index] == null){
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    private TrieNode findNodeForPrefix(String prefix){
        TrieNode current = root;
        for(char c : prefix.toCharArray()){
            int index = getIndex(c);
            if(current.children[index] == null){
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
    public List<String> getWordsStartingWith(String prefix){
        List<String> result = new ArrayList<>();
        TrieNode prefixNode = findNodeForPrefix(prefix);

        if(prefixNode == null){
            return result;
        }

        dfs(prefixNode, new StringBuilder(prefix), result);

        return result;
    }
    private void dfs(TrieNode prefixNode, StringBuilder word, List<String> result){
        if(prefixNode.isEndOfWord){
            result.add(word.toString());
        }

        for(int i = 0; i < 62; i++){
            if(prefixNode.children[i] != null){
                word.append(indexToChar(i));
                dfs(prefixNode.children[i], word, result);
                word.deleteCharAt(word.length()-1);
            }
        }
    }
    private char indexToChar(int index){
        if (index >= 0 && index < 26){
            return (char) (index + 'a');
        } else if(index >= 26 && index < 52 ){
            return (char) (index - 26 + 'A');
        } else {
            return (char) (index - 52 + '0');
        }
    }
}