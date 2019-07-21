package com.product.data.local;

import com.product.data.Dataset;
import com.product.data.TrieNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/** author: Ranjith Manickam @ 21 July' 2019 */
@Getter
@ToString
@AllArgsConstructor
public class LocalDataset implements Dataset {

    private final TrieNode badWords;
}
