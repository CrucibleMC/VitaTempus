package io.github.cruciblemc.vitatempus;

import io.github.cruciblemc.vitatempus.core.BukkitPacketDeliver;
import io.github.cruciblemc.vitatempus.packets.Title;
import io.github.cruciblemc.vitatempus.teste.Teste1;
import io.github.cruciblemc.vitatempus.teste.Teste2;
import io.github.cruciblemc.vitatempus.teste.Teste3;
import io.github.cruciblemc.vitatempus.teste.Teste4;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class VitaTempus extends JavaPlugin {

    private static final String NecroTempusChannel = "necrotempus:main";

    BukkitPacketDeliver bukkitPacketDeliver;

    private static VitaTempus instance;

    public static VitaTempus getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        bukkitPacketDeliver = BukkitPacketDeliver.register(this, NecroTempusChannel);

        Teste1.onInit(this, bukkitPacketDeliver);
        Teste2.onInit(this, bukkitPacketDeliver);
        Teste3.onInit(this, bukkitPacketDeliver);
        Teste4.onInit(this, bukkitPacketDeliver);

    }

    @Override
    public void onDisable() {
        bukkitPacketDeliver.onDisable();
    }


}
