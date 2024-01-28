package net.brydget.harmony.gui;

import net.brydget.harmony.util.MessageColorFormatter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class GUIButtonUtil {
    static ItemStack NEXT_PAGE = new ItemStack(Material.ARROW);
    static ItemStack PREVIOUS_PAGE = new ItemStack(Material.ARROW);

    public static void _init() {
        // Initialization for all required item stacks
        // This is called automatically in BackendPlugin.java when the plugin is loaded
        ItemMeta nextPageMeta = NEXT_PAGE.getItemMeta();
        ItemMeta previousPageMeta = PREVIOUS_PAGE.getItemMeta();

        nextPageMeta.setDisplayName(MessageColorFormatter.colorize("&aNext page"));
        previousPageMeta.setDisplayName(MessageColorFormatter.colorize("&cPrevious page"));

        nextPageMeta.setLore(Arrays.stream(MessageColorFormatter.colorize(
                "Click to go to the next page."
        ).split("\n")).collect(Collectors.toList()));

        previousPageMeta.setLore(Arrays.stream(MessageColorFormatter.colorize(
                "Click to go to the previous page."
        ).split("\n")).collect(Collectors.toList()));

        NEXT_PAGE.setItemMeta(nextPageMeta);
        PREVIOUS_PAGE.setItemMeta(previousPageMeta);
    }

    // Add "Next Page" and "Previous Page" buttons
    // to the GUIs

    public static void addNextButton(GUIBuilder guiBuilder) {
        guiBuilder.pages.forEach(registeredGUI -> {
            setItem(
                    registeredGUI,
                    NEXT_PAGE,
                    getSlotsBeforeLastCenter(registeredGUI, 2)
            );
        });
    }

    public static void addPreviousButton(GUIBuilder guiBuilder) {
        guiBuilder.pages.forEach(registeredGUI -> {
            setItem(
                    registeredGUI,
                    PREVIOUS_PAGE,
                    getSlotsBeforeLastCenter(registeredGUI, 0)
            );
        });
    }

    public static void addButtons(GUIBuilder guiBuilder) {
        addNextButton(guiBuilder);
        addPreviousButton(guiBuilder);

        for (int i = 0; i < guiBuilder.pages.size(); i++) {
            final ItemStack currentPage = new ItemStack(Material.PAPER);
            final ItemMeta currentPageMeta = currentPage.getItemMeta();
            final RegisteredGUI registeredGUI = guiBuilder.pages.get(i);

            int slot = getSlotsBeforeLastCenter(registeredGUI, 1);

            currentPageMeta.setDisplayName(MessageColorFormatter.colorize("&6Page " + i));
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
