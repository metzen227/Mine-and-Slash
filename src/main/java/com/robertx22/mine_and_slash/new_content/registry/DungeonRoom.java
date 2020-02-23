package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.ResourceLocation;

public class DungeonRoom implements IWeighted {

    public ResourceLocation loc;
    public RoomType type;
    int weight = 1000;

    public DungeonRoom(String id, RoomType type) {
        this.loc = new ResourceLocation(Ref.MODID, "dun/" + type.id + "/" + id);
        this.type = type;
    }

    public DungeonRoom setTest() {
        isTest = true;
        return this;
    }

    boolean isTest = false;

    @Override
    public int Weight() {
        return weight;
    }
}