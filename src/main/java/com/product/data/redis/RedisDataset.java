package com.product.data.redis;

import com.product.data.Dataset;
import com.product.data.TrieNode;

/** author: Ranjith Manickam @ 21 July' 2019 */
public class RedisDataset implements Dataset {

    /** {@inheritDoc} */
    @Override
    public TrieNode getBadWords() {
        // To be implemented with redis data cache for distributed processing.
        return new TrieNode();
    }
}
