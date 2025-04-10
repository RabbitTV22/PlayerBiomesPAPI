package me.rabbittv.PlayerBiomes;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.block.Biome;

public class PlayerBiomes extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "RabbitTV";
    }

    @Override
    public String getIdentifier() {
        return "playerbiomes";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) return "Unknown";
        Location loc = player.getLocation();
        Biome biome = loc.getWorld().getBiome(loc);
        switch (identifier.toLowerCase()) {
            case "biome_name": {
                return getBiomeFormatted(player);
            }
            case "biome_raw": {
                return biome.name();
            }
            case "biome_name_fancy": {
                return getFancyBiomeName(player);
            }
        }
        return "RabbitTV";

    }
    public static String getBiomeFormatted(OfflinePlayer player) {
        if (player.getPlayer() == null) return "Unknown";

        Location loc = player.getPlayer().getLocation();
        Biome biome = loc.getWorld().getBiome(loc);
        String fullKey = biome.getKey().toString();

        int lastSlash = fullKey.lastIndexOf("/");
        int lastColon = fullKey.lastIndexOf(":");

        String rawName;
        if (lastSlash != -1) {
            rawName = fullKey.substring(lastSlash + 1);
        } else if (lastColon != -1) {
            rawName = fullKey.substring(lastColon + 1);
        } else {
            rawName = fullKey;
        }

        rawName = rawName.replaceAll("[_.]", " ");
        StringBuilder formatted = new StringBuilder();
        for (String word : rawName.split(" ")) {
            if (!word.isEmpty()) {
                formatted.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return formatted.toString().trim();
    }

    public static String getFancyBiomeName(OfflinePlayer player) {
        if (player.getPlayer() == null) return "Unknown";

        Location loc = player.getPlayer().getLocation();
        Biome biome = loc.getWorld().getBiome(loc);
        String fullKey = biome.getKey().toString();

        int lastSlash = fullKey.lastIndexOf("/");
        int lastColon = fullKey.lastIndexOf(":");

        String rawName;
        if (lastSlash != -1) {
            rawName = fullKey.substring(lastSlash + 1);
        } else if (lastColon != -1) {
            rawName = fullKey.substring(lastColon + 1);
        } else {
            rawName = fullKey;
        }

        rawName = rawName.replaceAll("[_.]", " ").toLowerCase();
        StringBuilder fancy = new StringBuilder();
        String fancyFont = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀѕᴛᴜᴠᴡхʏᴢ";

        for (char c : rawName.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                fancy.append(fancyFont.charAt(c - 'a'));
            } else {
                fancy.append(c);
            }
        }

        return fancy.toString().trim();
    }


}

