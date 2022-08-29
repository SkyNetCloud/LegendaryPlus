package fr.pokepixel.legendaryplus;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.pixelmonmod.pixelmon.Pixelmon;
import fr.pokepixel.legendaryplus.command.LPCommands;
import fr.pokepixel.legendaryplus.config.LPConfig;
import net.minecraft.command.CommandSource;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
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


    public static List<String> legendaries = Lists.newArrayList();


    //public static LegendaryPlus INSTANCE;

    public LegendaryPlus() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, LPConfig.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LPConfig.CONFIG_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
    }




    private void setup(final FMLCommonSetupEvent event) {
        Pixelmon.EVENT_BUS.register(new PixelmonEvents());
    }

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
        LPCommands.UltrabestInfo.register(commandDispatcher);
        LPCommands.LegendaryPlusReloadCmd.register(commandDispatcher);
        LPCommands.LegendInfos.register(commandDispatcher);
        LPCommands.LastShinyCms.register(commandDispatcher);
        LPCommands.LastUBCmd.register(commandDispatcher);
        LPCommands.LastLegendCmd.register(commandDispatcher);
        LPCommands.LastBossCmd.register(commandDispatcher);
        LPCommands.ModInfoCmd.register(commandDispatcher);

    }
}
