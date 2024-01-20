package kr.psm.study;

import kr.psm.study.command.money;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Mkplugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginCommand("돈").setExecutor(new money());
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void stick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        @NotNull Action a = e.getAction();
        if(a == Action.LEFT_CLICK_AIR){
            if(p.getItemInHand().getType() == Material.CLOCK){
                p.sendMessage("막대기 우클릭");
            }
        }
    }
}
