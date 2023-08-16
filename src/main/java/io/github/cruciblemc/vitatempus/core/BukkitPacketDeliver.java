package io.github.cruciblemc.vitatempus.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BukkitPacketDeliver {

    private final Plugin plugin;
    private final String channel;

    private BukkitPacketDeliver(Plugin plugin, String channel){
        this.plugin = plugin;
        this.channel = channel;
        onInit();
    }

    public static BukkitPacketDeliver register(Plugin plugin, String channel){
        return new BukkitPacketDeliver(plugin, channel);
    }

    public void deliverTo(Player player, EncodedMessage encodedMessage){
        Bukkit.getScheduler().runTask(plugin, () -> {
            player.sendPluginMessage(plugin, channel, encodedMessage.getTransformedData());
        });
    }

    public void broadcast(EncodedMessage encodedMessage){
        for(Player player : Bukkit.getOnlinePlayers()){
            deliverTo(player, encodedMessage);
        }
    }

    public void deliverTo(Player player, MessagePacket messagePacket){
        EncodedMessage encodedMessage = MessagePacketEncoder.encode(messagePacket);

        Bukkit.getScheduler().runTask(plugin, () -> {
            player.sendPluginMessage(plugin, channel, encodedMessage.getTransformedData());
        });
    }

    public void broadcast(MessagePacket messagePacket){

        EncodedMessage encodedMessage = MessagePacketEncoder.encode(messagePacket);

        for(Player player : Bukkit.getOnlinePlayers()){
            deliverTo(player, encodedMessage);
        }
    }

    public void onInit(){
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin, channel);
    }

    public void onDisable(){
        Bukkit.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, channel);
    }


}
