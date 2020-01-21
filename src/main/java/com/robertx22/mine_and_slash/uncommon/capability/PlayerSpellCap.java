package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellPerksData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.capability.bases.IPerkCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerSpellCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "spells");

    private static final String SPELL_PERK_DATA = "spell_perk_data";
    private static final String PLAYER_SPELL_DATA = "player_spells_data";

    @CapabilityInject(ISpellsCap.class)
    public static final Capability<ISpellsCap> Data = null;

    public abstract static class ISpellsCap extends IPerkCap<SpellPerk, SpellPerksData> implements ICommonCapability {
        public abstract BaseSpell getSpellByKeybind(int key);

        public abstract PlayerSpellsData getSpellData();
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<ISpellsCap> {

        @Override
        public ISpellsCap defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<ISpellsCap> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl extends ISpellsCap {

        SpellPerksData perksData = new SpellPerksData();
        PlayerSpellsData playerSpellsData = new PlayerSpellsData();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();
            LoadSave.Save(perksData, nbt, SPELL_PERK_DATA);
            LoadSave.Save(playerSpellsData, nbt, PLAYER_SPELL_DATA);

            return nbt;
        }

        @Override
        public CapTypes getCapType() {
            return CapTypes.SPELLS;
        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.perksData = LoadSave.Load(SpellPerksData.class, new SpellPerksData(), nbt, SPELL_PERK_DATA);

            if (perksData == null) {
                perksData = new SpellPerksData();
            }

            this.playerSpellsData = LoadSave.Load(
                    PlayerSpellsData.class, new PlayerSpellsData(), nbt, PLAYER_SPELL_DATA);

            if (playerSpellsData == null) {
                playerSpellsData = new PlayerSpellsData();
            }
        }

        public int getAllowedPoints(EntityCap.UnitData data) {
            return (int) ((float) data.getLevel() * ModConfig.INSTANCE.Server.TALENT_POINTS_PER_LEVEL.get());
        }

        @Override
        public void allocate(SpellPerk talent) {
            getPerksData().allocate(talent.GUID());
        }

        @Override
        public void applyStats(EntityCap.UnitData data, PlayerEntity player) {
            this.perksData.applyStats(data);
        }

        @Override
        public SpellPerksData getPerksData() {
            return perksData;
        }

        @Override
        public SlashRegistryContainer getContainer() {
            return SlashRegistry.SpellPerks();
        }

        @Override
        public BaseSpell getSpellByKeybind(int key) {
            return this.playerSpellsData.getSpellByKeybind(key);
        }

        @Override
        public PlayerSpellsData getSpellData() {
            return this.playerSpellsData;
        }
    }

    public static class Storage extends BaseStorage<ISpellsCap> {

    }

}
