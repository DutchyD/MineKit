package dev.dutchy.minekit.command;

import dev.dutchy.minekit.MineKit;
import dev.dutchy.minekit.utility.PluginDevelopmentPhase;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class MineKitCommand implements CommandExecutor
{

    private final PluginDescriptionFile description = JavaPlugin.getPlugin(MineKit.class).getDescription();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments)
    {
        if (arguments.length != 1)
        {
            return false;
        }

        if (arguments[0].equalsIgnoreCase("help"))
        {
            sender.sendMessage("MineKit has the following commands: ");
            sender.sendMessage("    /minekit help - Shows this help message.");
            sender.sendMessage("    /minekit version - Shows the current version of MineKit.");
            return true;
        }

        if (arguments[0].equalsIgnoreCase("version"))
        {
            sender.sendMessage("You are currently using " + this.description.getFullName() + ".");
            sender.sendMessage("The current Plugin Development Stage is " + MineKit.DEVELOPMENT_PHASE + ".");
            if (!PluginDevelopmentPhase.PRODUCTION.equals(MineKit.DEVELOPMENT_PHASE))
            {
                sender.sendMessage(ChatColor.RED + "This version should not be used in a production environment.");
            }

            return true;
        }

        return false;
    }
}
