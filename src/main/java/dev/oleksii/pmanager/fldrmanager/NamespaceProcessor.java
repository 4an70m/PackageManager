package dev.oleksii.pmanager.fldrmanager;

import java.util.LinkedList;

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
