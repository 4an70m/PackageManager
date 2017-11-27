package ihor_selskyi.generate_package_xml;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by new on 14.11.2017.
 */
public class PackageXmlBuilder {

    private static final String PATH_TO_CONFIG_FILE = "C:\\Users\\new\\Desktop\\Новая папка (2)\\PackageManager\\src\\main\\java\\ihor_selskyi\\generate_package_xml\\config.json";
    private static final String PATH_TO_GROOVY_SCRIPT_FILE = "C:\\Users\\new\\Desktop\\Новая папка (2)\\PackageManager\\src\\main\\java\\ihor_selskyi\\generate_package_xml\\GeneratePackage.groovy";

    private String pathToProjectSrc;
    private String pathToPackageXml;
    private String version;

    public PackageXmlBuilder(String pathToProjectSrc, String pathToPackageXml, String version) throws PackageXmlBuilderException {
        this.pathToProjectSrc = pathToProjectSrc;
        this.pathToPackageXml = pathToPackageXml;
        this.version = version;
        if (this.pathToPackageXml == null) throw new PackageXmlBuilderException("Path to package.xml is not defined");
        if (this.pathToProjectSrc == null) throw new PackageXmlBuilderException("Path to project src is not defined");
        if (this.version == null) throw new PackageXmlBuilderException("Version is not defined");

    }

    public void run() throws PackageXmlBuilderException{
        ClassLoader parent = PackageXmlBuilder.class.getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        Class groovyClass = null;
        GroovyObject groovyObject = null;
        try{
            groovyClass = loader.parseClass(new File(PATH_TO_GROOVY_SCRIPT_FILE));
            groovyObject = (GroovyObject) groovyClass.newInstance();
        }catch (Exception e){
            throw new PackageXmlBuilderException(e.getMessage());
        }

        Object[] path = {PATH_TO_CONFIG_FILE, this.pathToProjectSrc, this.pathToPackageXml, this.version};
        groovyObject.setProperty("args", path);
        Object[] argz = {};
        groovyObject.invokeMethod("run", argz);
    }

    public class PackageXmlBuilderException extends Exception {

        public PackageXmlBuilderException() {
        }

        public PackageXmlBuilderException(String message) {
            super(message);
        }
    }
}
