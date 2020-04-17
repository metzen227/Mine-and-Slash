package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.config.base_player_stat.BasePlayerStatContainer;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public enum Masteries {

    OCEAN("ocean", Elements.Water.format, Words.Ocean),
    FIRE("fire", Elements.Fire.format, Words.Fire),
    NATURE("nature", Elements.Nature.format, Words.Nature),
    STORM("storm", Elements.Thunder.format, Words.Storm),
    DIVINE("divine", TextFormatting.WHITE, Words.Divine),
    HUNTING("hunting", TextFormatting.GREEN, Words.Hunting);

    public static int MAXIMUM_POINTS = 50;

    Masteries(String id, TextFormatting format, Words locName) {
        this.id = id;
        this.format = format;
        this.locName = locName;
    }

    public String id;
    public TextFormatting format;
    public Words locName;

    public ITextComponent getFullName() {
        return locName.locName()
            .appendText(" ")
            .appendSibling(Words.Mastery.locName());
    }

    public String getFullNameTranslated() {
        return locName.locNameForLangFile() + " " + Words.Mastery.locNameForLangFile();
    }

    public List<ExactStatData> getStatsFor(int schoolLevel, EntityCap.UnitData data) { // TODO make it differ per school;
        List<ExactStatData> list = new ArrayList<>();

        BasePlayerStatContainer.INSTANCE.SPELL_MASTERY_STATS.entrySet()
            .forEach(x -> {
                    list.add(new ExactStatData(x.getValue()
                        .floatValue(), SlashRegistry.Stats()
                        .get(x.getKey())));
                }
            );

        int level = getEffectiveLevel(schoolLevel);
        int lvl = MathHelper.clamp(level, 0, data.getLevel());

        list.sort(Comparator.comparingDouble(x -> x.getValue()));

        list.forEach(x -> x.scaleToLvl(lvl)); // scale to this level, not player level

        return list;
    }

    public int getEffectiveLevel(PlayerSpellCap.ISpellsCap spells) {
        return getEffectiveLevel(spells.getAbilitiesData()
            .getSchoolPoints(this));

    }

    public static int LVL_TO_UNLOCK_2ND_SCHOOL = 10;

    public int getEffectiveLevel(int schoolLevel) {
        int level = (int) (ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get() * ((float) schoolLevel / (float) MAXIMUM_POINTS));

        return level;

    }

    public ResourceLocation getGuiIconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spell_schools/schools/" + id + ".png");
    }

}