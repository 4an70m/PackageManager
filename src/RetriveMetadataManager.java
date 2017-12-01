import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import ihor_selskyi.generate_package_xml.PackageXmlBuilder;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import java.io.File;

/**
 * Created by new on 27.11.2017.
 */
public class RetriveMetadataManager {


    public void run(String login, String password, Boolean isProduction) throws RetriveMetadataException {
        String url = (isProduction)? "https://login.salesforce.com": "https://test.salesforce.com";
        try{
            File buildFile = new File("C:\\Users\\new\\Desktop\\Новая папка (2)\\PackageManager\\src\\build.xml");
            Project p = new Project();
            p.setUserProperty("ant.file", buildFile.getAbsolutePath());
            p.setProperty("sf.username", login);
            p.setProperty("sf.password", password);
            p.setProperty("sf.serverurl", url);
            p.init();
            ProjectHelper helper = ProjectHelper.getProjectHelper();
            p.addReference("ant.projectHelper", helper);
            helper.parse(p, buildFile);
            p.executeTarget("backupMetadata");
        }catch (Exception e){
            throw new RetriveMetadataException(e.getMessage());
        }

    }

    public class RetriveMetadataException extends Exception {

        public RetriveMetadataException() {
        }

        public RetriveMetadataException(String message) {
            super(message);
        }
    }
}
