package com.wmz.library.parser.sax;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by wmz on 18/6/16.
 */
public class BaseParser {

    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;
    private InputStream is;
    private BaseParserHandler baseParserHandler;
    public BaseParser(InputStream is,BaseParserHandler baseParserHandler){
        this.is = is;
        this.baseParserHandler = baseParserHandler;

        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void parse(){
        try {
            parser.parse(is,baseParserHandler);
            is.close();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
