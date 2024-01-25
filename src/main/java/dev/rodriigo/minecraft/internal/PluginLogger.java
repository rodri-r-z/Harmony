package dev.rodriigo.minecraft.internal;

import dev.rodriigo.minecraft.BackendPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PluginLogger {
    static final BackendPlugin instance = BackendPlugin.getInstance();
    static final Logger baseLogger = instance.getLogger();
    static final CommandSender console = instance.getConsole();

    static final String CONSOLE_PREFIX = "["+instance.getProvidedPluginName()+"] ";
    static final String INFO_PREFIX = CONSOLE_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&9[Info] ");
    static final String WARN_PREFIX = CONSOLE_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&6[Warn] ");
    static final String ERROR_PREFIX = CONSOLE_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&c[Err] ");


    public void info(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        console.sendMessage(INFO_PREFIX + message);
    }

    public void info(Supplier<String> messages) {
        console.sendMessage(INFO_PREFIX +
                ChatColor.translateAlternateColorCodes('&',
                        messages.get()
                )
        );
    }

    public void fine(String message) {
        baseLogger.fine(message);
    }

    public void fine(Supplier<String> messages) {
        baseLogger.fine(messages);
    }

    public void finer(String message) {
        baseLogger.finer(message);
    }

    public void finer(Supplier<String> messages) {
        baseLogger.finer(messages);
    }

    public void finest(String message) {
        baseLogger.finest(message);
    }

    public void finest(Supplier<String> messages) {
        baseLogger.finest(messages);
    }

    public void severe(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        console.sendMessage(ERROR_PREFIX + message);
    }

    public void warn(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        console.sendMessage(WARN_PREFIX + message);
    }

    public void warn(Supplier<String> messages) {
        console.sendMessage(WARN_PREFIX +
                ChatColor.translateAlternateColorCodes('&',
                        messages.get()
                )
        );
    }

    public void warning(String message) {
        warn(message);
    }

    public void warning(Supplier<String> messages) {
        warn(messages);
    }

    public void error(String message) {
        severe(message);
    }

    public void addHandler(Handler handler) {
        baseLogger.addHandler(handler);
    }

    public Filter getFilter() {
        return baseLogger.getFilter();
    }

    public Level getLevel() {
        return baseLogger.getLevel();
    }

    public boolean isLoggable(Level level) {
        return baseLogger.isLoggable(level);
    }

    public Handler[] getHandlers() {
        return baseLogger.getHandlers();
    }

    public ResourceBundle getResourceBundle() {
        return baseLogger.getResourceBundle();
    }

    public String getName() {
        return baseLogger.getName();
    }
}
