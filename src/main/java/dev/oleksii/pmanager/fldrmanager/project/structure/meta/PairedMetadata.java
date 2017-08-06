package dev.oleksii.pmanager.fldrmanager.project.structure.meta;

import java.io.File;

public final class PairedMetadata extends Metadata {

    protected Metadata metaFile;

    public PairedMetadata(String maingObjectPath, String additionalObjectPath) {
        super(maingObjectPath);
        this.metaFile = new SingleMetadata(additionalObjectPath);
    }

    public PairedMetadata(File mainFile, File additionalFile) {
        super(mainFile);
        this.metaFile = new SingleMetadata(additionalFile);
    }

    public PairedMetadata(File mainFile, Metadata additionalMeta) {
        super(mainFile);
        this.metaFile = additionalMeta;
    }

    @Override
    public String toString() {
        return "(" + this.getName() + " : " + metaFile + ")";
    }
}
