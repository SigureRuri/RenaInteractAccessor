package com.github.shur.renainteractaccessor.listener;

import com.github.shur.renainteractaccessor.RenaInteractAccessor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(final PlayerQuitEvent event) {
        RenaInteractAccessor.getInteractAccessorManager().cancel(event.getPlayer());
    }

}
