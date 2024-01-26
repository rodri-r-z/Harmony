package net.brydget.packet;

import com.comphenix.protocol.wrappers.AdventureComponentConverter;
import net.kyori.adventure.text.Component;

public abstract class PaperClientBoundPacketConverter {

    // Converts the Paper Adventure components
    // into JSON Components

    public static String convertToJSON(Object component) {
        return AdventureComponentConverter.fromComponent((Component) component).getJson();
    }

}
