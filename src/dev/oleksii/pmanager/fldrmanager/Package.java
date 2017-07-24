package dev.oleksii.pmanager.fldrmanager;

/**
 * Created by User on 18.07.2017.
 */
public class Package extends Project{

    public Package(String path) {
        super(path);
    }

    public void selectFileByName(String name) {
        for (WindowsObject wobject : projectFolder.traverse()) {
            if (name.equals(wobject.getName())) {
                wobject.select();
                return;
            }
        }
    }

    public void formPackage() {
        for (WindowsObject wobject : projectFolder.traverse()) {
            if (wobject.isSelected()) {
                System.out.println(wobject.getName());
            }
        }
    }

    public static void main(String[] args) {
        Package proj = new Package("C:\\Users\\User\\Documents\\4an70m\\src");
        proj.selectFileByName("TaskTrigger.trigger");
        proj.formPackage();
    }
}
