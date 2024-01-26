package net.brydget.harmony.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class RealCommandListener implements CommandExecutor, TabExecutor {

    DynamicCommandListener listener;

    public RealCommandListener(DynamicCommandListener listener) {
        this.listener = listener;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return listener.whenExecute(strings, commandSender);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return listener.tabComplete(strings, commandSender);
    }
}
