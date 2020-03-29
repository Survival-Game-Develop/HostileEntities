package com.sgd.hostileentities;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventManager implements Listener {

    /*
    * Change default event messages
    * */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        /**
         * Change Player Join Message.
         * @params: PlayerJoinEvent e
         * */
        //e.setJoinMessage(ChatColor.DARK_RED + e.getPlayer().getName() + "님이 살육으로 초조해진 마피아의 세계에 입장하셨습니다...");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        //e.setQuitMessage(ChatColor.DARK_RED + e.getPlayer().getName() + "님이 마피아의 세계에서 소리소문없이 사라졌습니다...");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        //e.setDeathMessage(ChatColor.RED + e.getEntity().getName() + "님이 마피아의 손에 목숨을 잃었습니다...");
    }
}
