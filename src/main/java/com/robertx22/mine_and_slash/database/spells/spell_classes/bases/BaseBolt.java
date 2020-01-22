package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class BaseBolt extends BaseSpell {

    @Override
    public int getManaCost() {
        return 25;
    }

    @Override
    public ITextComponent GetDescription() {
        return CLOC.tooltip("single_target_spell_bolt");

    }

    public abstract BaseElementalBoltEntity projectile(World world);
}