package com.github.shur.renainteractaccessor.listener;

import com.github.shur.renainteractaccessor.RenaInteractAccessor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class PluginListener implements Listener {
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDisable(final PluginDisableEvent event) {
        if (!RenaInteractAccessor.getPlugin().getName().equals(event.getPlugin().getName())) return;

        RenaInteractAccessor.getInteractAccessorManager().cancelAll();
    }
    
}
