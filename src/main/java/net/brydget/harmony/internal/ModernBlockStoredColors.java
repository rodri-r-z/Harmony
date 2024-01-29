package net.brydget.harmony.internal;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ModernBlockStoredColors extends NormalizedBlockColorStorage {

    // This class stores all colors for Wool
    // those colors are not available at the Spigot API we're using
    // this will be loaded when a server is NOT legacy

    public void _init(String modifier) {
        // Load all colors using reflection
        Arrays.stream(ModernBlockStoredColors.class.getFields()).forEach(a -> {
            try {
                // Filter only necessary colors
                final String colorName = a.getName();

                if (!colorName.toUpperCase().equals(colorName)) {
                    return;
                }

                Material material = loadMaterial(colorName+"_"+modifier);
                if (material != null) {
                    a.set(ModernBlockStoredColors.class, new ItemStack(material));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static Material loadMaterial(String name) {
        return Material.valueOf(name);
    }

}
