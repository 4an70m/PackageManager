package dev.oleksii.pmanager.fldrmanager.project.structure;

import dev.oleksii.pmanager.fldrmanager.project.structure.meta.PackageXml;
import dev.oleksii.pmanager.fldrmanager.project.structure.meta.PairedMetadata;
import dev.oleksii.pmanager.fldrmanager.project.structure.meta.SingleMetadata;
import dev.oleksii.pmanager.fldrmanager.project.structure.meta.WindowsObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class FileStructure extends LinkedHashMap<String, WindowsObject> {

    public static final String META_XML = "-meta.xml";

    public FileStructure() {
        super();
    }

    public void put(File file) throws IOException {
        WindowsObject windowsObject = null;

        if (file.isDirectory()) {
            windowsObject = new Folder(file);

        } else {

            if ("package.xml".equals(file.getName())) {
                windowsObject = new PackageXml(file);

            } else {
                windowsObject = new SingleMetadata(file);
            }
        }

        super.put(windowsObject.getName(), windowsObject);
    }

    public void restructureSingleMetadata() {
        Set<String> entriesToRemove = new HashSet<>();

        for (WindowsObject originalWobject : this.values()) {
            if (originalWobject instanceof SingleMetadata) {
                String originalPath = originalWobject.getName();

                String objectMetadataPath = this.makeObjectMetadataPath(originalPath);
                String metaMetadataPath = this.makeMetaMetadataPath(originalPath);

                if (this.containsKey(objectMetadataPath) && this.containsKey(metaMetadataPath)) {
                    PairedMetadata pair = new PairedMetadata(objectMetadataPath, metaMetadataPath);
                    super.replace(objectMetadataPath, pair);
                    entriesToRemove.add(metaMetadataPath);
                }
            }
        }

        this.keySet().removeIf(entriesToRemove::contains);
    }

    private String makeObjectMetadataPath(String path) {
        if (path.contains(META_XML)) {
            return path.replaceFirst(META_XML, "");
        }
        return path;
    }

    private String makeMetaMetadataPath(String path) {
        if (!path.contains(META_XML)) {
            return path + META_XML;
        }
        return path;
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(this.values().toArray()) + "}\n\n";
    }
}
