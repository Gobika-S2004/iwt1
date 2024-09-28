import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParserExample {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean isTitle = false;
                boolean isDescription = false;
                boolean isHeadline = false;
                boolean isContactInfo = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("section")) {
                        System.out.println("\nSection ID: " + attributes.getValue("id"));
                    } else if (qName.equalsIgnoreCase("title")) {
                        isTitle = true;
                    } else if (qName.equalsIgnoreCase("description")) {
                        isDescription = true;
                    } else if (qName.equalsIgnoreCase("headline")) {
                        isHeadline = true;
                    } else if (qName.equalsIgnoreCase("contact-info")) {
                        isContactInfo = true;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("title")) {
                        isTitle = false;
                    } else if (qName.equalsIgnoreCase("description")) {
                        isDescription = false;
                    } else if (qName.equalsIgnoreCase("headline")) {
                        isHeadline = false;
                    } else if (qName.equalsIgnoreCase("contact-info")) {
                        isContactInfo = false;
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (isTitle) {
                        System.out.println("Title: " + new String(ch, start, length));
                    } else if (isDescription) {
                        System.out.println("Description: " + new String(ch, start, length).trim());
                    } else if (isHeadline) {
                        System.out.println("Mission Headline: " + new String(ch, start, length));
                    } else if (isContactInfo) {
                        System.out.println("Contact Info: " + new String(ch, start, length).trim());
                    }
                }
            };

            saxParser.parse("data.xml", handler); // Replace with your XML file name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

