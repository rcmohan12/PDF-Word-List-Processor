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
        String[] text = pdfStripper.getText(document).split("\\.|\\?");
        document.close();

        processLines(text);

    }

    public static List<WordDetail> processLines(String[] text) {

        List<WordDetail> words = new ArrayList<WordDetail>();
        String prevWord = "";

        for(String str : text) {
            String cleaned = str;
            if(str.contains("Ab morgen")) { // Exception case
                cleaned = str.substring(str.indexOf("ab"));
                System.out.println(cleaned);
            }

            WordDetail word = new WordDetail();
            String arr[] = cleaned.split(" ", 2);

            if(prevWord.length() != 0 && containsWord(str, prevWord)) {
                word.setWord(prevWord);
            } else {
                word.setWord(arr[0]);
                prevWord = arr[0].trim();
            }

            word.setExampleStatement(arr[1].replaceAll("(?i)"+prevWord, "______"));
            System.out.println(word);
            words.add(word);

        }
        return words;

    }

    public static boolean containsWord(String inputString, String word) {

        List<String> inputStringList = Arrays.asList(inputString.split(" "));
        return inputStringList.contains(word);

    }

}
