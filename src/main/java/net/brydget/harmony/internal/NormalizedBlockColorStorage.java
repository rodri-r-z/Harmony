package net.brydget.harmony.internal;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    
    public Map<String, ItemStack> AllColors = new HashMap<>();
    
    protected void _save() {
        final List<Field> methods = Arrays.stream(this.getClass().getFields()).collect(Collectors.toList());
        for (Field method : methods) {
            try {
                AllColors.put(method.getName(), (ItemStack) method.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void __save() {
        _save();
    }

    public abstract void _init(String modifier);

}
