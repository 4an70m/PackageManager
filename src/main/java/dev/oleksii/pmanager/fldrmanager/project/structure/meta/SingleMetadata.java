package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import java.io.File;

public final class SingleMetadata extends Metadata {

    public SingleMetadata(String pathname) {
        super(pathname);
    }

    public SingleMetadata(File file) {
        super(file);
    }
}
