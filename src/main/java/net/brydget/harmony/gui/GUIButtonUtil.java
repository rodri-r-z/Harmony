package net.brydget.harmony.gui;

import net.brydget.harmony.BackendPlugin;
import net.brydget.harmony.util.MessageColorFormatter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class GUIButtonUtil {
    static ItemStack NEXT_PAGE = new ItemStack(Material.ARROW);
    static ItemStack PREVIOUS_PAGE = new ItemStack(Material.ARROW);
    static BackendPlugin instance = BackendPlugin.getInstance();

    public static void _init() {
        // Initialization for all required item stacks
        // This is called automatically in BackendPlugin.java when the plugin is loaded
        ItemMeta nextPageMeta = NEXT_PAGE.getItemMeta();
        ItemMeta previousPageMeta = PREVIOUS_PAGE.getItemMeta();

        nextPageMeta.setDisplayName(MessageColorFormatter.colorize("&aNext page"));
        previousPageMeta.setDisplayName(MessageColorFormatter.colorize("&cPrevious page"));

        nextPageMeta.setLore(Arrays.stream(MessageColorFormatter.colorize(
                "&r",
                "&fClick to go to the next page."
        ).split("\n")).collect(Collectors.toList()));

        previousPageMeta.setLore(Arrays.stream(MessageColorFormatter.colorize(
                "&r",
                "&fClick to go to the previous page."
        ).split("\n")).collect(Collectors.toList()));

        NEXT_PAGE.setItemMeta(nextPageMeta);
        PREVIOUS_PAGE.setItemMeta(previousPageMeta);
    }

    // Add "Next Page" and "Previous Page" buttons
    // to the GUIs

    public static void addNextButton(GUIBuilder guiBuilder) {
        guiBuilder.pages.forEach(registeredGUI -> {
            int i = getSlotsBeforeLastCenter(registeredGUI, 2);

            registeredGUI.nextPageSlot = i;
            addButton(registeredGUI, NEXT_PAGE, i);
        });
    }

    public static void addPreviousButton(GUIBuilder guiBuilder) {
        guiBuilder.pages.forEach(registeredGUI -> {
            int i = getSlotsBeforeLastCenter(registeredGUI, 0);

            registeredGUI.previousPageSlot = i;
            addButton(registeredGUI, PREVIOUS_PAGE, i);
        });
    }

    public static void addNextButton(GUIBuilder guiBuilder, ItemStack button, int slot) {
        guiBuilder.pages.forEach(registeredGUI -> {
            registeredGUI.nextPageSlot = slot;
            addButton(registeredGUI, button, slot);
        });
    }

    public static void addPreviousButton(GUIBuilder guiBuilder, ItemStack button, int slot) {
        guiBuilder.pages.forEach(registeredGUI -> {
            registeredGUI.previousPageSlot = slot;
            addButton(registeredGUI, button, slot);
        });
    }

    static void addButton(RegisteredGUI registeredGUI, ItemStack itemStack, int slot) {
        registeredGUI.hasInitializedButtons = true;

        setItem(
                registeredGUI,
                itemStack,
                slot
        );
    }

    @Deprecated
    public static void addButtonListeners() {
        // Automatically add listeners for all buttons
        // since this commit
    }

    public static void addButtons(GUIBuilder guiBuilder) {
        addNextButton(guiBuilder);
        addPreviousButton(guiBuilder);

        for (int i = 0; i < guiBuilder.pages.size(); i++) {
            final ItemStack currentPage = new ItemStack(Material.PAPER);
            final ItemMeta currentPageMeta = currentPage.getItemMeta();
            final RegisteredGUI registeredGUI = guiBuilder.pages.get(i);

            int slot = getSlotsBeforeLastCenter(registeredGUI, 1);

            currentPageMeta.setDisplayName(MessageColorFormatter.colorize("&6Page " + (i + 1)));
            currentPage.setAmount(i + 1);

            currentPage.setItemMeta(currentPageMeta);
            setItem(
                    registeredGUI,
                    currentPage,
                    slot
            );
        }
    }

    public static void addButton(GUIBuilder guiBuilder, ItemStack itemStack, int slot) {
        guiBuilder.pages.forEach(registeredGUI -> {
            setItem(
                    registeredGUI,
                    itemStack,
                    slot
            );
        });
    }

    static void setItem(RegisteredGUI registeredGUI, ItemStack itemStack, int slot) {
        registeredGUI.setItem(
                slot,
                itemStack
        );
    }

    static int getSlotsBeforeLastCenter(RegisteredGUI registeredGUI, int slots) {
        return registeredGUI.getSize() - slots - 1;
    }

}
