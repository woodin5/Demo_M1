package com.wmz.test.parser;

import com.wmz.library.parser.sax.BaseParserHandler;
import com.wmz.test.bean.Book;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmz on 18/6/16.
 */
public class BookParser extends BaseParserHandler {
    private List<Book> books;
    private Book book;
    private String currentTag = null;
    public BookParser(ParseEndListener listener) {
        super(listener);
    }

    @Override
    public void startDocument() throws SAXException {
        books = new ArrayList<Book>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("book".equals(qName)){
            book = new Book();
            book.setId(attributes.getValue(0));
        }
        currentTag = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(currentTag!=null){
            String content = new String(ch,start,length);
            if("name".equals(currentTag)){
                book.setName(content);
            }else if("price".equals(currentTag)){
                book.setPrice(content);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("book".equals(qName)) {
            books.add(book);
            book = null;
        }
        currentTag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        mParseEndListener.onParseEnd(books);
    }

    public List<Book> getBooks() {
        return books;
    }
}
