package dev.oleksii.pmanager.fldrmanager;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by User on 18.07.2017.
 */
public class Project {

    private Folder projectFolder;

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

    private static class Folder extends WindowsObject {

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

        @Override
        public String toString() {
            return "" + this.getName() + "\n\t{" + structure + '}';
        }
    }

    private static class PackageXml extends WindowsObject {

        public PackageXml(String pathname) {
            super(pathname);
        }

        public PackageXml(File file) {
            super(file);
        }
    }

    private static class MetadataFile extends WindowsObject {

        public MetadataFile(String pathname) {
            super(pathname);
        }

        public MetadataFile(File file) {
            super(file);
        }
    }

    private static class FilesStructure extends LinkedList<WindowsObject> {
        public boolean add(File file) {
            WindowsObject windowsObject = null;
            if (file.isDirectory()) {
                windowsObject = new Folder(file);

            } else if (file.isFile()) {

                if ("package.xml".equals(file.getName())) {
                    windowsObject = new PackageXml(file);

                } else {
                    windowsObject = new MetadataFile(file);
                }
            }

            return super.add(windowsObject);
        }

        @Override
        public String toString() {
            return "{" + Arrays.toString(this.toArray()) + "}\n\n";
        }
    }




    public static void main(String[] args) {
        Project proj = new Project("C:\\Users\\User\\Documents\\4an70m\\src");
        System.out.println(proj);
    }
}
