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

    private final StopWordsHandler stopWordsHandler;
    private final ObjectionableContentHandler objectionableContentHandler;

    @Autowired
    public ProductCommentHandler(StopWordsHandler stopWordsHandler, ObjectionableContentHandler objectionableContentHandler) {
        this.stopWordsHandler = stopWordsHandler;
        this.objectionableContentHandler = objectionableContentHandler;
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

        // comment has only stop words (ex: hello)
        if (comment.isEmpty()) {
            return false;
        }

        // check preprocessed comment has bad words
        return this.objectionableContentHandler.isObjectionableContent(comment);
    }

    /**
     * To pre-process the request comment.
     *
     * This pre-process removes all the stop words.
     *
     * @param comment - input comment to be processed.
     * @return - returns the pre-processed comment.
     */
    private String preprocess(String comment) {
        // tokenize the comment
        List<String> words = Collections.list(new StringTokenizer(comment)).stream()
                .map(token -> ((String) token).toLowerCase().trim())
                .collect(Collectors.toList());

        // removing all stop words
        this.stopWordsHandler.removeAllStopWords(words);

        return String.join(" ", words);
    }
}
