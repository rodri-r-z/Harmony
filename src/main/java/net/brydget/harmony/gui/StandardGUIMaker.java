package net.brydget.harmony.gui;

public abstract class StandardGUIMaker {

    // This is a utility class to create GUIs for your plugins

    public static GUIBuilder builder() {
        return new GUIBuilder();
    }

}
