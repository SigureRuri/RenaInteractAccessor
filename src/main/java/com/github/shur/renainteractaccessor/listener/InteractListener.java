package com.github.shur.renainteractaccessor.listener;

import com.github.shur.renainteractaccessor.RenaInteractAccessor;
import com.github.shur.renainteractaccessor.interactaccessor.InteractAccessorManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onInteractEntity(final PlayerInteractEntityEvent event) {
        final Player player = event.getPlayer();
        final InteractAccessorManager interactAccessorManager = RenaInteractAccessor.getInteractAccessorManager();
        if (!interactAccessorManager.has(player)) return;

        interactAccessorManager.response(player, event.getRightClicked());

        event.setCancelled(true);
    }

}
