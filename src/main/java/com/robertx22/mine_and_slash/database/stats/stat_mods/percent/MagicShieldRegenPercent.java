package com.robertx22.mine_and_slash.database.stats.stat_mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.MagicShieldRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MagicShieldRegenPercent extends StatMod {

    public MagicShieldRegenPercent() {
    }

    @Override
    public String GUID() {
        return "MagicShieldPercent";
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 8;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return MagicShieldRegen.INSTANCE;
    }
}