package firstjoincommand.firstjoincommand;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MainEvent implements Listener {
    FirstJoinCommand plugin;

    public MainEvent(FirstJoinCommand main) {
        this.plugin = main;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if(!event.getPlayer().hasPlayedBefore()){
            if(plugin.getConfig().getBoolean("settings.firstjoinmessages.enable")){
                event.getPlayer().sendMessage(plugin.getConfig().getString("settings.firstjoinmessages.message").replace("&", "§")
                        .replace("{player}", event.getPlayer().getDisplayName()));
            }
            if(plugin.getConfig().getBoolean("settings.firstconsolecommand.enable")){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plugin.getConfig().getString("settings.firstconsolecommand.command").replace("&", "§").replace("{player}", event.getPlayer().getDisplayName()));
            }

            if(plugin.getConfig().getBoolean("settings.firstplayercommand.enable")){
                Bukkit.dispatchCommand(event.getPlayer(), plugin.getConfig().getString("settings.firstplayercommand.command").replace("&", "§").replace("{player}", event.getPlayer().getDisplayName()));
            }
        }
    }
}
