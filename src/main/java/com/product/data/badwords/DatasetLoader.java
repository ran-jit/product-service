package com.product.data.badwords;

import com.product.data.TrieNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** author: Ranjith Manickam @ 21 July' 2019 */
public class DatasetLoader {

    /**
     * To build the data set from text file.
     *
     * @param path - data .txt file path.
     * @param node - Trie node instance to transform the data for processing.
     * @throws IOException - If file not exists handled exception.
     */
    public static void build(Path path, TrieNode node) throws IOException {
        Files.lines(path).forEach(line -> addToTrie(node, line.toLowerCase(), 0));
    }

    /** Helper method for loading data in Trie node. */
    private static void addToTrie(TrieNode node, String line, int index) {
        if (line == null || index > line.length()) {
            return;
        }

        Character ch = line.charAt(index);
        if (!node.containsChild(ch)) {
            node.addChild(ch);
        }

        if (index == line.length() - 1) {
            node.getChild(ch).setLast(Boolean.TRUE);
            return;
        }

        addToTrie(node.getChild(ch), line, ++index);
    }
}
