package io.github.cruciblemc.vitatempus.teste;

import io.github.cruciblemc.vitatempus.core.BukkitPacketDeliver;
import io.github.cruciblemc.vitatempus.packets.Title;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Teste1 {

    public static void onInit(Plugin plugin, BukkitPacketDeliver packetDeliver){

        Title title = Title.of("Olá jogador", "teste NBT");

        new BukkitRunnable(){

            @Override
            public void run() {
                packetDeliver.broadcast(title);
            }

        }.runTaskTimer(plugin, 0,200);
    }

}
