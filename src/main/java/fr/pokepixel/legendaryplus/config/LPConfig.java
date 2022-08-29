package fr.pokepixel.legendaryplus.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;


public class LPConfig {


    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final Config CONFIG;
    private static final Splitter DOT_SPLITTER = Splitter.on(".");


    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public static class Lang {

        public static final String CATEGORY_LANG = "lang";
        public static String legendlist = "The legendary is {legendname}, and the nearest playerg is {player} in the biome {biome}.";
        public static String ultrabeastlist = "The Ultrabeast is {ubname}, and the nearest player is {player} in the biome {biome}.";
        public static String lastpoke = "{x}) Name : {pokemon} - Date : {days} day(s), {hours} hour(s), {minutes} minute(s) ago. [{state}] {playerph}";
        public static String titletrad = "A {legendname} has spawned around you {player}!";
        public static String messagetotheplayer = "A {legendname} has spawned around you {player}!";
        public static String messagetoallplayers = "A {legendname} has spawned around {player}!";
        //State
        public static String alive = "Alive";
        public static String captured = "Captured";
        public static String defeated = "Defeated";
        public static String despawned = "Despawned";

    }




    public static class Config {

        private static final String CATEGORY = "general";

        public static String[] entityblacklist = new String[]{"empty"};

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklist;

        public static ForgeConfigSpec.BooleanValue titlemsg;
        public static ForgeConfigSpec.BooleanValue msgplayer;
        public static ForgeConfigSpec.BooleanValue allplayers;
        public static ForgeConfigSpec.IntValue limitleg;
        public static ForgeConfigSpec.IntValue limitub;
        public static ForgeConfigSpec.IntValue limitshiny;
        public static ForgeConfigSpec.IntValue limitboss;


        public Config(ForgeConfigSpec.Builder builder) {

            builder.push(CATEGORY);
            blacklist = builder.comment("Add each legendary you dont want to see appear in /lastlegend").defineListAllowEmpty(split("blacklist"), blacklistSupplier(entityblacklist), LPConfig::isString);


            titlemsg = builder.comment("Set to true to display a title & subtitle on the player which is the nearest of a legendary").define("titlemsg", false);
            msgplayer = builder.comment("Set to true to send a message in tchat to the player which is the nearest of the legendary").define("msgplayer", false);
            allplayers = builder.comment("Set to true to send the msgplayer to all players instead of only which is the nearest of the legendary").define("allplayers", false);

            limitleg = builder.comment("Limit of legendary that will be displayed in /lastlegend").defineInRange("limitleg", 3, 1, 10);
            limitshiny = builder.comment("Limit of shiny that will be displayed in /lastshiny").defineInRange("limitleg", 3, 1, 10);
            limitub = builder.comment("Limit of ultrabeast that will be displayed in /lastultrabeast").defineInRange("limitleg", 3, 1, 10);
            limitboss = builder.comment("Limit of boss that will be displayed in /lastboss").defineInRange("limitleg", 3, 1, 10);

        }

    }

    private static boolean isString(Object o) {

        return o instanceof String;

    }

    private static Supplier<List<? extends String>> blacklistSupplier(String[] list) {

        return () -> Arrays.asList(list);

    }

    private static List<String> split(String path) {

        return Lists.newArrayList(DOT_SPLITTER.split(path));

    }


}
