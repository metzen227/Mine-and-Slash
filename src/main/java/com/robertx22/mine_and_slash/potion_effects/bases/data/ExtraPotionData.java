package com.robertx22.mine_and_slash.potion_effects.bases.data;

import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.UUID;

@Storable
public class ExtraPotionData {

    @Store // some potions will only do a thing x amount of times
    public int uses = 1;

    @Store
    public String casterID = "";

    @Store
    private int stacks = 1;

    @Store
    public int casterLvl = 1;

    public void addStacks(int num, BasePotionEffect effect) {
        this.stacks = MathHelper.clamp(stacks + num, 0, effect.maxStacks());
    }

    public void decreaseStacks(int num) {
        stacks -= num;
    }

    public int getStacks() {
        return stacks;
    }

    public LivingEntity getCaster(World world) {
        if (casterID.isEmpty()) {
            return null;
        }
        return Utilities.getLivingEntityByUUID(world, UUID.fromString(casterID));

    }

}