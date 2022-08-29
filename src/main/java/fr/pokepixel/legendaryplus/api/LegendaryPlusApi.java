package fr.pokepixel.legendaryplus.api;

import fr.pokepixel.legendaryplus.PokemonInfo;
import fr.pokepixel.legendaryplus.config.LPConfig;

import java.util.UUID;

import static fr.pokepixel.legendaryplus.utils.GsonUtils.replaceLatest;

public class LegendaryPlusApi {

    public static void addLegendary(String name, long millis, UUID uuid) {
        int limit = LPConfig.Config.limitleg.get();
        String state = LPConfig.Lang.alive;
        replaceLatest("lastlegendary",new PokemonInfo.Info(name,millis,uuid,state,""),limit);
    }

    public static void addUB(String name, long millis, UUID uuid) {
        int limit = LPConfig.Config.limitub.get();
        String state = LPConfig.Lang.alive;
        replaceLatest("lastultrabeast",new PokemonInfo.Info(name,millis,uuid,state,""),limit);
    }

    public static void addShiny(String name, long millis, UUID uuid) {
        int limit = LPConfig.Config.limitshiny.get();
        String state = LPConfig.Lang.alive;
        replaceLatest("lastshiny",new PokemonInfo.Info(name,millis,uuid,state,""),limit);
    }

    public static void addBoss(String name, long millis, UUID uuid) {
        int limit = LPConfig.Config.limitboss.get();
        String state = LPConfig.Lang.alive;
        replaceLatest("lastboss",new PokemonInfo.Info(name,millis,uuid,state,""),limit);
    }
}
