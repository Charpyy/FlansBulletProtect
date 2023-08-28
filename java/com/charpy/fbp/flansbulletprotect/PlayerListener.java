package com.charpy.fbp.flansbulletprotect;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import flansapi.handlers.BulletHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        final BulletHandler bHandler = new BulletHandler();
        final String owner = bHandler.getBulletOwner(e.getDamager().getUniqueId().toString());
        if (owner == null) {
            return;
        }
        final Player ownerPlayer = Bukkit.getPlayer(owner);
        if (ownerPlayer == null || !ownerPlayer.isOnline()) {
            return;
        }
        final WorldGuardPlugin worldGuard = (WorldGuardPlugin)Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (worldGuard != null) {
            final ProtectedRegion spawnRegion = worldGuard.getRegionManager(ownerPlayer.getWorld()).getRegion("spawn");
            if (spawnRegion != null && spawnRegion.contains(ownerPlayer.getLocation().getBlockX(), ownerPlayer.getLocation().getBlockY(), ownerPlayer.getLocation().getBlockZ())) {
                ownerPlayer.sendMessage("§8» §4FlansBulletProtect §8« §cYou can't shoot on player at spawn");
                e.setCancelled(true);
            }
        }
        else {
            System.out.println("WorldGuard plugin not found. Some features may not work.");
        }
    }
}
