package com.product.handler;

import com.product.entry.CommentEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/** author: Ranjith Manickam @ 21 July' 2019 */
@Component
public class ProductCommentHandler {

    private final ObjectionableContentHandler handler;

    @Autowired
    public ProductCommentHandler(ObjectionableContentHandler handler) {
        this.handler = handler;
    }

    /**
     * To check the request comment entry is objectionable content or not.
     *
     * @param entry - request comment entry to be processed.
     * @return - returns true if the input string is objectionable.
     */
    public Boolean isObjectionableContent(CommentEntry entry) {

        // preprocess the comment
        String comment = preprocess(entry.getComment());

        // check preprocessed comment has bad words
        return this.handler.isObjectionableContent(comment);
    }

    /**
     * To pre-process the request comment.
     *
     * This pre-process removes all the stop words.
     *
     * @param comment - input comment to be processed.
     * @return - returns the pre-processed comment.
     */
    private static String preprocess(String comment) {
        // tokenize the comment
        List<String> words = Collections.list(new StringTokenizer(comment)).stream()
                .map(token -> ((String) token).toLowerCase())
                .collect(Collectors.toList());

        // removing all stop words
        StopWordsHandler.removeAllStopWords(words);

        return String.join(" ", words);
    }
}
