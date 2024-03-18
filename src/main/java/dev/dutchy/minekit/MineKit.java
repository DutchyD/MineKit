package dev.dutchy.minekit;

import dev.dutchy.minekit.command.MineKitCommand;
import dev.dutchy.minekit.utility.PluginDevelopmentPhase;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class MineKit extends JavaPlugin
{

    public static final PluginDevelopmentPhase DEVELOPMENT_PHASE = PluginDevelopmentPhase.DEVELOPMENT;

    @Override
    public void onEnable()
    {
        final Logger logger = this.getLogger();

        logger.info(this.getDescription().getFullName() + " enabled.");

        if (DEVELOPMENT_PHASE.equals(PluginDevelopmentPhase.TESTING))
        {
            logger.warning("You are using a MineKit version in the testing phase.");
            logger.warning("This version should not be used in a production environment.");
        }
        else if (DEVELOPMENT_PHASE.equals(PluginDevelopmentPhase.DEVELOPMENT))
        {
            logger.warning("You are using a MineKit version in the development phase.");
            logger.warning("This version should not be used in a production environment.");
        }

        this.registerCommands();
    }

    @Override
    public void onDisable()
    {
        final Logger logger = this.getLogger();
        logger.warning(this.getDescription().getFullName() + " has been disabled.");
    }

    private void registerCommands()
    {
        final Logger logger = this.getLogger();
        logger.info("Registering necessary commands...");
        this.registerCommand("minekit", new MineKitCommand());
    }

    private void registerCommand(@NotNull String label, @NotNull CommandExecutor executor)
    {
        final Logger logger = this.getLogger();

        PluginCommand command = this.getCommand(label);

        if (command == null)
        {
            throw new IllegalStateException("Command " + label + " is null. Please register it in the plugin.yml file.");
        }

        command.setExecutor(executor);

        logger.info("Registered command " + label + ".");
    }
}
