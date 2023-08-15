package io.github.cruciblemc.vitatempus.teste;

import io.github.cruciblemc.vitatempus.core.BukkitPacketDeliver;
import io.github.cruciblemc.vitatempus.extra.BossBarColor;
import io.github.cruciblemc.vitatempus.extra.BossBarType;
import io.github.cruciblemc.vitatempus.packets.BossBar;
import io.github.cruciblemc.vitatempus.packets.PlayerTab;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Teste4 {

    public static void onInit(Plugin plugin, BukkitPacketDeliver packetDeliver){

        new BukkitRunnable(){

            @Override
            public void run() {
                PlayerTab playerTab = PlayerTab.of("Header", "Footer");
                packetDeliver.broadcast(playerTab);
            }

        }.runTaskTimer(plugin, 0,40);
    }

}
