package com.vcs.examples.web.news.tagger.top;

import com.vcs.examples.web.news.tagger.word.Word;
import com.vcs.examples.web.news.tagger.word.WordRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TopWords {

    private List<String> meaningLess = Arrays.asList("ir", "iki", "su", "anot", "taip", "ne", "pat", "ar", "arba", "be", "bei", "gal", "bet", "kaip", "pati", "juk", "šią", "tų", "tais",
            "kai", "kur", "dėl", "kad", "tik", "tam", "nes", "net", "jei", "ten", "nors", "apie", "yra", "į", "tos", "bus", "ką", "tą", "štai", "tuos", "šios", "kitų", "kokį", "kurį", "tame", "pati",
            "čia", "per", "nuo", "iš", "pas", "šio", "šių", "to", "ji", "jų", "jį", "ją", "jo", "jos", "jie", "jus", "jas", "jai", "juo", "jam", "šį", "ši", "man", "mes", "lyg", "aš", "dar", "o", "tuo", "tad", "jau", "jog", "vis", "po", "nė", "tiek", "už", "tie", "neva", "kuo", "pro");

    public List<String> clearMeaningLess(String text) {

        String[] wrdsArr = text.split(" ");

        List<String> wordsStr = new ArrayList<String>(Arrays.asList(wrdsArr));
        wordsStr.removeAll(meaningLess);

        return wordsStr;
    }

    public List<Word> convertToWords(List<String> wordsStr) {
        return wordsStr.stream().map(Word::new).collect(Collectors.toList());
    }


    public List<WordRate> countByRoot(List<Word> words) {

        List<WordRate> result = new ArrayList<>();

        Collections.sort(words, (a1, a2) -> a2.getStr().length() - a1.getStr().length());

        for (Word w : words) {
            addToMap(result, w);
        }

        //words.stream().sorted((a1,a2) -> a2.getStr().length() -  a1.getStr().length() ).collect(Collectors.groupingBy());
        //words.stream().collect(new Map<Word, List<Word>>, (map, word, ) );
        Collections.sort(result, (a1, a2) -> a2.getWords().size() - a1.getWords().size());
        return result;
    }

    private void addToMap(List<WordRate> rates, Word w) {

        for (WordRate rate : rates) {
            if (isSimilarWord(w, rate.getWord())) {
                rate.getWords().add(w);
                return;
            }
        }

        rates.add(new WordRate(w));
    }

    private boolean isSimilarWord(Word newWord, Word mapWord) {

        if (newWord.equals(mapWord)) {
            return true;
        }

        return mapWord.getRoot().startsWith(newWord.getRoot());
    }


}
