package ihor_selskyi;

import ihor_selskyi.generate_package_xml.PackageXmlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by new on 24.11.2017.
 */
public class Main {
    public static void main(String[] args) throws PackageXmlBuilder.PackageXmlBuilderException {
        PackageXmlBuilder generator =  new PackageXmlBuilder(
                "C:\\Users\\new\\Desktop\\force-meta-backup-master\\build\\metadata",
                "C:\\Users\\new\\Desktop\\force-meta-backup-master\\build\\metadata\\package.xml",
                "41.0");
        generator.run();
    }
}
