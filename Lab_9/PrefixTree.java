import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;

class TrieNode {
    public char value;
    public boolean isBranch_End;
    Map<Character, TrieNode> children;

    public TrieNode(char value) {
        this.value = value;
        this.children = new HashMap<>();
        this.isBranch_End = false;
    }

    public void markAsLeaf() {
        this.isBranch_End = true;
    }

}

public class PrefixTree {
    private TrieNode root;
    private TrieNode new_node;

    public PrefixTree() {
        root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!current.children.containsKey(ch)) {
                TrieNode new_node = new TrieNode(ch);
                current.children.put(ch, new_node);
            }
            current = current.children.get(ch);
        }
        current.markAsLeaf();
    }

    public boolean search(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!current.children.containsKey(ch)) {
                return false;
            }

            current = current.children.get(ch);
        }

        return current.isBranch_End;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            if (!current.children.containsKey(prefix.charAt(i))) {
                return false;
            }
            
            current = current.children.get(prefix.charAt(i));
        }

        return true;
    }

    public void traverse() {
        for (TrieNode child : root.children.values()) {
            printTrie(child, " ");
        }
    }

    private void printTrie(TrieNode node, String indent) {
        System.out.print(indent + " └── " + node.value);
        if (node.isBranch_End) System.out.print(" (end)");
        System.out.println();

        for (TrieNode child : node.children.values()) {
            printTrie(child, indent + "  ");
        }
    }

    

    public static void main(String[] args) {
        PrefixTree trie = new PrefixTree();
  
      }
}