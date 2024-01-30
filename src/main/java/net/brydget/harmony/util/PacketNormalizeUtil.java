package net.brydget.harmony.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.AdventureComponentConverter;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.brydget.harmony.annotation.Nullable;
import net.brydget.harmony.packet.PaperClientBoundPacketConverter;
import net.brydget.harmony.packet.RegisteredPacketMode;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

public abstract class PacketNormalizeUtil {

    /*
    * Packets received by ProtocolLib cannot have the information
    * the final user is looking for
    * (i.e. SYSTEM_CHAT packets got strings null when the server is using PaperMC)
    * */

    /**
     * Get the JSON content from SYSTEM_CHAT and CHAT packets
     *
     * @param  packet   the packet to be checked
     * @return          the clientBoundSystemChatPacket or null
     */
    @Nullable
    public static String fromSystemChatPacket(PacketContainer packet) {
        // Check if the packet is a SYSTEM_CHAT packet
        if (packet.getType().equals(PacketType.Play.Server.SYSTEM_CHAT))  {
            // Retrieved from GitHub: https://github.com/dmulloy2/ProtocolLib/issues/2330
            // Paper adds two more fields to the NMS ClientBoundSystemChatPacket
            // That makes the strings to be null on the packet, but they are not null on the modifier
            // Located into the Adventure Chat NMS Field added by Paper
            try {
                StructureModifier<Object> adventureModifier = packet.getModifier().withType(AdventureComponentConverter.getComponentClass());

                if (!adventureModifier.getFields().isEmpty()) {
                    // Only true if paper is present
                    // Now we get the message
                    // We get the component as an object, we need to cast it
                    // Reached this part, we're sure it's an adventure component
                    Object componentObject = adventureModifier.read(0);
                    String clientBoundSystemChatPacket = PaperClientBoundPacketConverter.convertToJSON(
                            componentObject
                    );
                    // Set to null the component so paper use strings instead of adventure components
                    adventureModifier.write(0, null);
                    packet.getStrings().write(0, clientBoundSystemChatPacket);
                    return clientBoundSystemChatPacket;
                }
            } catch (Throwable ignored) {
                // Ignore if Adventure is unavailable
            }
        }
        // SYSTEM_CHAT packet was added on 1.19, versions below it use the "CHAT" packet
        // The CHAT packet doesn't come with Strings, but ChatComponents
        // We need to convert the ChatComponents to Strings
        if (packet.getType().equals(PacketType.Play.Server.CHAT)) {
            return packet.getChatComponents().read(0).getJson();
        }
        if (packet.getStrings().size() == 0) return null;
        return packet.getStrings().read(0);
    }

    /**
     * Sends a JSON component to a player
     *
     * @param  JSON   the JSON component to send
     * @param  player the player to send the JSON component to
     */
    public static void sendComponent(String JSON, Player player) {
        // Sends a JSON component to a player
        // To ensure backwards compatibility, we'll use packets
        // Instead of player#spigot#sendMessage
        PacketContainer packet;
        try {
            // If the server is 1.19+, it's better to use SYSTEM_CHAT packet instead
            // of CHAT packet
            packet = new PacketContainer(PacketType.Play.Server.SYSTEM_CHAT);
            packet.getStrings().write(0, JSON);
        } catch (Exception ignored) {
            // If something went wrong, we'll catch an IllegalArgumentException
            // This means the server is below 1.19
            packet = new PacketContainer(PacketType.Play.Server.CHAT);
            packet.getChatComponents().write(
                    0,
                    WrappedChatComponent.fromJson(JSON)
            );
        }
        // Send the packet to the player
        RegisteredPacketMode.getProtocolManager().sendServerPacket(player, packet);
    }

    /**
     * Sends a BaseComponent to a player
     *
     * @param  component  the BaseComponent to send
     * @param  player     the player to send the BaseComponent to
     */
    public static void sendComponent(BaseComponent component, Player player) {
        // Sends a BaseComponent to a player
        sendComponent(
                ComponentSerializer.toString(component),
                player
        );
    }

    /**
     * Sends an array of BaseComponents to a player
     *
     * @param  components  the array of BaseComponents to send
     * @param  player      the player to send the components to
     */
    public static void sendComponent(BaseComponent[] components, Player player) {
        // Sends an array of BaseComponents to a player
        sendComponent(
                ComponentSerializer.toString(components),
                player
        );
    }

    /**
     * Sends a JSON message to the specified CommandSender.
     *
     * @param  JSON    the JSON message to send
     * @param  entity  the CommandSender to send the message to
     */
    public static void sendComponent(String JSON, CommandSender entity) {
        entity.sendMessage(TextComponent.toLegacyText(ComponentSerializer.parse(JSON)));
    }

    /**
     * Sends a BaseComponent to a player
     *
     * @param  component	description of parameter
     * @param  entity	    description of parameter
     */
    public static void sendComponent(BaseComponent component, CommandSender entity) {
        // Sends a BaseComponent to a player
        sendComponent(
                ComponentSerializer.toString(component),
                entity
        );
    }

    /**
     * Sends an array of BaseComponents to a player
     *
     * @param  components   array of BaseComponents to be sent
     * @param  entity       the CommandSender entity to receive the components
     */
    public static void sendComponent(BaseComponent[] components, CommandSender entity) {
        // Sends an array of BaseComponents to a player
        sendComponent(
                ComponentSerializer.toString(components),
                entity
        );
    }

}
