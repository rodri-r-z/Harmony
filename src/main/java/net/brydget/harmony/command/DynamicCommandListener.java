package net.brydget.harmony.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class DynamicCommandListener extends Command {

    String commandName;

    public DynamicCommandListener(String name) {
        super(name);
        commandName = name;
    }

    public DynamicCommandListener(String name, List<String> aliases) {
        super(name, null, null, aliases);
        commandName = name;
    }

    public DynamicCommandListener(String name, String description, List<String> aliases) {
        super(name, description, null, aliases);
        commandName = name;
    }

    public DynamicCommandListener(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
        commandName = name;
    }

    // Handler for unregistered commands listening for chat messages
    // Using packets
    public abstract boolean whenExecute(String[] args, CommandSender commandSender);

    /**
     * Get the command name.
     *
     * @return         	the command name
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Include completions to tab when using commands
     *
     * @param  args           The current command arguments
     * @param  commandSender  Who executed the command
     * @return                The completions
     */
    public abstract List<String> tabComplete(String[] args, CommandSender commandSender);

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return whenExecute(strings, commandSender);
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, String s, String[] strings) {
        return tabComplete(strings, commandSender);
    }

}
