package io.github.cruciblemc.vitatempus.teste;

import io.github.cruciblemc.vitatempus.core.BukkitPacketDeliver;
import io.github.cruciblemc.vitatempus.extra.BossBarColor;
import io.github.cruciblemc.vitatempus.extra.BossBarType;
import io.github.cruciblemc.vitatempus.packets.ActionBar;
import io.github.cruciblemc.vitatempus.packets.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Teste3 {

    private static final UUID uuid = UUID.randomUUID();

    public static void onInit(Plugin plugin, BukkitPacketDeliver packetDeliver){

        new BukkitRunnable(){

            @Override
            public void run() {
                BossBar bossBar = BossBar.of("BossBar Testando", BossBarType.NOTCHED_20, BossBarColor.BLUE, (float) Math.random(), true, uuid);
                packetDeliver.broadcast(bossBar);
            }

        }.runTaskTimer(plugin, 0,40);
    }

}
