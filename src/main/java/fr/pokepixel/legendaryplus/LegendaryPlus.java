package fr.pokepixel.legendaryplus;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.CommandDispatcher;
import com.pixelmonmod.pixelmon.Pixelmon;
import fr.pokepixel.legendaryplus.command.LPCommands;
import fr.pokepixel.legendaryplus.config.LPConfig;
import net.minecraft.command.CommandSource;
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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@SuppressWarnings("ALL")
@Mod("legendaryplus")
public class LegendaryPlus {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "legendaryplus";
    public static final String MOD_NAME = "LegendaryPlus";
    public static final String VERSION = "9.0.0";

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static File directory;

    public static File lastlegendary;
    public static File lastmythical;
    public static File lastultrabeast;
    public static File lastshiny;
    public static File lastboss;

    public LegendaryPlus() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, LPConfig.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LPConfig.CONFIG_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
        Pixelmon.EVENT_BUS.register(new PixelmonEvents());
    }




    private void setup(final FMLCommonSetupEvent event) {
        directory = new File(MOD_ID);
        directory.mkdir();

        lastlegendary = new File(directory.getPath(), "lastlegendary.json");
        boolean check = lastlegendary.exists();
        if (!check) {
            PrintWriter start;
            try {
                start = new PrintWriter(lastlegendary, "UTF-8");
                start.write(gson.toJson(new PokemonInfo(Lists.newArrayList())));
                start.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        lastultrabeast = new File(directory.getPath(), "lastultrabeast.json");
        check = lastultrabeast.exists();
        if (!check) {
            PrintWriter start;
            try {
                start = new PrintWriter(lastultrabeast, "UTF-8");
                start.write(gson.toJson(new PokemonInfo(Lists.newArrayList())));
                start.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        lastshiny = new File(directory.getPath(), "lastshiny.json");
        check = lastshiny.exists();
        if (!check) {
            PrintWriter start;
            try {
                start = new PrintWriter(lastshiny, "UTF-8");
                start.write(gson.toJson(new PokemonInfo(Lists.newArrayList())));
                start.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        lastboss = new File(directory.getPath(), "lastboss.json");
        check = lastboss.exists();
        if (!check) {
            PrintWriter start;
            try {
                start = new PrintWriter(lastboss, "UTF-8");
                start.write(gson.toJson(new PokemonInfo(Lists.newArrayList())));
                start.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        lastmythical = new File(directory.getPath(), "lastmythical.json");
        check = lastmythical.exists();
        if (!check) {
            PrintWriter start;
            try {
                start = new PrintWriter(lastmythical, "UTF-8");
                start.write(gson.toJson(new PokemonInfo(Lists.newArrayList())));
                start.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        LOGGER.info("Something");

    }

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();

    }
}
