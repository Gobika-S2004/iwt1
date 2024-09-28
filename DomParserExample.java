import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Scanner;

public class DomParserExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        System.out.print("Enter the XML file name (with extension): ");
        String xmlFileName = scanner.nextLine();

        System.out.print("Enter the ID to search for: ");
        String searchId = scanner.nextLine();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFileName); // Use user input for XML file

            document.getDocumentElement().normalize();

            NodeList sectionList = document.getElementsByTagName("section");
            boolean found = false;

            for (int i = 0; i < sectionList.getLength(); i++) {
                Node section = sectionList.item(i);
                if (section.getNodeType() == Node.ELEMENT_NODE) {
                    Element sectionElement = (Element) section;
                    String id = sectionElement.getAttribute("id"); 
                    if (id.equals(searchId)) {
                        found = true;
                        String title = sectionElement.getElementsByTagName("title").item(0).getTextContent();
                        String description = sectionElement.getElementsByTagName("description").item(0).getTextContent();
                        System.out.println("\nSection Title: " + title);
                        System.out.println("Description: " + description);
                    }
                }
            }

            if (!found) {
                System.out.println("No section found with ID: " + searchId);
            }
            Node missionNode = document.getElementsByTagName("mission").item(0);
            if (missionNode != null) {
                Element missionElement = (Element) missionNode;
                String missionHeadline = missionElement.getElementsByTagName("headline").item(0).getTextContent();
                String missionDescription = missionElement.getElementsByTagName("description").item(0).getTextContent();
                System.out.println("\nMission Headline: " + missionHeadline);
                System.out.println("Mission Description: " + missionDescription);
            }

            String contactInfo = document.getElementsByTagName("contact-info").item(0).getTextContent();
            System.out.println("\nContact Info: " + contactInfo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
