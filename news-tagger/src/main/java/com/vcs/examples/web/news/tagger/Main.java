package com.vcs.examples.web.news.tagger;


import com.vcs.examples.web.news.tagger.parsers.DelfiParser;
import com.vcs.examples.web.news.tagger.parsers.WebParser;
import com.vcs.examples.web.news.tagger.top.TopWords;
import com.vcs.examples.web.news.tagger.word.Word;
import com.vcs.examples.web.news.tagger.word.WordRate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        WebParser wp = new DelfiParser();

        List<String> urls = wp.getArticlesURL();
        urls.forEach(System.out::println);

        TopWords tw = new TopWords();

        List<String> wordsStr = tw.clearMeaningLess(wp.getArticleText(urls).toString());
        List<Word> words = tw.convertToWords(wordsStr);
        Collections.sort(wordsStr, (a1, a2) -> a2.length() - a1.length());

        List<WordRate> top = tw.countByRoot(words);

        for (WordRate r : top) {
            System.out.println(r.getWord().getRoot() + " (" + r.getWords().size() + ") " + Arrays.toString(r.getWords().toArray()));
        }

    }

}
