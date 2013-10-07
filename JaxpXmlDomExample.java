// Core Java APIs
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

// JAXP
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

// DOM
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class JaxpXmlDomExample {

	private static void printNode(Node node, String indent) {

		switch (node.getNodeType()) {

		case Node.DOCUMENT_NODE:
			System.out.println("<xml version=\"1.0\">\n");
			NodeList nodes = node.getChildNodes();
			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					printNode(nodes.item(i), "");
				}
			}
			break;

		case Node.ELEMENT_NODE:
			String name = node.getNodeName();
			System.out.print(indent + "<" + name);
			NamedNodeMap attributes = node.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) {
				Node current = attributes.item(i);
				System.out.print(" " + current.getNodeName() + "=\""
						+ current.getNodeValue() + "\"");
			}
			System.out.print(">");

			// recurse on each child
			NodeList children = node.getChildNodes();
			if (children != null) {
				for (int i = 0; i < children.getLength(); i++) {
					printNode(children.item(i), indent + "  ");
				}
			}

			System.out.print("</" + name + ">");
			break;

		case Node.TEXT_NODE:
			System.out.print(node.getNodeValue());
			break;
		}

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		if (args.length != 1) {
			System.err.println("\nUsage: java JaxpXmlDomExample filename\n");
			System.exit(1);
		}

		File xmlFile = new File(args[0]);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Turn on validation, and turn off namespaces
		factory.setValidating(true);
		factory.setNamespaceAware(false);

		// Obtain a do*****ent builder object
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);

		printNode(doc, "");
	}

}
