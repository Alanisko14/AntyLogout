package x_alanisko_x.antylogout;

import x_alanisko_x.antylogout.model.ConfigText;
import x_alanisko_x.antylogout.model.RandomColor;
import x_alanisko_x.antylogout.service.AntyLogoutService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Iterator;

public final class AntyLogout extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        new ConfigText(this);
        Iterator<KeyedBossBar> bossBars = Bukkit.getBossBars();
        while(bossBars.hasNext()){
            KeyedBossBar keyedBossBar = bossBars.next();
            if(keyedBossBar.getKey().getKey().contains("antylogout")){
                keyedBossBar.removeAll();
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/bossbar remove "+keyedBossBar.getKey());
        }
        new AntyLogoutService();
        getServer().getPluginManager().registerEvents(new AntyLogoutService(), this);
        this.getCommand("antylogout").setExecutor(new x_alanisko_x.antylogout.commands.AntyLogout());
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&6[&c&lAnty&f&lLogout&6] &aCreated by &l&"+ RandomColor.getRandom()  + "x_Alanisko_x"));
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&6[&c&lAnty&f&lLogout&6] &aSuccessful loaded"));
    }

    @Override
    public void onDisable() {
    }
}
