package dev.oleksii.pmanager.fldrmanager.project.structure;

import dev.oleksii.pmanager.fldrmanager.project.structure.meta.WindowsObject;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
    public String toString() {
        return "" + this.getName() + "\n\t{" + structure + '}';
    }
}
