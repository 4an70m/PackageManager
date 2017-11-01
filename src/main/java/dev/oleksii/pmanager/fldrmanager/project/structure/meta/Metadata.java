package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

public abstract class Metadata extends WindowsObject {

    public Metadata(String pathname) {
        super(pathname);
    }

    public Metadata(File file) {
        super(file);
    }

    @Override
    public Element getPackageXml(Document document) {
        Element element = document.createElement("members");
        Node elementValue = document.createTextNode(FilenameUtils.removeExtension(this.getName()));
        element.appendChild(elementValue);
        return element;
    }
}
