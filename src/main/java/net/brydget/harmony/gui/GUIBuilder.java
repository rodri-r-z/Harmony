package net.brydget.harmony.gui;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GUIBuilder {
    List<RegisteredGUI> pages = new ArrayList<>();
    List<Inventory> inventories = new ArrayList<>();
    static String PAGE_VARIABLE_REGEX = "(?i)\\{page}|(?i)%page%";

    public RegisteredGUI newPage(String title) {
        title = title.replaceAll(
                PAGE_VARIABLE_REGEX,
                String.valueOf(pages.size() + 1)
        );

        final RegisteredGUI page = new RegisteredGUI()
                .setTitle(title);
        newPage(page);

        return page;
    }

    public void deletePage(int page) {
        pages.remove(page);
    }

    public void deletePage(RegisteredGUI page) {
        pages.remove(page);
    }

    public void setPage(int index, RegisteredGUI page) {
        pages.set(index, page);
    }

    public RegisteredGUI newPage(RegisteredGUI page) {
        pages.add(page);
        return page;
    }

    public int pageCount() {
        return pages.size();
    }

    public PaginatedGUI build() {
        for (RegisteredGUI page : pages) {
            inventories.add(page.build());
        }

        final PaginatedGUI gui = new PaginatedGUI();
        gui.inventories = inventories;
        return gui;
    }

}
