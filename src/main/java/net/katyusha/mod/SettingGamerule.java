package net.katyusha.mod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.GameRules;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

@Mod(modid = SettingGamerule.MODID, name = SettingGamerule.NAME, version = SettingGamerule.VERSION, acceptableRemoteVersions = "*")
public class SettingGamerule
{
    public static final String MODID = "settinggamerule";
    public static final String NAME = "Setting Gamerule";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        // logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @EventHandler
    public void serverStarted(FMLServerStartedEvent event) {

        int worldCount = FMLCommonHandler.instance().getMinecraftServerInstance().worlds.length;
        logger.info("SettingGamerule successfully loaded");
        logger.info("Found " + worldCount + " worlds");

        for (int i = 0; i < worldCount; i++) {
            GameRules gameRules = FMLCommonHandler.instance().getMinecraftServerInstance().worlds[i].getGameRules();
            String worldName = FMLCommonHandler.instance().getMinecraftServerInstance().worlds[i].getWorldInfo().getWorldName();

            gameRules.setOrCreateGameRule("doFireTick", Boolean.toString(false));
            gameRules.setOrCreateGameRule("keepInventory", Boolean.toString(true));
            gameRules.setOrCreateGameRule("mobGriefing", Boolean.toString(false));

            logger.info("Gamerule of world " + worldName + " have successfully set");
        }
    }
}
