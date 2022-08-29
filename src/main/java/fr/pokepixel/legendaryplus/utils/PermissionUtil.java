package fr.pokepixel.legendaryplus.utils;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.server.permission.PermissionAPI;

public class PermissionUtil {

    public static boolean checkPermAsPlayer(final CommandSource cs, final String perm) {
        try {
            return checkPerm(cs.asPlayer(), perm);
        }
        catch (CommandSyntaxException e) {
            return isServer(cs);
        }
    }

    public static boolean checkPerm(final ServerPlayerEntity player, final String perm) {
        return player != null && perm != null && (perm.isEmpty() || PermissionAPI.hasPermission((PlayerEntity)player, perm) || player.hasPermissionLevel(4));
    }

    public static boolean isServer(final CommandSource cs) {
        return cs.getServer() == LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
    }
}
