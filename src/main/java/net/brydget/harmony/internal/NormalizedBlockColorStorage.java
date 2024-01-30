package net.brydget.harmony.internal;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class NormalizedBlockColorStorage {

    public ItemStack BLACK;
    public ItemStack BLUE;
    public ItemStack BROWN;
    public ItemStack CYAN;
    public ItemStack GRAY;
    public ItemStack GREEN;
    public ItemStack LIGHT_BLUE;
    public ItemStack LIGHT_GRAY;
    public ItemStack LIME;
    public ItemStack MAGENTA;
    public ItemStack ORANGE;
    public ItemStack PINK;
    public ItemStack PURPLE;
    public ItemStack RED;
    public ItemStack WHITE;
    public ItemStack YELLOW;
    
    public Map<String, ItemStack> AllColors = new HashMap<>();
    
    protected void _save() {
        final List<Field> methods = Arrays.stream(this.getClass().getFields()).collect(Collectors.toList());
        for (Field method : methods) {
            final String b = method.getName();
            if (!b.toUpperCase().equals(b)) {
                continue;
            }

            try {
                AllColors.put(b, (ItemStack) method.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void __save() {
        _save();
    }

    public abstract void _init(String modifier);

}
