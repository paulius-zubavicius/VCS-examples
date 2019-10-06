package com.vcs.examples.web.news.tagger.word;

import java.util.ArrayList;
import java.util.List;

public class WordRate {

    private List<Word> words = new ArrayList<>();
    private Word word;

    public WordRate(Word word) {
        this.word = word;
        words.add(word);
    }

    public List<Word> getWords() {
        return words;
    }

    public int getCount() {
        return words.size();
    }

    public Word getWord() {
        return word;
    }
}
