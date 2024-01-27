package net.brydget.harmony.gui;


import net.brydget.harmony.BackendPlugin;
import net.brydget.harmony.annotation.NeverNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

public class PaginatedGUI {
    // Code is pretty self-explanatory
    // if you understand Spigot API and Java basics, you can understand this

    List<Inventory> inventories;
    int PAGES;
    final BackendPlugin backendPlugin = BackendPlugin.getInstance();
    final PluginManager pluginManager = backendPlugin.getServer().getPluginManager();
    final List<PageSlotClickListener> listeners = new ArrayList<>();

    public PaginatedGUI(List<Inventory> inventories) {
        this.inventories = inventories;
        PAGES = inventories.size();

        if (inventories.isEmpty()) {
            throw new RuntimeException("This GUI was instances manually! Please use GUIBuilder to create GUIS.");
        }

        // Add listeners to cancel InventoryClickEvents
        for (int i = 0; i < inventories.size(); i++) {
            final Inventory a = inventories.get(i);
            int finalI = i;
            pluginManager.registerEvents(new Listener() {
                @EventHandler(ignoreCancelled = true)
                public void onInventoryClick(InventoryClickEvent event) {
                    if (event.getClickedInventory().equals(a)) {
                        event.setCancelled(true);
                        listeners.forEach(b -> {
                            final PageSlotClickEvent event1 = new PageSlotClickEvent();
                            event1.inventory = a;
                            event1.page = finalI;
                            event1.slot = event.getSlot();
                            event1.event = event;

                            b.whenFired(event1);
                        });
                    }
                }
            }, backendPlugin);
        }
    }

    public void addListener(PageSlotClickListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PageSlotClickListener listener) {
        listeners.remove(listener);
    }


    /**
     * A method to show an inventory to a player.
     *
     * @param  player   the player to show the inventory to
     */
    public void show(@NeverNull Player player) {
        player.openInventory(inventories.get(0));
    }

    /**
     * A method to show the inventory to the player.
     *
     * @param  player	the player to show the inventory to
     * @param  page		the page of the inventory to show
     */
    public void show(@NeverNull Player player, int page) {
        player.openInventory(inventories.get(page));
    }

    /**
     * Closes the inventory for the specified player if they have it open.
     *
     * @param  player The player whose inventory to close
     */
    public void close(@NeverNull Player player) {
        if (inventories.stream().anyMatch(a -> a.getViewers().contains(player))) {
            player.closeInventory();
        }
    }

    /**
     * Closes the inventory for the specified player on the given page.
     *
     * @param  player  the player whose inventory will be closed
     * @param  page    the page number of the inventory
     */
    public void close(@NeverNull Player player, int page) {
        if (PAGES <= page) return;

        if (inventories.get(page).getViewers().contains(player)) {
            player.closeInventory();
        }
    }

    /**
     * Opens the next page in the inventory for the given player.
     *
     * @param  player	the player whose inventory will be navigated
     */
    public void nextPage(@NeverNull Player player) {
        if (PAGES <= 1) return;
        final Inventory openInventory = inventories.stream().filter(a -> a.getViewers().contains(player)).findFirst().orElse(null);
        if (openInventory == null) return;

        final int currentIndex = inventories.indexOf(openInventory);
        final Inventory nextInventory = inventories.get(currentIndex + 1);

        if (nextInventory == null) return;

        player.openInventory(nextInventory);
    }

    /**
     * Moves the player to the previous page in the inventory.
     *
     * @param  player	the player to move
     */
    public void previousPage(@NeverNull Player player) {
        if (PAGES <= 1) return;
        final Inventory openInventory = inventories.stream().filter(a -> a.getViewers().contains(player)).findFirst().orElse(null);
        if (openInventory == null) return;

        final int currentIndex = inventories.indexOf(openInventory);
        if (currentIndex == 0) return;

        final Inventory previousInventory = inventories.get(currentIndex - 1);

        if (previousInventory == null) return;

        player.openInventory(previousInventory);
    }

}
