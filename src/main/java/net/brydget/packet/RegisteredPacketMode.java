package net.brydget.packet;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

public class RegisteredPacketMode {

    static ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public void addStandardListener(StandardPacketListener listener) {
        protocolManager.addPacketListener(listener);
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public void forwardPacket(PacketContainer packet, Player player) {
        protocolManager.sendServerPacket(player, packet);
    }

    public void forwardPacket(PacketContainer packet) {
        protocolManager.broadcastServerPacket(packet);
    }

    public void broadcastServerPacket(PacketContainer packet) {
        forwardPacket(packet);
    }

    public void sendPacket(PacketContainer packet) {
        forwardPacket(packet);
    }

    public void sendServerPacket(PacketContainer packet) {
        forwardPacket(packet);
    }

    public void sendServerPacket(PacketContainer packet, Player player) {
        forwardPacket(packet, player);
    }

    public void sendPacket(PacketContainer packet, Player player) {
        forwardPacket(packet, player);
    }
}
