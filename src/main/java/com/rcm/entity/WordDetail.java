package com.rcm.entity;

public class WordDetail {

    public String word;
    public String translatedWord;
    public String exampleStatement;
    public String translatedExampleStatement;
    public String article;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getExampleStatement() {
        return exampleStatement;
    }

    public void setExampleStatement(String exampleStatement) {
        this.exampleStatement = exampleStatement;
    }

    public String getTranslatedExampleStatement() {
        return translatedExampleStatement;
    }

    public void setTranslatedExampleStatement(String translatedExampleStatement) {
        this.translatedExampleStatement = translatedExampleStatement;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "WordDetail{" +
                "word='" + word + '\'' +
                ", translatedWord='" + translatedWord + '\'' +
                ", exampleStatement='" + exampleStatement + '\'' +
                ", translatedExampleStatement='" + translatedExampleStatement + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
