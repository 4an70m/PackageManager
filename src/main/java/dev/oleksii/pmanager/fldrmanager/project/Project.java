package dev.oleksii.pmanager.fldrmanager.project;

import dev.oleksii.pmanager.fldrmanager.project.packagexmlgenerator.generator.PackageXmlGenerator;
import dev.oleksii.pmanager.fldrmanager.project.structure.folder.Folder;
import dev.oleksii.pmanager.fldrmanager.project.structure.folder.PackageFolder;

import java.io.IOException;

@SuppressWarnings("All")
public class Project {

    protected Folder projectFolder;

    public Project(String path) {
        try {
            this.projectFolder = new PackageFolder(path);
            this.projectFolder.restructureFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Project{\n" +
                projectFolder +
                '}';
    }


    public static void main(String[] args) {
        Project proj = new Project("C:\\Users\\new\\IdeaProjects\\abbottDev1Test\\src");
        System.out.println(proj);
        PackageXmlGenerator xmlGenerator = new PackageXmlGenerator(proj.projectFolder);
        xmlGenerator.getPackageXml();
    }
}
