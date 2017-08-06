package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import java.io.File;

public final class PackageXml extends WindowsObject {

    public PackageXml(String pathname) {
        super(pathname);
    }

    public PackageXml(File file) {
        super(file);
    }
}
