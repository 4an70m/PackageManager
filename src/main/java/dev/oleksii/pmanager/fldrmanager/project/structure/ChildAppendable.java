package dev.oleksii.pmanager.fldrmanager.project.structure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by new on 17.08.2017.
 */
public interface ChildAppendable {
    void appendChild(Element folder, Document document);
}
