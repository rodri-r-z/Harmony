package net.brydget.harmony.internal;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class NormalizedBlockColorStorage {

    public static ItemStack BLACK;
    public static ItemStack BLUE;
    public static ItemStack BROWN;
    public static ItemStack CYAN;
    public static ItemStack GRAY;
    public static ItemStack GREEN;
    public static ItemStack LIGHT_BLUE;
    public static ItemStack LIGHT_GRAY;
    public static ItemStack LIME;
    public static ItemStack MAGENTA;
    public static ItemStack ORANGE;
    public static ItemStack PINK;
    public static ItemStack PURPLE;
    public static ItemStack RED;
    public static ItemStack WHITE;
    public static ItemStack YELLOW;
    
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
