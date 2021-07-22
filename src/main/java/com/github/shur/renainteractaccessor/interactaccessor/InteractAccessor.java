package com.github.shur.renainteractaccessor.interactaccessor;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public final class InteractAccessor {

    /* package */ final Player player;

    /* package */ String id = null;

    /* package */ long expirationTicks = 0;

    /* package */ ResponseListener onResponse = (s) -> {};

    /* package */ CancelListener onCancel = () -> {};

    public InteractAccessor(Player player) {
        this.player = player;
    }

    public InteractAccessor id(String id) {
        this.id = id;
        return this;
    }

    public InteractAccessor expirationTicks(long ticks) {
        this.expirationTicks = ticks;
        return this;
    }

    public InteractAccessor onResponse(ResponseListener onResponse) {
        if (onResponse == null) throw new IllegalArgumentException("onResponse must not be null");

        this.onResponse = onResponse;
        return this;
    }

    public InteractAccessor onCancel(CancelListener onCancel) {
        if (onCancel == null) throw new IllegalArgumentException("onCancel must not be null");

        this.onCancel = onCancel;
        return this;
    }

    public interface ResponseListener {
        void run(Entity interactedEntity);
    }

    public interface CancelListener {
        void run();
    }

}
