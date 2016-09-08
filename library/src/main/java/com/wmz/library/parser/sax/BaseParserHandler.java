package com.wmz.library.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by wmz on 18/6/16.
 */
public abstract class BaseParserHandler extends DefaultHandler{
    /**
     * 解析完成监听器
     */
    protected ParseEndListener mParseEndListener;

    public BaseParserHandler(ParseEndListener listener){
        mParseEndListener = listener;
    }

    @Override
    public abstract void startDocument() throws SAXException;

    @Override
    public abstract void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;

    @Override
    public abstract void characters(char[] ch, int start, int length) throws SAXException;

    @Override
    public abstract void endElement(String uri, String localName, String qName) throws SAXException;

    @Override
    public abstract void endDocument() throws SAXException;

    public interface ParseEndListener<T>{
        void onParseEnd(T t);
    }
}
