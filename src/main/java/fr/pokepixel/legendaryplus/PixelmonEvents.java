package fr.pokepixel.legendaryplus;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import fr.pokepixel.legendaryplus.config.LPConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static fr.pokepixel.legendaryplus.utils.ChatColor.translateAlternateColorCodes;
import static fr.pokepixel.legendaryplus.utils.GsonUtils.*;

public class PixelmonEvents {

    private static long elapsedDays,elapsedHours,elapsedMinutes;

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
                if (s.equalsIgnoreCase(pixelmon.getLocalizedName())) {
                    blacklist = false;
                }
            }
            if (blacklist && pixelmon.isLegendary() && !pixelmon.hasOwner() && !pixelmon.isBossPokemon()) {
                Date date = new Date();
                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = UUID.randomUUID();
                String state = LPConfig.Config.alive.get();
                int limit = LPConfig.Config.limitleg.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastlegendary", info, limit);
                if (LPConfig.Config.allplayers.get()) {
                    if (pixelmon.getEntityWorld().getClosestPlayer(pixelmon, 500) != null) {
                        String keytransform1 = translateAlternateColorCodes('&', LPConfig.Config.messagetoallplayers.toString())
                                .replace("{legendname}", pixelmon.getLocalizedName())
                                .replace("{player}", (CharSequence) playermp.getName());
                        playermp.server.getPlayerList().getPlayers().forEach(entityPlayerMP -> entityPlayerMP.sendMessage(new StringTextComponent(keytransform1), uuid));
                    }
                }
                if (LPConfig.Config.msgplayer.get()) {
                    if (pixelmon.getEntityWorld().getClosestPlayer(pixelmon, 500) != null) {
                        String keytransform1 = LPConfig.Config.messagetotheplayer.get()
                                .replace("{legendname}", pixelmon.getLocalizedName())
                                .replace("{player}", (CharSequence) playermp.getName());
                        playermp.sendMessage(new StringTextComponent(translateAlternateColorCodes('&', keytransform1)), uuid);
                    }
                }
            }
            if (blacklist && pixelmon.getSpecies().isLegendary() && !pixelmon.hasOwner() && !pixelmon.isBossPokemon()) {
                Date date = new Date();

                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = UUID.randomUUID();
                String state = LPConfig.Config.alive.get();
                int limit = LPConfig.Config.limitub.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastultrabeast", info, limit);
            }
            if (pixelmon.getPokemon().isShiny() && !pixelmon.getSpecies().isLegendary() && !pixelmon.getSpecies().isUltraBeast() && !pixelmon.isBossPokemon()) {
                Date date = new Date();
                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = UUID.randomUUID();
                String state = LPConfig.Config.alive.get();
                int limit = LPConfig.Config.limitshiny.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastshiny", info, limit);
            }
            if (pixelmon.isBossPokemon()){
                Date date = new Date();
                String name = pixelmon.getLocalizedName();
                long ms = date.getTime();
                UUID uuid = UUID.randomUUID();
                String state = LPConfig.Config.alive.get();
                int limit = LPConfig.Config.limitboss.get();
                PokemonInfo.Info info = new PokemonInfo.Info(name, ms, uuid, state, "");
                replaceLatest("lastboss", info, limit);
            }
        }
    }

    @SubscribeEvent
    public void onCapture(CaptureEvent.SuccessfulCapture event) {
        PixelmonEntity pokemon = event.getPokemon();
        if (pokemon.getSpecies().isLegendary()){
            String state = LPConfig.Config.captured.toString();
            replaceOne("lastlegendary", "{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString(),state,UUID.randomUUID());
        }else if (pokemon.getSpecies().isUltraBeast()){
            String state = LPConfig.Config.captured.toString();
            replaceOne("lastultrabeast", "{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString(),state,pokemon.getUniqueID());
        }else if (event.getPokemon().getPokemon().isShiny()){
            String state = LPConfig.Config.captured.toString();
            replaceOne("lastshiny", "{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString() ,state,UUID.randomUUID());
        }
    }

    @SubscribeEvent
    public void onKill(BeatWildPixelmonEvent event) {
        PixelmonEntity pokemon = (PixelmonEntity) event.wpp.getEntity();
        if (pokemon.getSpecies().isLegendary()){
            String state = LPConfig.Config.defeated.toString();
            replaceOne("lastlegendary","{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString(),state, pokemon.getUniqueID());
        }else if (pokemon.getSpecies().isUltraBeast()){
            String state = LPConfig.Config.defeated.toString() ;
            replaceOne("lastultrabeast", "{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString().toString(),state, pokemon.getUniqueID());
        }else if (pokemon.getPokemon().isShiny()){
            String state = LPConfig.Config.defeated.toString() ;
            replaceOne("lastshiny", "{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString(),state, pokemon.getUniqueID());
        }else if (pokemon.isBossPokemon()){
            String state = LPConfig.Config.defeated.toString() ;
            replaceOne("lastboss", "{player}".replace((CharSequence) "{player}", (CharSequence) event.player.getName()).toString(),state, pokemon.getUniqueID());
        }
    }

}
