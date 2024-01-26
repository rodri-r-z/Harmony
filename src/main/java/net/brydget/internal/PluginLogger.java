package net.brydget.internal;

import net.brydget.BackendPlugin;
import net.brydget.util.MessageColorFormatter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PluginLogger {
    static final BackendPlugin instance = BackendPlugin.getInstance();
    static final Logger baseLogger = instance.getLogger();
    static final CommandSender console = instance.getConsole();

    static final String CONSOLE_PREFIX = ChatColor.translateAlternateColorCodes('&', "&e["+instance.getProvidedPluginName()+"] ");
    static final String INFO_PREFIX = CONSOLE_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&9[Info]: &r");
    static final String WARN_PREFIX = CONSOLE_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&6[Warn]: &r");
    static final String ERROR_PREFIX = CONSOLE_PREFIX+ ChatColor.translateAlternateColorCodes('&', "&c[Err]: &r");


    public void info(String message) {
        message = MessageColorFormatter.colorize(message);
        console.sendMessage(INFO_PREFIX + message);
    }

    public void info(Supplier<String> messages) {
        info(messages.get());
    }

    public void info(Object message) {
        info(String.valueOf(message));
    }
    public void info(Object... message) {
        Arrays.stream(message).collect(Collectors.toList()).forEach(this::info);
    }

    public void warn(String message) {
        message = MessageColorFormatter.colorize(message);
        console.sendMessage(WARN_PREFIX + message);
    }

    public void warn(Supplier<String> messages) {
        warn(messages.get());
    }

    public void warn(Object message) {
        warn(String.valueOf(message));
    }
    public void warn(Object[] message) {
        Arrays.stream(message).collect(Collectors.toList()).forEach(this::warn);
    }

    public void warning(String message) {
        warn(message);
    }

    public void warning(Supplier<String> messages) {
        warn(messages);
    }
    public void warning(Object message) {
        warn(String.valueOf(message));
    }
    public void warning(Object[] message) {
        Arrays.stream(message).collect(Collectors.toList()).forEach(this::warn);
    }
    public void error(String message) {
        severe(message);
    }
    public void error(Object message) {
        severe(String.valueOf(message));
    }
    public void error(Object[] message) {
        Arrays.stream(message).collect(Collectors.toList()).forEach(this::error);
    }
    public void severe(Object[] message) {
        Arrays.stream(message).collect(Collectors.toList()).forEach(this::severe);
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
        message = MessageColorFormatter.colorize(message);
        console.sendMessage(ERROR_PREFIX + message);
    }

    public void severe(Supplier<String> messages) {
        severe(messages.get());
    }

    public void severe(Object messages) {
        severe(String.valueOf(messages));
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
