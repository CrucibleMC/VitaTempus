package io.github.cruciblemc.vitatempus.teste;

import io.github.cruciblemc.vitatempus.core.BukkitPacketDeliver;
import io.github.cruciblemc.vitatempus.core.MessagePacket;
import io.github.cruciblemc.vitatempus.packets.ActionBar;
import io.github.cruciblemc.vitatempus.packets.Title;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Teste2 {

    public static void onInit(Plugin plugin, BukkitPacketDeliver packetDeliver){

        ActionBar actionBar = ActionBar.of("Olá senhor");

        new BukkitRunnable(){

            @Override
            public void run() {
                packetDeliver.broadcast(actionBar);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        packetDeliver.broadcast(actionBar.remove());
                    }
                }.runTaskLater(plugin, 50);

            }

        }.runTaskTimer(plugin, 0,200);
    }

}
