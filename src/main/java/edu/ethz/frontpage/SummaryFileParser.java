package edu.ethz.frontpage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author jait
 */
public class SummaryFileParser {
    
    public String parse(Path summaryFile) throws Exception {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(new File(summaryFile.toString()), handler);
                
        return handler.builder.toString();
    }
    
}

class SAXHandler extends DefaultHandler {
    StringBuilder builder = new StringBuilder();
    boolean isParsing = false;
    
    @Override
    public void startElement(String uri, String localName, String qName,
                            Attributes attributes) throws SAXException {
        if ("TEXT".equals(qName)) {
            isParsing = true;
        }
    }
    
    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        if (isParsing) {
            builder.append(new String(chars, i, i1));
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if("TEXT".equals(qName)) {
            isParsing = false;
        }
    }
}
