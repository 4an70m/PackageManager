package dev.oleksii.pmanager.fldrmanager.project.structure.folder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;

/**
 * Created by new on 17.08.2017.
 */
public class PackageFolder extends Folder{

    public PackageFolder(String pathname) throws IOException {
        super(pathname);
    }

    public PackageFolder(File file) throws IOException {
        super(file);
    }

    @Override
    public Element getPackageXml(Document document) {
        Element packageElement = document.createElement("Package");
        packageElement.setAttribute("xmlns", "http://soap.sforce.com/2006/04/metadata");

        return super.createChildElements(document, packageElement);
    }

    @Override
    public void appendChild(Element folder, Document document) {
        Element versionElement = document.createElement("version");
        Node versionElementValue = document.createTextNode("40");
        versionElement.appendChild(versionElementValue);

        folder.appendChild(versionElement);
    }
}
