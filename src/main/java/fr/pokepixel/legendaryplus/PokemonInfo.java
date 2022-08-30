package fr.pokepixel.legendaryplus;

import com.google.common.base.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.UUID;

public class PokemonInfo {

    private final List<Info> infoList;

    public PokemonInfo(List<Info> infoList){
        this.infoList = infoList;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public static class Info{
        private String name;
        private String state;

        private String player;

        private final UUID uuid;
        private long ms;

        //#TODO  Find a Replacement for String player system cuz it doesn't work
        public Info(String name, long ms, UUID uuid, String state, String player){

            this.name = name;
            this.ms = ms;
            this.uuid = uuid;
            this.state = state;
            this.player = player;
        }

        public String getName() {
            return name;
        }

        public long getMs() {
            return ms;
        }

        public UUID getUuid() {
            return uuid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

    }
}
