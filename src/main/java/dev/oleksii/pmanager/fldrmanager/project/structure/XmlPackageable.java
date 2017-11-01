package dev.oleksii.pmanager.fldrmanager.project.structure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by new on 15.08.2017.
 */
public interface XmlPackageable {
    Element getPackageXml(Document document);
}
