package fr.pokepixel.legendaryplus.utils;

import net.minecraft.util.text.*;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern HEXPATTERN;
    private static final String SPLITPATTERN = "((?=\\{#[a-fA-F0-9]{6}}))";
    public static String noPermission;
    public static final Color BLUE;
    public static final Color ORANGE;
    public static final String BLUE_PARSE = "{#00AFFC}";
    public static final String ORANGE_PARSE = "{#FF6700}";
    public static final Color INFOTEXT;
    public static final String VERSION = "1.0-Beta";

    private static final IFormattableTextComponent PLUGIN_PREFIX;
    private static final IFormattableTextComponent MESSAGE_PREFIX;

    public static ITextComponent parseHexCodes(final String text, final boolean removeItalics) {
        if (text == null) {
            return null;
        }
        final StringTextComponent comp = new StringTextComponent("");
        final String[] temp = text.split("((?=\\{#[a-fA-F0-9]{6}}))");
        final Matcher[] m = new Matcher[1];
        final AtomicReference<Color>[] color = new AtomicReference[]{null};
        final StringTextComponent stringTextComponent = null;
        Arrays.stream(temp).forEach(s -> {
            m[0] = TextUtil.HEXPATTERN.matcher(s);
            if (m[0].find()) {
                color[0].set(Color.fromHex(m[0].group(1)));
                s = m[0].replaceAll("");
                if (removeItalics) {
                    stringTextComponent.appendSibling((ITextComponent) new StringTextComponent(s).setStyle(Style.EMPTY.setColor(color[0].get()).setItalic(Boolean.valueOf(false))));
                } else {
                    stringTextComponent.appendSibling((ITextComponent) new StringTextComponent(s).setStyle(Style.EMPTY.setColor(color[0].get())));
                }
            } else {
                stringTextComponent.appendSibling((ITextComponent) new StringTextComponent(s));
            }
            return;
        });
        return (ITextComponent) comp;
    }

    public static String toString(final String[] a) {
        if (a == null) {
            return "null";
        }
        final int iMax = a.length - 1;
        if (iMax == -1) {
            return "";
        }
        final StringBuilder b = new StringBuilder();
        int i = 0;
        while (true) {
            b.append(a[i]);
            if (i == iMax) {
                break;
            }
            b.append(" ");
            ++i;
        }
        return b.toString();
    }

    public static IFormattableTextComponent getPluginPrefix() {
        return TextUtil.PLUGIN_PREFIX.deepCopy();
    }

    public static IFormattableTextComponent getMessagePrefix() {
        return TextUtil.MESSAGE_PREFIX.deepCopy();
    }

    static {
        HEXPATTERN = Pattern.compile("\\{(#[a-fA-F0-9]{6})}");
        TextUtil.noPermission = TextFormatting.RED + "("+ TextFormatting.DARK_RED + "!" + TextFormatting.RED + ")" + TextFormatting.YELLOW + " You lack the permission to use this";
        BLUE = Color.fromHex("#00AFFC");
        ORANGE = Color.fromHex("#FF6700");
        INFOTEXT = Color.fromTextFormatting(TextFormatting.GRAY);
        PLUGIN_PREFIX = new StringTextComponent(TextFormatting.YELLOW+ "Legendary" + TextFormatting.BLUE +"Plus");
        MESSAGE_PREFIX = getPluginPrefix().appendSibling(new StringTextComponent(" » ").setStyle(Style.EMPTY.setColor(TextUtil.ORANGE)));
    }
}
