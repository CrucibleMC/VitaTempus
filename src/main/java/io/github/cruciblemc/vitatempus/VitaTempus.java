package io.github.cruciblemc.vitatempus;

import io.github.cruciblemc.vitatempus.core.BukkitPacketDeliver;
import io.github.cruciblemc.vitatempus.necrotempus.NecroTempus;
import org.bukkit.plugin.java.JavaPlugin;

public final class VitaTempus extends JavaPlugin {

    private static final String NecroTempusChannel = "necrotempus:main";

    private BukkitPacketDeliver necroTempusPacketDeliver;

    private static VitaTempus instance;

    public static VitaTempus getInstance() {
        return instance;
    }

    public BukkitPacketDeliver getNecroTempusPacketDeliver() {
        return necroTempusPacketDeliver;
    }

    @Override
    public void onEnable() {
        instance = this;
        necroTempusPacketDeliver = BukkitPacketDeliver.register(this, NecroTempusChannel);
        NecroTempus.getInstance().onInit(this, NecroTempusChannel);
    }

    @Override
    public void onDisable() {
        necroTempusPacketDeliver.onDisable();
        NecroTempus.getInstance().onDisable(this, NecroTempusChannel);

    }


}
