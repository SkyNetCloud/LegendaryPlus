package fr.pokepixel.legendaryplus;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.pixelmonmod.pixelmon.Pixelmon;
import fr.pokepixel.legendaryplus.command.LPCommands;
import fr.pokepixel.legendaryplus.config.LPConfig;
import fr.pokepixel.legendaryplus.utils.PermissionManager;
import net.minecraft.command.CommandSource;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Mod("legendaryplus")
public class LegendaryPlus {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "legendaryplus";
    public static final String MOD_NAME = "LegendaryPlus";
    public static final String VERSION = "9.0.0";

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static ForgeConfigSpec config;
    public static ForgeConfigSpec lang;
    public static File directory;
    public static File lastlegendary;
    public static File lastshiny;
    public static File lastboss;


    //public static List<String> legendaries = Lists.newArrayList();


    //public static LegendaryPlus INSTANCE;

    public LegendaryPlus() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, LPConfig.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LPConfig.CONFIG_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onCommandRegistry(final RegisterCommandsEvent event) {
        PermissionManager.registerBasePermissions();
        event.getDispatcher().register(LPCommands.LegendaryPlusInfo.getCommand());
        event.getDispatcher().register(LPCommands.LegendaryPlusReloadCmd.getCommand());
        event.getDispatcher().register(LPCommands.LastBossCmd.getCommand());
        event.getDispatcher().register(LPCommands.LastLegendCmd.getCommand());
        event.getDispatcher().register(LPCommands.LastShinyCms.getCommand());
        event.getDispatcher().register(LPCommands.LastUBCmd.getCommand());
        event.getDispatcher().register(LPCommands.LegendInfos.getCommand());
        event.getDispatcher().register(LPCommands.UltrabestInfo.getCommand());
    }


    private void setup(final FMLCommonSetupEvent event) {
        Pixelmon.EVENT_BUS.register(new PixelmonEvents());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {


    }
}
