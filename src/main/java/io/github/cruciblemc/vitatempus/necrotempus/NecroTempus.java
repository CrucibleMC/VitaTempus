package io.github.cruciblemc.vitatempus.necrotempus;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import io.github.cruciblemc.vitatempus.VitaTempus;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NecroTempus implements PluginMessageListener, Listener {

    private static NecroTempus instance;

    public static NecroTempus getInstance(){
        return instance != null ? instance : (instance = new NecroTempus());
    }

    private NecroTempus(){
        Bukkit.getPluginManager().registerEvents(this, VitaTempus.getInstance());
    }

    private static final ConcurrentHashMap<UUID, String> versions = new ConcurrentHashMap<>();

    public String getVersion(Player player){
        return versions.get(player.getUniqueId());
    }

    public boolean hasNecroTempus(Player player){
        return getVersion(player) != null;
    }

    @SneakyThrows
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        ByteBuffer byteBuffer = ByteBuffer.wrap(message);

        short size =    byteBuffer.getShort(1);
        byte[] data =   Arrays.copyOfRange(message, 3, size);
        NBTContainer nbtContainer = new NBTContainer(new ByteArrayInputStream(data));


        if(nbtContainer.hasTag("version")){
            String version = nbtContainer.getString("version");
            versions.put(player.getUniqueId(), version.substring(0, Math.min(64, version.length() - 1)));
        }

    }

    public void onInit(Plugin plugin, String channel){
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, this);
    }

    public void onDisable(Plugin plugin, String channel){
        Bukkit.getServer().getMessenger().unregisterIncomingPluginChannel(plugin, channel, this);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        versions.remove(event.getPlayer().getUniqueId());
    }

}
