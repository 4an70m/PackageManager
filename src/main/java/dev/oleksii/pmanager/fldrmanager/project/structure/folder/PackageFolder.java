package dev.oleksii.pmanager.fldrmanager.project.structure.folder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;

/**
 * Created by new on 17.08.2017.
 */
public class Package extends Folder{

    public Package(String pathname) throws IOException {
        super(pathname);
    }

    public Package(File file) throws IOException {
        super(file);
    }

    @Override
    public Element getPackageXml(Document document) {
        Element packageElement = document.createElement("Package");
        packageElement.setAttribute("xmlns", "");
        Node attribute = document.createAttribute();

    }
}
