package net.brydget.harmony.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PageSlotClickEvent {

    int slot;
    int page;
    Inventory inventory;
    InventoryClickEvent event;

    public int getPage() {
        return page;
    }

    public int getSlot() {
        return slot;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryClickEvent getBaseEvent() {
        return event;
    }

}
