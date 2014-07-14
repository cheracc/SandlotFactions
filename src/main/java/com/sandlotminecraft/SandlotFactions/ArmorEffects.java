package com.sandlotminecraft.SandlotFactions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.sandlotminecraft.SandlotFactions.ArmorEffects.ArmorSet.*;

/**
 * Created by Shawn on 7/11/2014.
 */
public class ArmorEffects implements Listener {
    Player p = null;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.p = event.getPlayer();

        AddEffect(p);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        this.p = event.getPlayer();

        AddEffect(p);
    }

    @EventHandler
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        this.p = event.getPlayer();

        AddEffect(p);
    }

    private void AddEffect(Player player) {
        ArmorSet set = getSet();

        switch (set) {
            case Chain:
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 1, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0, true));
                break;
            case Leather:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1, true));
                break;
            case Gold:
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0, true));
                break;
            case Iron:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true));
                break;
            case Diamond:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0, true));
                break;
            case PartDiamond:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0, true));
                break;
            case None:
                break;

        }
    }

    public ArmorSet getSet() {
        ItemStack helm = this.p.getEquipment().getHelmet();
        ItemStack legs = this.p.getEquipment().getLeggings();
        ItemStack boots = this.p.getEquipment().getBoots();
        ItemStack chest = this.p.getEquipment().getChestplate();
        boolean emptySlot = false;

        if ((helm == null) ||
                (legs == null) ||
                (boots == null) ||
                (chest == null)) {
            emptySlot = true;
        }
        if (!emptySlot && (helm.getType() == Material.LEATHER_HELMET) &&
                (legs.getType() == Material.LEATHER_LEGGINGS) &&
                (boots.getType() == Material.LEATHER_BOOTS) &&
                (chest.getType() == Material.LEATHER_CHESTPLATE)) {
            return Leather;
        }
        if (!emptySlot && (helm.getType() == Material.IRON_HELMET) &&
                (legs.getType() == Material.IRON_LEGGINGS) &&
                (boots.getType() == Material.IRON_BOOTS) &&
                (chest.getType() == Material.IRON_CHESTPLATE)) {
            return Iron;
        }
        if (!emptySlot && (helm.getType() == Material.CHAINMAIL_HELMET) &&
                (legs.getType() == Material.CHAINMAIL_LEGGINGS) &&
                (boots.getType() == Material.CHAINMAIL_BOOTS) &&
                (chest.getType() == Material.CHAINMAIL_CHESTPLATE)) {
            return Chain;
        }
        if (!emptySlot && (helm.getType() == Material.GOLD_HELMET) &&
                (legs.getType() == Material.GOLD_LEGGINGS) &&
                (boots.getType() == Material.GOLD_BOOTS) &&
                (chest.getType() == Material.GOLD_CHESTPLATE)) {
            return Gold;
        }
        if (!emptySlot && (helm.getType() == Material.DIAMOND_HELMET) &&
                (legs.getType() == Material.DIAMOND_LEGGINGS) &&
                (boots.getType() == Material.DIAMOND_BOOTS) &&
                (chest.getType() == Material.DIAMOND_CHESTPLATE)) {
            return Diamond;
        }
        if ((helm != null && helm.getType() == Material.DIAMOND_HELMET) ||
                (legs != null && legs.getType() == Material.DIAMOND_LEGGINGS) ||
                (boots != null && boots.getType() == Material.DIAMOND_BOOTS) ||
                (chest != null && chest.getType() == Material.DIAMOND_CHESTPLATE)) {
            return PartDiamond;
        }
        return None;
    }

    public enum ArmorSet {
        Leather, Chain, Gold, Iron, Diamond, PartDiamond,  None;
    }
}
