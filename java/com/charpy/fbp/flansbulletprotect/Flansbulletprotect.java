package com.charpy.fbp.flansbulletprotect;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Flansbulletprotect extends JavaPlugin {
    public WorldGuardPlugin worldGuard;

    @Override
    public void onEnable() {
        System.out.println(" ");
        System.out.println("§4================================================================ ");
        System.out.println(" ");
        System.out.println("§4FlansBulletProtect loaded successfully ");
        System.out.println(" ");
        System.out.println("§4================================================================");
        System.out.println(" ");
        worldGuard = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("§4FlansBulletProtect Shutting down");
    }
}
