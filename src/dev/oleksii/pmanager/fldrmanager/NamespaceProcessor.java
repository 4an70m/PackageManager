package dev.oleksii.pmanager.fldrmanager;

import java.util.LinkedList;

/**
 * Created by User on 18.07.2017.
 */
public class NamespaceProcessor {

    public Filters filters;

    public NamespaceProcessor() {
        this.filters = new Filters();
    }

    public static abstract class NamespaceFilter {
        public abstract FilterResult filter();
    }

    public static class FilterResult {

    }

    public static class Filters extends LinkedList<NamespaceFilter> {

    }

}
