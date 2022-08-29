package fr.pokepixel.legendaryplus.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.pokepixel.legendaryplus.utils.PermissionUtil;
import fr.pokepixel.legendaryplus.utils.TextUtil;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

public class LPCommands {


    public static class LegendaryPlusInfo {
        public static LiteralArgumentBuilder<CommandSource> getCommand() {
            return Commands.literal("lpinfo").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.admin.info")).executes(cc -> {
                cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("You're running LegendaryPlus version 9.0.0").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);
                return 1;
            });
        }
    }

    public static class LegendaryPlusReloadCmd {
        public static LiteralArgumentBuilder<CommandSource> getCommand() {
            return Commands.literal("reload").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.admin.reload")).executes(cc -> {
                cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                return 1;
            });
        }
    }


    public static class LastBossCmd {
            public static LiteralArgumentBuilder<CommandSource> getCommand() {
                return Commands.literal("lplastboss").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.user.base")).executes(cc -> {
                    cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                    return 1;
                });
            }
    }
    public static class LastLegendCmd {
            public static LiteralArgumentBuilder<CommandSource> getCommand() {
                return Commands.literal("lplastlen").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.user.base")).executes(cc -> {
                    cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                    return 1;
                });
            }
    }


    public static class LastShinyCms {
            public static LiteralArgumentBuilder<CommandSource> getCommand() {
                return Commands.literal("lplastshiny").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.user.base")).executes(cc -> {
                    cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                    return 1;
                });
            }
    }

    public static class LastUBCmd {
            public static LiteralArgumentBuilder<CommandSource> getCommand() {
                return Commands.literal("lplastub").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.user.base")).executes(cc -> {
                    cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                    return 1;
                });
            }
    }

    public static class LegendInfos {
            public static LiteralArgumentBuilder<CommandSource> getCommand() {
                return Commands.literal("lpleninfo").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.user.base")).executes(cc -> {
                    cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                    return 1;
                });
            }
    }



    public static class UltrabestInfo {

            public static LiteralArgumentBuilder<CommandSource> getCommand() {
                return Commands.literal("lpubinfo").requires(cs -> PermissionUtil.checkPermAsPlayer(cs, "lp.user.base")).executes(cc -> {
                    cc.getSource().sendFeedback(TextUtil.getMessagePrefix().appendSibling(new StringTextComponent("Reloading LegendaryPlus please check your consoles for any errors").setStyle(Style.EMPTY.setColor(TextUtil.INFOTEXT))), false);

                    return 1;
                });
            }
    }

}

