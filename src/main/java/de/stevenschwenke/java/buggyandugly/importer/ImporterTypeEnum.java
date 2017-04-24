package de.stevenschwenke.java.buggyandugly.importer;

public enum ImporterTypeEnum {

    // other entries omitted for imports that aren't used in this demo
    IMPORT_PRODUCTION("PRODUCTION", "module.ENUMS.importerType.label.production");

    private final String name;
    private final String resourceKey;

    ImporterTypeEnum(String name, String resourceKey) {
        this.name = name;
        this.resourceKey = resourceKey;
    }

    public String getName() {
        return name;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "Resource key: " + this.resourceKey;
    }
}

