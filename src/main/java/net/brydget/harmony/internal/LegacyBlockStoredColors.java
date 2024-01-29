package net.brydget.harmony.internal;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

public class LegacyBlockStoredColors extends NormalizedBlockColorStorage {

    public void _init(String modifier) {
        modifier = modifier.toUpperCase();

        // Load all colors using reflection
        String finalModifier = modifier;
        Arrays.stream(LegacyBlockStoredColors.class.getFields()).forEach(a -> {
            try {
                // Filter only necessary colors
                String colorName = a.getName();

                if (!colorName.toUpperCase().equals(colorName)) {
                    return;
                }

                // Rare exception: LIGHT_GRAY_WOOL is not available on legacy versions
                // Skip and use GRAY instead
                if (colorName.equals("LIGHT_GRAY")) {
                    colorName = "GRAY";
                }
                DyeColor dyeColor = DyeColor.valueOf(
                        colorName
                );

                // The method: toItemStack() is deprecated
                // It means that it was not deprecated some time ago
                // Try to use this method to ensure backwards compatibility-

                final MaterialData data = Material.valueOf(finalModifier).getNewData(dyeColor.getDyeData());
                ItemStack result;

                try {
                    result = (ItemStack) data.getClass().getMethod("toItemStack", int.class)
                            .invoke(data, 1);
                } catch (Exception e) {
                    result = (ItemStack) data.getClass().getMethod("toItemStack")
                            .invoke(data);
                }

                a.set(LegacyBlockStoredColors.class, result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
