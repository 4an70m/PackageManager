package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

public final class PackageXml extends WindowsObject {

    public PackageXml(String pathname) {
        super(pathname);
    }

    public PackageXml(File file) {
        super(file);
    }

    @Override
    public Element getPackageXml(Document document) {
        Element temp = document.createElement("packagefile");
        Node value = document.createTextNode("package.xml");
        temp.appendChild(value);

        return temp;
    }
}
