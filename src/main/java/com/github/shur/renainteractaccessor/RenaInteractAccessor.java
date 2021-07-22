package com.github.shur.renainteractaccessor;

import com.github.shur.renainteractaccessor.interactaccessor.InteractAccessorManager;
import com.github.shur.renainteractaccessor.listener.InteractListener;
import com.github.shur.renainteractaccessor.listener.PluginListener;
import com.github.shur.renainteractaccessor.listener.QuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RenaInteractAccessor extends JavaPlugin {

    private static boolean enabled = false;

    private static JavaPlugin plugin = null;

    private static final InteractAccessorManager interactAccessorManager = new InteractAccessorManager();

    public static void onEnable(JavaPlugin plugin) {
        if (enabled) return;
        enabled = true;
        RenaInteractAccessor.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(new InteractListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new QuitListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PluginListener(), plugin);
    }

    public static boolean isPluginEnabled() {
        return enabled;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static InteractAccessorManager getInteractAccessorManager() {
        return interactAccessorManager;
    }

    @Override
    public void onEnable() {
        onEnable(this);
    }

}
