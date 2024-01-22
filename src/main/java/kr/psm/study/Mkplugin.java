package kr.psm.study;

import kr.psm.study.command.money;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Random;

public final class Mkplugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginCommand("돈").setExecutor(new money());

        for (World world : Bukkit.getWorlds()) {
            setupWorldBorder(world);
        }
    }

    private void setupWorldBorder(World world) {
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setSize(20000);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        spawnPlayerRandomly(player);
    }

    private void spawnPlayerRandomly(Player player) {
        World world = player.getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        double borderSize = worldBorder.getSize() / 2.0;

        Random random = new Random();

        double x = worldBorder.getCenter().getX() + random.nextDouble() * borderSize - borderSize / 2.0;
        double z = worldBorder.getCenter().getZ() + random.nextDouble() * borderSize - borderSize / 2.0;

        double y = world.getHighestBlockYAt((int) x, (int) z) + 1;

        player.teleport(new Vector(x, y, z).toLocation(world));
    }

    @EventHandler
    public void stick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        if(a == Action.LEFT_CLICK_AIR){
            if(p.getInventory().getItemInMainHand().getType() == Material.CLOCK){
                p.sendMessage("막대기 우클릭");
            }
        }
    }

    @EventHandler
    public void aa(BlockBreakEvent e){
        Player p = e.getPlayer();
        p.sendMessage(e.getBlock().getType() + " 블록을 부셨군요 !");
    }
}
