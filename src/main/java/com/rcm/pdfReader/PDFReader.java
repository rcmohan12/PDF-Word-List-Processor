package com.rcm.pdfReader;

import com.rcm.entity.WordDetail;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFReader {

    public static void main(String args[]) throws IOException {
        //Loading an existing document
        ClassLoader classLoader = PDFReader.class.getClassLoader();
        File file = new File(classLoader.getResource("A1.pdf").getFile());
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage( 9 );
        pdfStripper.setEndPage( 27 );

        //Retrieving text from PDF document
        //Max data that can be held by a string is INT.Max ~ 2 billion. But the practical value would depend on the heap space configured
        String[] text = pdfStripper.getText(document).split("\\.|\\?|: \\.\\.\\.|\t");
        document.close();

        //method that extracts line data
        processLines(text);

    }

    public static List<WordDetail> processLines(String[] text) {

        List<WordDetail> words = new ArrayList<WordDetail>();
        String prevWord = "";

        for(String str : text) {
            String cleaned = str.trim();

            try {
                if(str.contains("Ab morgen")) { // Exception case
                    cleaned = str.substring(str.indexOf("ab"));
                    System.out.println(cleaned);
                }

                WordDetail word = new WordDetail();
                if(cleaned.startsWith("der")
                        || cleaned.startsWith("die")
                        || cleaned.startsWith("das")
                        || cleaned.startsWith("der/die")) {
                    String arr[] = cleaned.split(" ", 3);

                    prevWord = updateWordObj(str, prevWord, word, arr[1].trim(), arr[2], arr[0].trim());

                } else {
                    String arr[] = cleaned.split(" ", 2);

                    if(arr.length > 1) {
                        prevWord = updateWordObj(str, prevWord, word, arr[0].trim(), arr[1], "");
                    }

                }

                words.add(word);
            } catch (Exception e) {
                System.out.println("Failed to process this line :"+str);
            }
        }

        return words;

    }

    private static String updateWordObj(String str, String prevWord, WordDetail word, String root, String sentence, String article) {
        try {

            if(prevWord.length() != 0 && containsPreviousWord(str, prevWord)) {
                word.setWord(prevWord);
                word.setExampleStatement(str.replaceAll("(?i)"+prevWord, " ______ "));
            } else {
                word.setWord(root);
                prevWord = root;

                word.setExampleStatement(sentence.replaceAll("(?i)"+prevWord, " ______ "));

            }

            word.setArticle(article);

            System.out.println(word);

        } catch (Exception e) {
            System.out.println("Failed to construct the WordDetails object. ");
        }

        return prevWord;
    }

    public static boolean containsPreviousWord(String inputString, String word) {

        List<String> inputStringList = Arrays.asList(inputString.split(" "));
        return inputStringList.contains(word) || inputStringList.contains(word+".");

    }

}
