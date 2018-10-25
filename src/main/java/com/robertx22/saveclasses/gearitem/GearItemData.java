package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.crafting.bases.IRerollable;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.lists.GearTypes;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.gearitem.IStatsContainer;
import com.robertx22.gearitem.ITooltip;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class GearItemData implements IStatsContainer, Serializable, ITooltip {

	private static final long serialVersionUID = -8327205425334275976L;

	public int Rarity;
	public String gearTypeName;
	public String name = "Error no name";
	public int level;

	public PrimaryStatsData primaryStats;
	public SecondaryStatsData secondaryStats;

	public SuffixData suffix;
	public PrefixData prefix;

	public ChaosStatsData chaosStats = null;

	public GearItemSlot GetBaseGearType() {

		return GearTypes.All.get(gearTypeName);
	}

	public ItemRarity GetRarity() {
		return Rarities.Items.get(Rarity);
	}

	public String GetDisplayName() {

		String text = GetRarity().Color();

		if (prefix != null) {
			text += prefix.BaseAffix().Name() + " ";
		}
		text += name;

		if (suffix != null) {
			text += " " + suffix.BaseAffix().Name() + " ";
		}

		return text;

	}

	private List<IStatsContainer> GetAllStatContainers() {

		List<IStatsContainer> containers = new ArrayList<IStatsContainer>();

		if (suffix != null) {
			containers.add(suffix);
		}
		if (prefix != null) {
			containers.add(prefix);
		}

		if (primaryStats != null) {
			containers.add(primaryStats);
		}
		if (secondaryStats != null) {
			containers.add(secondaryStats);
		}
		if (chaosStats != null) {
			containers.add(chaosStats);
		}

		return containers;

	}

	@Override
	public List<StatModData> GetAllStats() {

		List<StatModData> datas = new ArrayList<StatModData>();

		for (IStatsContainer con : GetAllStatContainers()) {
			datas.addAll(con.GetAllStats());
		}

		return datas;
	}

	@Override
	public void BuildTooltip(ItemTooltipEvent event) {

		event.getToolTip().clear();

		event.getToolTip().add(GetDisplayName());
		event.getToolTip().add(TextFormatting.YELLOW + "Level: " + level);

		event.getToolTip().add("");

		List<ITooltipList> list = new ArrayList<ITooltipList>();
		list.add(primaryStats);
		list.add(secondaryStats);
		list.add(prefix);
		list.add(suffix);
		list.add(chaosStats);

		for (ITooltipList part : list) {

			if (part != null) {
				event.getToolTip().addAll(part.GetTooltipString());
				event.getToolTip().add("");

			}

		}

		ItemRarity rarity = GetRarity();
		event.getToolTip().add(rarity.Color() + "Rarity: " + rarity.Name());

	}

	private List<IRerollable> GetAllRerollable() {
		List<IRerollable> list = new ArrayList<IRerollable>();
		IfNotNullAdd(secondaryStats, list);
		IfNotNullAdd(primaryStats, list);
		IfNotNullAdd(prefix, list);
		IfNotNullAdd(suffix, list);
		IfNotNullAdd(chaosStats, list);
		return list;
	}

	private <T> void IfNotNullAdd(T obj, List<T> list) {
		if (obj != null) {
			list.add(obj);
		}
	}

	public void TryRerollComponents(EntityPlayer player) {

		boolean rerolled = false;
		for (IRerollable rerollable : GetAllRerollable()) {
			if (rerollable.IfRerollFully()) {
				rerollable.RerollFully(this);
				rerolled = true;
			}
			if (rerollable.IfRerollNumbers()) {
				rerollable.RerollNumbers(this);
				rerolled = true;
			}
		}

		if (rerolled) {
			SoundUtils.playSoundAtPlayer(player, SoundEvents.BLOCK_ANVIL_USE, 1, 1);
		}

	}

}