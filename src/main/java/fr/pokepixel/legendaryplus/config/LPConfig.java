package fr.pokepixel.legendaryplus.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
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
    public static void bake() {
        LPConfig.bake();
    }


    public static class Config {

        private static final String CATEGORY = "general".toUpperCase();

        public static final String CATEGORY_LANG = "lang".toUpperCase();

        public static ForgeConfigSpec.ConfigValue<String> legendlist;
        public static ForgeConfigSpec.ConfigValue<String> ultrabeastlist;
        public static ForgeConfigSpec.ConfigValue<String> lastpoke;
        public static ForgeConfigSpec.ConfigValue<String> titletrad;
        public static ForgeConfigSpec.ConfigValue<String> messagetotheplayer;
        public static ForgeConfigSpec.ConfigValue<String> messagetoallplayers;

        //State
        public static ForgeConfigSpec.ConfigValue<String> alive ;
        public static ForgeConfigSpec.ConfigValue<String> captured;
        public static ForgeConfigSpec.ConfigValue<String> defeated;

        public static ForgeConfigSpec.ConfigValue<String> despawned;

        public static ForgeConfigSpec.ConfigValue<String> playerph;

        public static String[] entityblacklist = new String[]{"empty"};

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklist;

        public static ForgeConfigSpec.BooleanValue titlemsg;
        public static ForgeConfigSpec.BooleanValue msgplayer;
        public static ForgeConfigSpec.BooleanValue allplayers;
        public static ForgeConfigSpec.IntValue limitleg;
        public static ForgeConfigSpec.IntValue limitub;
        public static ForgeConfigSpec.IntValue limitshiny;
        public static ForgeConfigSpec.IntValue limitboss;

        public static ForgeConfigSpec.IntValue limitmythical;


        public Config(ForgeConfigSpec.Builder builder) {

            builder.push(CATEGORY);
            blacklist = builder.comment("Add each legendary you dont want to see appear in /lastlegend").defineListAllowEmpty(split("blacklist"), blacklistSupplier(entityblacklist), LPConfig::isString);


            titlemsg = builder.comment("Set to true to display a title & subtitle on the player which is the nearest of a legendary").define("titlemsg", false);
            msgplayer = builder.comment("Set to true to send a message in tchat to the player which is the nearest of the legendary").define("msgplayer", false);
            allplayers = builder.comment("Set to true to send the msgplayer to all players instead of only which is the nearest of the legendary").define("allplayers", false);

            limitleg = builder.comment("Limit of legendary that will be displayed in /lastlegend").defineInRange("limitleg", 3, 1, 10);
            limitshiny = builder.comment("Limit of shiny that will be displayed in /lastshiny").defineInRange("limitshiny", 3, 1, 10);
            limitub = builder.comment("Limit of ultrabeast that will be displayed in /lastultrabeast").defineInRange("limitub", 3, 1, 10);
            limitboss = builder.comment("Limit of boss that will be displayed in /lastboss").defineInRange("limitboss", 3, 1, 10);
            limitmythical = builder.comment("Limit of mythical that will be displayed in /lastmythical").defineInRange("limitmythical", 3, 1, 10);

            builder.pop();

            builder.push(CATEGORY_LANG);

            legendlist = builder.comment("The message that's send out when a legendary spawn").define("legendlist", "The legendary is {legendname}, and the nearest playerg is {player} in the biome {biome}.");
            ultrabeastlist = builder.comment("The message that's send out when a Ultrabeast spawn").define("legendlist", "The legendary is {legendname}, and the nearest playerg is {player} in the biome {biome}.");
            lastpoke = builder.comment("Last pokemon").define("lastpoke", "The legendary is {legendname}, and the nearest playerg is {player} in the biome {biome}.");
            titletrad = builder.comment("Title Rad").define("titletrad", "A {legendname} has spawned around you {player}!");
            messagetotheplayer = builder.comment("Message to the player").define("messagetotheplayer", "A {legendname} has spawned around you {player}!");
            messagetoallplayers = builder.comment("Message to all player").define("messagetoallplayers", "A {legendname} has spawned around {player}!");

            alive = builder.comment("Alive Text").define("alive", "Alive");
            captured = builder.comment("Captured Text").define("captured", "Captured");
            defeated = builder.comment("Defeated Text").define("defeated", "Defeated");
            despawned = builder.comment("Despawned Text").define("despawned", "Despawned");

            playerph = builder.comment("Player placeholder").define("playerplaceholder", "- {player}");


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
