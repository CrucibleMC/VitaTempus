package io.github.cruciblemc.vitatempus.packets;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import io.github.cruciblemc.vitatempus.core.MessagePacket;
import io.github.cruciblemc.vitatempus.extra.BossBarColor;
import io.github.cruciblemc.vitatempus.extra.BossBarType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
public class BossBar extends MessagePacket {

    private String text;
    private String type;
    private String color;
    private float percentage;
    private boolean visible;

    @Getter
    private final UUID uuid;

    @Override
    public byte packetID() {
        return 1;
    }

    private BossBar(String text, BossBarType type, BossBarColor color, float percentage, boolean visible, UUID uuid){

        this.text = text;
        this.type = type.getType();

        if(color == BossBarColor.LAZY){
            this.color = "$" + color.intValue();
        }else{
            this.color = color.getIdentifier();
        }

        this.percentage = percentage;
        this.visible = visible;
        this.uuid = uuid;

    }

    private BossBar(String text, BossBarType type, BossBarColor color, float percentage, boolean visible){
        this(text, type, color, percentage, visible, UUID.randomUUID());
    }

    public static BossBar of(String text, BossBarType type, BossBarColor color, float percentage, boolean visible){
        return new BossBar(text, type, color, percentage, visible);
    }

    public static BossBar of(String text, BossBarType type, BossBarColor color, float percentage, boolean visible, UUID uuid){
        return new BossBar(text, type, color, percentage, visible, uuid);
    }

    public MessagePacket remove(){
        return new RemoveBossBar(uuid);
    }


    @Override
    public ReadWriteNBT writeCompound() {

        ReadWriteNBT nbtCompound = NBT.createNBTObject();

        nbtCompound.setString("packetType", getPacketType());

        nbtCompound.setString("text", text);
        nbtCompound.setString("type", type);
        nbtCompound.setFloat("percentage", percentage);
        nbtCompound.setBoolean("isVisible", visible);
        nbtCompound.setString("uuid", uuid.toString());
        nbtCompound.setString("color", color);

        return nbtCompound;
    }

    public static class RemoveBossBar extends BossBar{

        private RemoveBossBar(UUID uuid) {
            super("VITATEMPUS", BossBarType.FLAT, BossBarColor.PINK, 1F, true, uuid);
        }

        @Override
        public String getPacketType() {
            return "remove";
        }
    }

}
