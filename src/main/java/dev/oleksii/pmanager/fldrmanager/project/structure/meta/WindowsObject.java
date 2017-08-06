package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import java.io.File;

public abstract class WindowsObject extends File {

    private boolean isSelected;

    public WindowsObject(String pathname) {
        super(pathname);
    }

    public WindowsObject(File file) {
        this(file.getAbsolutePath());
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void select() {
        isSelected = true;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
