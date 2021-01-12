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

}
