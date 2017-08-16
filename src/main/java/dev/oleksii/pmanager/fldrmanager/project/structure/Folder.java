package dev.oleksii.pmanager.fldrmanager.project.structure;

import dev.oleksii.pmanager.fldrmanager.project.structure.meta.WindowsObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class Folder extends WindowsObject {

    private FileStructure structure;

    public Folder(String pathname) throws IOException {
        super(pathname);

        this.structure = new FileStructure();

        for (File file : this.listFiles()) {
            this.structure.put(file);
        }
    }

    public Folder(File file) throws IOException {
        this(file.getAbsolutePath());
    }

    public List<WindowsObject> traverse() {
        List<WindowsObject> result = new LinkedList<>();
        for (WindowsObject wobject : structure.values()) {
            result.add(wobject);
            if (wobject instanceof Folder) {
                result.addAll(((Folder) wobject).traverse());
            }
        }
        return result;
    }

    public void restructureFolder() {
        for (WindowsObject wobject : structure.values()) {
            if (wobject instanceof Folder) {
                Folder folder = (Folder) wobject;
                ((Folder) wobject).structure.restructureSingleMetadata();
                folder.restructureFolder();
            }
        }
    }

    public WindowsObject findByName(String name) {
        if (this.structure.containsKey(name)) {
            return this.structure.get(name);
        }
        for (WindowsObject wobject : this.structure.values()) {
            if (wobject.isDirectory()) {
                return ((Folder) wobject).findByName(name);
            }
        }
        return null;
    }

    @Override
    public Element getPackageXml(Document document) {
        Element folderElement = document.createElement("folder");
        List<Element> childElements = this.structure.values()
                                                    .stream()
                                                    .map(childElement -> childElement.getPackageXml(document))
                                                    .collect(Collectors.toList());

        childElements.stream().forEach(folderElement::appendChild);
        return folderElement;
    }

    @Override
    public String toString() {
        return "" + this.getName() + "\n\t{" + structure + '}';
    }
}
