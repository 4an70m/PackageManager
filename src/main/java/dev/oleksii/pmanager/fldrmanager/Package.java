package dev.oleksii.pmanager.fldrmanager;

import dev.oleksii.pmanager.fldrmanager.project.Project;
import dev.oleksii.pmanager.fldrmanager.project.structure.meta.WindowsObject;

public class Package extends Project {

    public Package(String path) {
        super(path);
    }

    public void selectFileByName(String name) {
        WindowsObject foundObject = this.projectFolder.findByName(name);
        if (foundObject != null) {
            foundObject.select();
        }
    }

    public void formPackage() {
        for (WindowsObject wobject : projectFolder.traverse()) {
            if (wobject.isSelected()) {
                System.out.println(wobject);
            }
        }
    }

    public static void main(String[] args) {
        Package proj = new Package("C:\\Users\\4an70m\\Documents\\Salesforce\\sfdc-dev-oleksii\\src");
        proj.selectFileByName("AccountCloudCompanyInfoTest.cls");
        proj.formPackage();
    }
}
