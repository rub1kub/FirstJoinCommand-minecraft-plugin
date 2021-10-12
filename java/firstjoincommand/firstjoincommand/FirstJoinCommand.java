package firstjoincommand.firstjoincommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FirstJoinCommand extends JavaPlugin {
    final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        File file = new File("plugins/FirstJoinCommand");
        file.mkdir();

        File config = new File("plugins/FirstJoinCommand/config.yml");
        if(!config.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        this.console.sendMessage(this.getMessageColor("\n \n&8[]=====[&aEnabling FirstJoinCommand&8]=====[]\n&8| &cInformation:\n&8|   &cName: &7FirstJoinCommand\n&8|   &cDeveloper: &7R_u_B_i_K / SlimeStudio\n&8|   &cVersion: &7" + this.getVersion() + "\n&8[]======================================[]&r\n "));

        if(!getConfig().getBoolean("settings.firsconsolecommand.enable") && !getConfig().getBoolean("settings.firsplayercommand.enable") && !getConfig().getBoolean("settings.firstjoinmessages.enable")){
            this.console.sendMessage(this.getMessageColor("\n \n&8[]=====[&eFirstJoinCommand WARNING&8]=====[]\n&8| &cInformation:\n&8|   &7Все выполняемые команды отключены!\n   &7Посмотрите config.yml!\n&8[]======================================[]&r\n "));
        }

        getServer().getPluginManager().registerEvents(new MainEvent(this), this);
        getCommand("firstjoincommand").setExecutor(new fjc(this));
    }

    @Override
    public void onDisable() {
        this.console.sendMessage(this.getMessageColor("\n \n&8[]=====[&aDisabling FirstJoinCommand&8]=====[]\n&8| &cInformation:\n&8|   &cName: &7FirstJoinCommand\n&8|   &cDeveloper: &7R_u_B_i_K / SlimeStudio\n&8|   &cVersion: &7" + this.getVersion() + "\n&8[]======================================[]&r\n "));
    }

    public String getVersion() {
        return this.version;
    }

    public String getMessageColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}

