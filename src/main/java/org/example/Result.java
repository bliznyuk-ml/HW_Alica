package org.example;

public class Result implements Comparable<Result> {

    long wordCount;
    String word;

    public Result(long wordCount, String word) {
        this.wordCount = wordCount;
        this.word = word;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word + ": " + wordCount;
    }

    @Override
    public int compareTo(Result o) {
        return (int) (o.getWordCount() - this.getWordCount());
    }
}


