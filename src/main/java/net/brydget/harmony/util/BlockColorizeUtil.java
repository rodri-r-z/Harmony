package net.brydget.harmony.util;

import net.brydget.harmony.annotation.Nullable;
import net.brydget.harmony.internal.LegacyBlockStoredColors;
import net.brydget.harmony.internal.ModernBlockStoredColors;
import net.brydget.harmony.internal.NormalizedBlockColorStorage;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public abstract class BlockColorizeUtil {

    // Some blocks (i.e. Carpets, Glass, Wool, etc...) have colors
    // Blocks come colored at "org.bukkit.Material", however they do not on legacy versions
    // If you use legacy versions with that Material, you'll get a NoSuchFieldException
    // This because you'll need to use DyeColor instead.

    static Map<String, ItemStack> WOOL_COLORS;
    static Map<String, ItemStack> CARPET_COLORS;
    static Set<String> ALL_COLORS;

    static {
        NormalizedBlockColorStorage storage1;
        NormalizedBlockColorStorage storage2;

        try {
            org.bukkit.Material.class.getField("WOOL");
            // If the server is NOT legacy, this will throw a NoSuchFieldException

            storage1 = new LegacyBlockStoredColors();
            storage2 = new LegacyBlockStoredColors();

        } catch (NoSuchFieldException e) {
            storage1 = new ModernBlockStoredColors();
            storage2 = new ModernBlockStoredColors();
        }

        storage1._init("Wool");
        storage2._init("Carpet");

        WOOL_COLORS = storage1.AllColors;
        CARPET_COLORS = storage2.AllColors;

        ALL_COLORS = WOOL_COLORS.keySet();
    }

    @Nullable
    public static ItemStack toWool(String color) {
        return WOOL_COLORS.get(color);
    }

    @Nullable
    public static ItemStack toCarpet(String color) {
        return CARPET_COLORS.get(color);
    }

    public static Set<String> getAllColors() {
        return ALL_COLORS;
    }
}
