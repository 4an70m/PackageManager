package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import java.io.File;

public abstract class Metadata extends WindowsObject {

    public Metadata(String pathname) {
        super(pathname);
    }

    public Metadata(File file) {
        super(file);
    }
}
