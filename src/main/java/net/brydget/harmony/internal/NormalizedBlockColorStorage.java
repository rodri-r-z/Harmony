package net.brydget.harmony.internal;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;

public abstract class NormalizedBlockColorStorage {

    protected static ItemStack BLACK;
    protected static ItemStack BLUE;
    protected static ItemStack BROWN;
    protected static ItemStack CYAN;
    protected static ItemStack GRAY;
    protected static ItemStack GREEN;
    protected static ItemStack LIGHT_BLUE;
    protected static ItemStack LIGHT_GRAY;
    protected static ItemStack LIME;
    protected static ItemStack MAGENTA;
    protected static ItemStack ORANGE;
    protected static ItemStack PINK;
    protected static ItemStack PURPLE;
    protected static ItemStack RED;
    protected static ItemStack WHITE;
    protected static ItemStack YELLOW;
    
    public Map<String, ItemStack> AllColors;
    
    protected void _save() {
        Arrays.stream(this.getClass().getFields())
                .forEach(a -> {
                    try {
                        AllColors.put(a.getName(), (ItemStack) a.get(this));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void __save() {
        _save();
    }

    public abstract void _init(String modifier);

}
