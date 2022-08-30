package fr.pokepixel.legendaryplus.utils;

import com.google.common.collect.Lists;
import fr.pokepixel.legendaryplus.LegendaryPlus;
import fr.pokepixel.legendaryplus.PokemonInfo;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.system.CallbackI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static fr.pokepixel.legendaryplus.LegendaryPlus.gson;

public class GsonUtils {

    public static void writeJson(String filename, PokemonInfo object){
        try (PrintWriter writer = new PrintWriter(new File(LegendaryPlus.directory, filename + ".json"),"UTF-8")) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            System.out.println("Error 01");
            e.printStackTrace();
        }
    }

    public static Optional<PokemonInfo> readJson(String filename){
        try (Reader reader = new InputStreamReader(Files.newInputStream(new File(LegendaryPlus.directory, filename + ".json").toPath()), StandardCharsets.UTF_8)) {
            return Optional.of(gson.fromJson(reader, PokemonInfo.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static List<PokemonInfo.Info> listPoke(String file){
        List<PokemonInfo.Info> infoList = Lists.newArrayList();
        readJson(file).ifPresent(pokemonInfo -> {
            if (pokemonInfo.getInfoList().size()>0){
                infoList.addAll(pokemonInfo.getInfoList());
            }
        });
        Collections.reverse(infoList);
        return infoList;
    }

    public static void replaceLatest(String file, PokemonInfo.Info info, int limit){
        List<PokemonInfo.Info> list = listPoke(file);
        while (list.size()>=limit){
            list.remove(0);
        }
        list.add(info);
        writeJson(file,new PokemonInfo(list));
    }

    public static void replaceOne(String file,String state, UUID uuid){
        List<PokemonInfo.Info> list = listPoke(file);
        list.forEach(info1 -> {
            //System.out.println("find poke");
            if (info1.getUuid().equals(uuid)){
                //System.out.println("replace player");
                //info1.setPlayer(player);
                info1.setState(state);
            }
        });
        writeJson(file,new PokemonInfo(list));
    }
}
