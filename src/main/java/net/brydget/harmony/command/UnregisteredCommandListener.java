package net.brydget.harmony.command;

import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
public abstract class UnregisteredCommandListener {

    String commandName;

    /**
     * @deprecated (since v2.2. Scheduled for removal in v3.0)
     * */
    @Deprecated
    protected UnregisteredCommandListener(String name) {
        commandName = name;
    }

    // Handler for unregistered commands listening for chat messages
    // Using packets

    public abstract boolean whenExecute(String[] args, Player player, PacketEvent packetEvent);

    public String getCommandName() {
        return commandName;
    }

}
