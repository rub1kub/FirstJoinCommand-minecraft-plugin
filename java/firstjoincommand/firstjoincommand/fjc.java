package firstjoincommand.firstjoincommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class fjc implements CommandExecutor, TabCompleter {
    FirstJoinCommand plugin;

    public fjc(FirstJoinCommand main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        String aboutmessages = "\n \n&8[]=====[&aFirstJoinCommand&8]=====[]\n&8| &cInformation:\n&8|   &cName: &7FirstJoinCommand\n&8|   &cDeveloper: &7R_u_B_i_K / SlimeStudio\n&8|   &cVersion: &7" + plugin.getVersion() + "\n&8[]======================================[]&r\n ";

        if(!sender.hasPermission("fjc.settings")){
            sender.sendMessage(plugin.getConfig().getString("settings.messages.notperm").replace("&", "§"));
            return true;
        }

        else if(args.length == 0){
            sender.sendMessage(aboutmessages.replace("&", "§"));
            return true;
        }

        switch (args[0].toLowerCase()){
            case "reload":
                plugin.reloadConfig();
                sender.sendMessage(plugin.getConfig().getString("settings.messages.reload").replace("&", "§"));
                break;
            case "setfirstconsolecommand":
                if (args.length >= 1){
                    plugin.getConfig().set("settings.firstconsolecommand.command", args[1]);
                    sender.sendMessage(plugin.getConfig().getString("settings.messages.changed").replace("&", "§"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else
                    sender.sendMessage(plugin.getConfig().getString("settings.messages.notarg").replace("&", "§"));
                break;
            case "setplayercommand":
                if (args.length >= 1){
                    plugin.getConfig().set("settings.firstplayercommand.command", args[1]);
                    sender.sendMessage(plugin.getConfig().getString("settings.messages.changed").replace("&", "§"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                }
                break;
            default:
                sender.sendMessage(aboutmessages.replace("&", "§"));
                break;
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
        java.util.List<String> list = Arrays.asList("reload", "setfirstconsolecommand", "setplayercommand");
        if (args.length == 0){
            return list;
        }
        return list.stream().filter(line -> line.startsWith(args[0])).collect(Collectors.toList());
    }
}
