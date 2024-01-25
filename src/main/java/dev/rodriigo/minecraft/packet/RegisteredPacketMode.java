package dev.rodriigo.minecraft.packet;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class RegisteredPacketMode {

    static ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public void addStandardListener(StandardPacketListener listener) {
        protocolManager.addPacketListener(listener);
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
