package com.github.shur.renainteractaccessor.interactaccessor;

import com.github.shur.renainteractaccessor.RenaInteractAccessor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InteractAccessorManager {

    private final Map<UUID, InteractAccessor> accessors = new HashMap<>();

    public void register(final InteractAccessor interactAccessor) {
        if (interactAccessor == null) throw new IllegalArgumentException("interactAccessor must not be null");

        final Player player = interactAccessor.player;
        final UUID uuid = player.getUniqueId();

        if (accessors.containsKey(uuid)) accessors.get(uuid).onCancel.run();

        accessors.put(uuid, interactAccessor);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (accessors.containsKey(uuid) && accessors.get(uuid) == interactAccessor) {
                    InteractAccessorManager.this.cancel(uuid);
                }
            }
        }.runTaskLater(RenaInteractAccessor.getPlugin(), interactAccessor.expirationTicks);
    }

    public void response(final UUID uuid, final Entity interactedEntity) {
        if (uuid == null) throw new IllegalArgumentException("uuid must not be null");
        if (interactedEntity == null) throw  new IllegalArgumentException("interactedEntity must not be null");

        if (!accessors.containsKey(uuid)) return;

        final InteractAccessor accessor = accessors.get(uuid);
        accessor.onResponse.run(interactedEntity);

        accessors.remove(uuid);
    }

    public void response(final Player player, final Entity interactedEntity) {
        if (player == null) throw new IllegalArgumentException("player must not be null");
        if (interactedEntity == null) throw  new IllegalArgumentException("interactedEntity must not be null");

        response(player.getUniqueId(), interactedEntity);
    }

    public void cancel(final UUID uuid) {
        if (uuid == null) throw new IllegalArgumentException("player must not be null");

        if (!accessors.containsKey(uuid)) return;

        final InteractAccessor accessor = accessors.get(uuid);
        accessor.onCancel.run();

        accessors.remove(uuid);
    }

    public void cancel(final Player player) {
        if (player == null) throw new IllegalArgumentException("player must not be null");

        cancel(player.getUniqueId());
    }

    public void cancelAll() {
        accessors.keySet().forEach(this::cancel);
    }

    public boolean has(UUID uuid) {
        if (uuid == null) throw new IllegalArgumentException("player must not be null");

        return accessors.containsKey(uuid);
    }

    public boolean has(Player player) {
        if (player == null) throw new IllegalArgumentException("player must not be null");

        return has(player.getUniqueId());
    }

    public boolean has(UUID uuid, String id) {
        if (uuid == null) throw new IllegalArgumentException("uuid must not be null");
        if (id == null) throw new IllegalArgumentException("id must not be null");

        if (!accessors.containsKey(uuid)) return false;
        final String accessorsId = accessors.get(uuid).id;
        return accessorsId != null && accessorsId.equals(id);
    }

    public boolean has(Player player, String id) {
        if (player == null) throw new IllegalArgumentException("player must not be null");
        if (id == null) throw new IllegalArgumentException("id must not be null");

        return has(player.getUniqueId(), id);
    }

}
