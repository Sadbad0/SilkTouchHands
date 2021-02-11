package com.visualcody.silktouchhands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class events implements Listener {

    private static EnumSet<Material> m = EnumSet.of(
            Material.AIR,
            Material.REDSTONE_WIRE,
            Material.REDSTONE_WALL_TORCH,
            Material.REDSTONE_TORCH,
            Material.PISTON_HEAD,
            Material.GRASS,
            Material.TALL_GRASS,
            Material.TALL_SEAGRASS,
            Material.SEA_PICKLE,
            Material.SEAGRASS,
            Material.VINE,
            Material.CHEST,
            Material.TRAPPED_CHEST,
            Material.JUKEBOX,
            Material.FURNACE,
            Material.HOPPER,
            Material.DRAGON_HEAD,
            Material.DRAGON_WALL_HEAD,
            Material.COMPOSTER,
            Material.FLETCHING_TABLE,
            Material.LOOM,
            Material.BARREL,
            Material.SMOKER,
            Material.DROPPER,
            Material.DISPENSER,
            Material.SNOW,
            Material.LECTERN,
            Material.WHEAT,
            Material.MELON_STEM,
            Material.ATTACHED_MELON_STEM,
            Material.PUMPKIN_STEM,
            Material.ATTACHED_PUMPKIN_STEM,
            Material.BEETROOTS,
            Material.END_PORTAL,
            Material.NETHER_PORTAL
    );

    // Unused but could have it so the silktouch effect works as long as your not holding a tool but I think that might break stuff
    private static EnumSet<Material> tools = EnumSet.of(
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,
            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_SWORD,
            Material.NETHERITE_AXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_SWORD,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_PICKAXE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,
            Material.WOODEN_AXE,
            Material.WOODEN_HOE,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_SHOVEL,
            Material.WOODEN_SWORD,
            Material.SHEARS
    );



    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType().equals(Material.AIR)) return;
        Player p = e.getPlayer();
        int ic = 1;
        if(p != null) {
            if(p.getGameMode().equals(GameMode.SURVIVAL) && hasPerm(p)) {
                if(p.getEquipment().getItemInMainHand().getType().equals(Material.AIR)) {
                    // only fire with nothing in hand
                    if(!m.contains(e.getBlock().getType())) { // Only continue if block is not on the ban list
                        if(e.getBlock().getState() instanceof ShulkerBox) return; // Stop doing stuff if its a shulker box as I am not going down that rabbit hole
                        if(e.getBlock().getType().toString().endsWith("WALL_SIGN")) return; // Why Wall signs are a different object to normal signs baffles me
                        if(e.getBlock().getType().toString().startsWith("POTTED")) return; // Way too many potted things to add all to ban list
                        if(!main.config.getBoolean("infinite_cake") && e.getBlock().getType().equals(Material.CAKE)) return;
                        if(main.config.getBoolean("double_beds") && e.getBlock().getType().toString().endsWith("BED")) ic = 2;
                        e.setDropItems(false);
                        ItemStack i = new ItemStack(e.getBlock().getType(), 1);
                        p.getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                        if(ic == 2) p.getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), i); // do a 2nd drop because bed
                    }

                }
            }


        }
    }


    private boolean hasPerm(Player p) {
        if(main.config.getBoolean("use_permissions")) {
            return p.hasPermission("silktouchhands.silktouch");
        } else {
            return true;
        }
    }
}
