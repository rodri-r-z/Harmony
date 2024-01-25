package dev.rodriigo.minecraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class DynamicCommandListener extends Command {

    String commandName;

    public DynamicCommandListener(String name) {
        super(name);
        commandName = name;
    }

    // Handler for unregistered commands listening for chat messages
    // Using packets
    public abstract boolean whenExecute(String[] args, CommandSender commandSender);

    public String getCommandName() {
        return commandName;
    }

    public abstract List<String> tabComplete(String[] args, CommandSender commandSender);

}
