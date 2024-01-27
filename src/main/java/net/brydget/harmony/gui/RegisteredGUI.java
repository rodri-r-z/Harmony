package net.brydget.harmony.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RegisteredGUI {

    String title = "";
    int size = 0;
    Map<Integer, ItemStack> items = new HashMap<>();

    /**
     * Sets the title of the GUI and returns the GUIBuilder instance.
     *
     * @param  title  the title to be set
     * @return       the GUIBuilder instance
     */

    public RegisteredGUI setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the width of the GUI builder.
     *
     * @param  size  the width to set
     * @return       the GUI builder with the width set
     */
    public RegisteredGUI setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Set the items for the GUI builder.
     *
     * @param  items  a map of integer keys to ItemStack values
     * @return       the GUIBuilder instance with the updated items
     */
    public RegisteredGUI setItems(Map<Integer, ItemStack> items) {
        this.items = items;
        return this;
    }

    /**
     * Set an item in the GUI at the specified slot.
     *
     * @param  slot   the slot where the item will be set
     * @param  item   the item to be set in the GUI
     * @return       the GUIBuilder object
     */
    public RegisteredGUI setItem(int slot, ItemStack item) {
        this.items.put(slot, item);
        return this;
    }

    /**
     * Builds and returns an Inventory object.
     *
     * @return         	the built Inventory object
     */
    Inventory build() {
        Inventory inv = Bukkit.createInventory(null, size, title);
        items.forEach(inv::setItem);

        return inv;
    }

}
