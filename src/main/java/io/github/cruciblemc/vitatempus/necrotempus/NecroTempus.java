package io.github.cruciblemc.vitatempus.necrotempus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class NecroTempus implements PluginMessageListener {

    private static NecroTempus instance;

    public static NecroTempus getInstance(){
        return instance != null ? instance : (instance = new NecroTempus());
    }

    private NecroTempus(){}

    private static final HashMap<UUID, String> versions = new HashMap<>();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        System.out.println(channel);
        System.out.println(player.getName());
        System.out.println(Arrays.toString(message));
    }

    public void onInit(Plugin plugin, String channel){
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, this);
    }

    public void onDisable(Plugin plugin, String channel){
        Bukkit.getServer().getMessenger().unregisterIncomingPluginChannel(plugin, channel, this);
    }

}
