package dev.oleksii.pmanager.fldrmanager;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 18.07.2017.
 */
public class Project {

    protected Folder projectFolder;

    public Project(String path) {
        this.projectFolder = new Folder(path);
    }

    @Override
    public String toString() {
        return "Project{\n" +
                projectFolder +
                '}';
    }


    public static class WindowsObject extends File{

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

    public static class Folder extends WindowsObject {

        private FilesStructure structure;

        public Folder(String pathname) {
            super(pathname);

            this.structure = new FilesStructure();

            for (File file : this.listFiles()) {
                this.structure.add(file);
            }
        }

        public Folder(File file) {
            this(file.getAbsolutePath());
        }

        public List<WindowsObject> traverse() {
            List<WindowsObject> result = new LinkedList<>();
            for (WindowsObject wobject : structure) {
                result.add(wobject);
                if (wobject instanceof Folder) {
                    result.addAll(((Folder) wobject).traverse());
                }
            }
            return result;
        }

        @Override
        public String toString() {
            return "" + this.getName() + "\n\t{" + structure + '}';
        }
    }

    public static class PackageXml extends WindowsObject {

        public PackageXml(String pathname) {
            super(pathname);
        }

        public PackageXml(File file) {
            super(file);
        }
    }

    public static class MetadataFile extends WindowsObject {

        public MetadataFile(String pathname) {
            super(pathname);
        }

        public MetadataFile(File file) {
            super(file);
        }
    }



    public static class ObjectMetadataFile extends MetadataFile {
        public ObjectMetadataFile(String pathname) {
            super(pathname);
        }

        public ObjectMetadataFile(File file) {
            super(file);
        }
    }

    public static class MetaMetadataField extends MetadataFile {
        public MetaMetadataField(String pathname) {
            super(pathname);
        }

        public MetaMetadataField(File file) {
            super(file);
        }
    }

    public static abstract class MetaPairedMetadataFile extends MetadataFile {

        protected MetaMetadataField metaFile;

        public MetaPairedMetadataFile(String pathname) {
            super(pathname);
        }

        public MetaPairedMetadataFile(File file) {
            super(file);
        }
    }

    public static class ClassMetadataFile extends MetaPairedMetadataFile {
        public ClassMetadataFile (String pathname) {
            super(pathname);
        }
    }

    public static class TriggerMetadataFile extends MetaPairedMetadataFile {
        public TriggerMetadataFile (String pathname) {
            super(pathname);
        }

        public TriggerMetadataFile(File file) {
            this(file.getName());
        }
    }


    public static class FilesStructure extends LinkedList<WindowsObject> {
        public boolean add(File file) {
            WindowsObject windowsObject = null;
            if (file.isDirectory()) {
                windowsObject = new Folder(file);

            } else if (file.isFile()) {

                if ("package.xml".equals(file.getName())) {
                    windowsObject = new PackageXml(file);

                } else {
                    windowsObject = this.createMetadataFile(file);
                }
            }

            return super.add(windowsObject);
        }

        private MetadataFile createMetadataFile(File file) {
            MetadataFile result = null;
            String fileExtension = this.getFileExtension(file);
            switch(fileExtension) {
                case "object": return new ObjectMetadataFile(file);
                case "trigger": return new TriggerMetadataFile(file);
            }
            return result;
        }

        private String getFileExtension(File file) {
            String name = file.getName();
            return name.substring(name.lastIndexOf(".") + 1);
        }

        @Override
        public String toString() {
            return "{" + Arrays.toString(this.toArray()) + "}\n\n";
        }
    }




    public static void main(String[] args) {
        Project proj = new Project("C:\\Users\\User\\Documents\\4an70m\\src");
    }
}
