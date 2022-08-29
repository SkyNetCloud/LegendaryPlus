package fr.pokepixel.legendaryplus.utils;

import fr.pokepixel.legendaryplus.LegendaryPlus;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class PermissionManager {
    private static final String BASE_USER = "lp.user.";
    private static final String BASE_ADMIN = "lp.admin.";
    public static final String BASE_PERMISSION = "lp.user.base";
    public static final String ADMIN_INFO = "lp.admin.lpinfo";
    public static final String ADMIN_RELOAD = "lp.admin.lpreload";

    public static void registerBasePermissions() {
        registerCommandPermission("lp.user.base");
        registerCommandPermission("lp.admin.lpinfo");
        registerCommandPermission("lp.admin.lpreload");
    }

    public static void registerCommandPermission(final String s) {
        if (s == null || s.isEmpty()) {
            LegendaryPlus.LOGGER.error("Trying to register a permission node failed, please check any configs for null/empty Configs");
            return;
        }
        PermissionAPI.registerNode(s, DefaultPermissionLevel.NONE, s);
    }
}
