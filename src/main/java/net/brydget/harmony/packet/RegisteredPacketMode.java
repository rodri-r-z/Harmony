package net.brydget.harmony.packet;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

public class RegisteredPacketMode {

    static ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    /**
     * Adds a standard listener to the protocol manager.
     *
     * @param  listener  the standard packet listener to add
     */
    public void addStandardListener(StandardPacketListener listener) {
        protocolManager.addPacketListener(listener);
    }

    /**
     * Removes a standard listener from the protocol manager.
     *
     * @param  listener   the standard packet listener to be removed
     */
    public void removeStandardListener(StandardPacketListener listener) {
        protocolManager.removePacketListener(listener);
    }

    /**
     * Get the ProtocolManager instance.
     *
     * @return         	the ProtocolManager instance
     */
    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    /**
     * Sends the provided packet to the specified player.
     *
     * @param  packet  the packet to be sent
     * @param  player  the player to whom the packet will be sent
     */
    public void forwardPacket(PacketContainer packet, Player player) {
        protocolManager.sendServerPacket(player, packet);
    }

    /**
     * Sends a packet to all players.
     *
     * @param  packet  the packet to be forwarded
     */
    public void forwardPacket(PacketContainer packet) {
        protocolManager.broadcastServerPacket(packet);
    }

    /**
     * Sends a packet to all players.
     *
     * @param  packet  the packet to be forwarded
     */
    public void broadcastServerPacket(PacketContainer packet) {
        forwardPacket(packet);
    }

    /**
     * Sends a packet to all players.
     *
     * @param  packet  the packet to be forwarded
     */
    public void sendPacket(PacketContainer packet) {
        forwardPacket(packet);
    }

    /**
     * Sends a packet to all players.
     *
     * @param  packet  the packet to be forwarded
     */
    public void sendServerPacket(PacketContainer packet) {
        forwardPacket(packet);
    }

    /**
     * Sends the provided packet to the specified player.
     *
     * @param  packet  the packet to be sent
     * @param  player  the player to whom the packet will be sent
     */
    public void sendServerPacket(PacketContainer packet, Player player) {
        forwardPacket(packet, player);
    }
    /**
     * Sends the provided packet to the specified player.
     *
     * @param  packet  the packet to be sent
     * @param  player  the player to whom the packet will be sent
     */

    public void sendPacket(PacketContainer packet, Player player) {
        forwardPacket(packet, player);
    }
}
