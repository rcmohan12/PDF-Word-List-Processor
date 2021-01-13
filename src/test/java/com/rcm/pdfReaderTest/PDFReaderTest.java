package com.rcm.pdfReaderTest;

import com.rcm.entity.WordDetail;
import com.rcm.pdfReader.PDFReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PDFReaderTest {

    @Test
    public void processLinesTest1() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"die Butter Für mich bitte ein Brötchen mit Butter und Käse"});
        Assert.assertEquals("Failed to extract the key word", "Butter", word.get(0).getWord());
    }

    @Test
    public void processLinesTest2() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"buchstabieren Bitte buchstabieren Sie Ihren Namen."});
        Assert.assertEquals("Failed to extract the key word", "buchstabieren", word.get(0).getWord());
    }

    @Test
    public void processLinesTest3() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"aber Ich bin oft im Büro, aber nur für wenige Stunden"});
        Assert.assertEquals("Failed to extract the key word", "aber", word.get(0).getWord());
    }

    @Test
    public void processLinesTest4() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"ab Ab morgen muss ich arbeiten",  "aber Ich bin oft im Büro, aber nur für wenige Stunden"});
        Assert.assertEquals("Failed to extract the key word", "ab", word.get(0).getWord());
        Assert.assertEquals("Failed to extract the key word", "aber", word.get(1).getWord());
    }

    @Test
    public void processLinesTest5() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"abholen Wann kann ich den Schrank bei dir abholen?",  "Wir müssen noch meinen Bruder abholen."});
        Assert.assertEquals("Failed to extract the key word", "abholen", word.get(0).getWord());
        Assert.assertEquals("Failed to extract the key word", "abholen", word.get(1).getWord());
    }

    @Test
    public void processLinesTest6() {
        List<WordDetail> word = PDFReader.processLines( new String[] {" der Anfang Sie wohnt am Anfang der Straße.",  "Wir machen Anfang Juli Urlaub."});
        Assert.assertEquals("Failed to extract the key word", "Anfang", word.get(0).getWord());
        Assert.assertEquals("Failed to extract the key word", "Anfang", word.get(1).getWord());
    }

    @Test
    public void processLinesTest7() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"bekannt Picasso ist sehr bekannt.", "der/die Bekannte, -n \t Ein Bekannter von mir heißt Klaus."});
        Assert.assertEquals("Failed to extract the key word", "bekannt", word.get(0).getWord());
        Assert.assertEquals("Failed to extract the key word", "Bekannte", word.get(1).getWord());
    }

    @Test
    public void processLinesTest8() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"die Dame, -n \t Damen (an der Toilette)"});
        Assert.assertEquals("Failed to extract the key word", "Dame", word.get(0).getWord());
    }

    @Test
    public void processLinesTest9() {
        List<WordDetail> word = PDFReader.processLines( new String[] {"die Dame, -n \t Damen (an der Toilette)"});
        Assert.assertEquals("Failed to extract the key word", "Dame", word.get(0).getWord());
    }

}
