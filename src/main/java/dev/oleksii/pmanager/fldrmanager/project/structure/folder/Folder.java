package dev.oleksii.pmanager.fldrmanager.project.structure.folder;

import dev.oleksii.pmanager.fldrmanager.project.structure.ChildAppendable;
import dev.oleksii.pmanager.fldrmanager.project.structure.FileStructure;
import dev.oleksii.pmanager.fldrmanager.project.structure.meta.WindowsObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Folder extends WindowsObject implements ChildAppendable {

    private static final Map<String, String> FOLDER_NAME_TYPE_NAME_MAPPING = new HashMap<>();
    static {
        FOLDER_NAME_TYPE_NAME_MAPPING.put("classes", "ApexClass");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("components", "ApexComponent");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("pages", "ApexPage");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("triggers", "ApexTrigger");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("objects", "CustomObject");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("flows", "Flow");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("staticresources", "StaticResource");
        FOLDER_NAME_TYPE_NAME_MAPPING.put("workflows", "Workflow");

    }

    public static String getTypeName(String folderName) {
        String typeName = FOLDER_NAME_TYPE_NAME_MAPPING.get(folderName);
        return typeName;
    }

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
        return createChildElements(document, folderElement);
    }

    protected Element createChildElements(Document document, Element folderElement) {
        List<Element> childElements = this.structure.values()
                                                    .stream()
                                                    .map(childElement -> childElement.getPackageXml(document))
                                                    .collect(Collectors.toList());

        childElements.stream().forEach(folderElement::appendChild);
        appendChild(folderElement, document);

        return folderElement;
    }

    @Override
    public void appendChild(Element folder, Document document) {
        Element nameElement = document.createElement("name");
        Node nameElementValue = document.createTextNode(Folder.getTypeName(this.getName()));
        nameElement.appendChild(nameElementValue);

        folder.appendChild(nameElement);
    }

    @Override
    public String toString() {
        return "" + this.getName() + "\n\t{" + structure + '}';
    }
}
