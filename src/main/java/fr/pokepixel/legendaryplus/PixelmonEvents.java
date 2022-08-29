package fr.pokepixel.legendaryplus;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import fr.pokepixel.legendaryplus.config.LPConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static fr.pokepixel.legendaryplus.utils.ChatColor.translateAlternateColorCodes;
import static fr.pokepixel.legendaryplus.utils.GsonUtils.replaceLatest;
import static fr.pokepixel.legendaryplus.utils.GsonUtils.replaceOne;

public class PixelmonEvents {


    @SubscribeEvent
    public void onPixelmonSpawn(SpawnEvent event) {
        final Entity entity = event.action.getOrCreateEntity();
        if (entity instanceof PixelmonEntity) {
            final Entity causeEntity = event.action.spawnLocation.cause;
            if (!(causeEntity instanceof ServerPlayerEntity)) {
                return;
            }
            ServerPlayerEntity playermp = (ServerPlayerEntity) causeEntity;
            PixelmonEntity pixelmon = (PixelmonEntity) entity;
            boolean blacklist = true;
            String[] list = LPConfig.Config.entityblacklist;
            List<String> list2 = Lists.newArrayList(list);
            for (String s : list2) {
                if (s.equalsIgnoreCase(String.valueOf(pixelmon.getName()))) {
                    blacklist = false;
                }
            }
            if (blacklist && pixelmon.isLegendary() && !pixelmon.hasOwner() && !pixelmon.isBossPokemon()) {
                Date date = new Date();
                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = pixelmon.getUniqueID();
                String state = LPConfig.Lang.alive.toString();
                int limit = LPConfig.Config.limitleg.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastlegendary", info, limit);
                if (LPConfig.Config.allplayers.get()) {
                    if (pixelmon.getEntityWorld().getClosestPlayer(pixelmon, 500) != null) {
                        String keytransform1 = translateAlternateColorCodes('&', LPConfig.Lang.messagetoallplayers.toString())
                                .replace("{legendname}", pixelmon.getLocalizedName())
                                .replace((CharSequence) "{player}", (CharSequence) playermp.getName());
                        playermp.server.getPlayerList().getPlayers().forEach(entityPlayerMP -> entityPlayerMP.sendMessage(new StringTextComponent(keytransform1), uuid));
                    }
                }
                if (LPConfig.Config.msgplayer.get()) {
                    if (pixelmon.getEntityWorld().getClosestPlayer(pixelmon, 500) != null) {
                        String keytransform1 = LPConfig.Lang.messagetotheplayer.toString()
                                .replace("{legendname}", pixelmon.getLocalizedName())
                                .replace((CharSequence) "{player}", (CharSequence) playermp.getName());
                        playermp.sendMessage(new StringTextComponent(translateAlternateColorCodes('&', keytransform1)), uuid);
                    }
                }
            }
            if (blacklist && PixelmonSpecies.getUltraBeasts().contains(pixelmon.getSpecies()) && !pixelmon.hasOwner() && !pixelmon.isBossPokemon()) {
                Date date = new Date();

                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = pixelmon.getUniqueID();
                String state = LPConfig.Lang.alive.toString();
                int limit = LPConfig.Config.limitub.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastultrabeast", info, limit);
            }
            if (pixelmon.getPokemon().isShiny() && !PixelmonSpecies.getLegendaries().contains(pixelmon.getSpecies()) && !PixelmonSpecies.getUltraBeasts().contains(pixelmon.getSpecies()) && !pixelmon.isBossPokemon()) {
                Date date = new Date();
                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = pixelmon.getUniqueID();
                String state = LPConfig.Lang.alive.toString();
                int limit = LPConfig.Config.limitshiny.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastshiny", info, limit);
            }
            if (pixelmon.isBossPokemon()){
                Date date = new Date();
                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = pixelmon.getUniqueID();
                String state = LPConfig.Lang.alive.toString();
                int limit = LPConfig.Config.limitboss.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastboss", info, limit);
            }
        }
    }

    @SubscribeEvent
    public void onCapture(CaptureEvent.SuccessfulCapture event) {
        PixelmonEntity pokemon = event.getPokemon();
        //System.out.println("[onCaptureLP] uuid = " + pokemon.getUniqueID() );
        if (PixelmonSpecies.getLegendaries().contains(pokemon.getSpecies())){
            String state = LPConfig.Lang.captured.toString();
            replaceOne("lastlegendary", String.valueOf(event.player.getName()),state,event.getPokemon().getUniqueID());
        }else if (PixelmonSpecies.getUltraBeasts().contains(pokemon.getSpecies())){
            String state = LPConfig.Lang.captured.toString();
            replaceOne("lastultrabeast", String.valueOf(event.player.getName()),state,pokemon.getUniqueID());
        }else if (event.getPokemon().getPokemon().isShiny()){
            String state = LPConfig.Lang.captured.toString();
            replaceOne("lastshiny", String.valueOf(event.player.getName()),state,pokemon.getUniqueID());
        }
    }

    @SubscribeEvent
    public void onKill(BeatWildPixelmonEvent event) {
        PixelmonEntity pokemon = (PixelmonEntity) event.wpp.getEntity();
        if (PixelmonSpecies.getLegendaries().contains(pokemon.getSpecies())){
            String state = LPConfig.Lang.defeated.toString();
            replaceOne("lastlegendary", String.valueOf(event.player.getName()),state,pokemon.getUniqueID());
        }else if (PixelmonSpecies.getUltraBeasts().contains(pokemon.getSpecies())){

            String state = LPConfig.Lang.defeated.toString() ;
            replaceOne("lastultrabeast", String.valueOf(event.player.getName()),state,pokemon.getUniqueID());
        }else if (pokemon.getPokemon().isShiny()){

            String state = LPConfig.Lang.defeated.toString() ;
            replaceOne("lastshiny", String.valueOf(event.player.getName()),state,pokemon.getUniqueID());
        }else if (pokemon.isBossPokemon()){

            String state = LPConfig.Lang.defeated.toString() ;
            replaceOne("lastboss", String.valueOf(event.player.getName()),state,pokemon.getUniqueID());
        }
    }
}
