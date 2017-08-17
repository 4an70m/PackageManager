package dev.oleksii.pmanager.fldrmanager.project.packagexmlgenerator.generator;

import dev.oleksii.pmanager.fldrmanager.project.structure.XmlPackageable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by new on 15.08.2017.
 */
public class PackageXmlGenerator {

    private XmlPackageable rootObject;

    public XmlPackageable getRootObject() {
        return rootObject;
    }

    public PackageXmlGenerator(XmlPackageable rootObject) {
        this.rootObject = rootObject;
    }

    public void getPackageXml() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document packageXmlDocument = docBuilder.newDocument();
            Node rootElement = this.rootObject.getPackageXml(packageXmlDocument);
            packageXmlDocument.appendChild(rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(packageXmlDocument);
            StreamResult streamResult = new StreamResult(new File("D:\\test.xml"));

            transformer.transform(source, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
