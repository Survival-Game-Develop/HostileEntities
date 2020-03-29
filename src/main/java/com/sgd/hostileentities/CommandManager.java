package com.sgd.hostileentities;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandManager implements CommandExecutor {
    Location afk_location = null;
    Logger bukkit_logger;
    ColorfulConsole colorfulConsole;

    public CommandManager(ColorfulConsole colorfulConsole){
        this.colorfulConsole = colorfulConsole;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.colorfulConsole.consoleColored(this.colorfulConsole.debug, "[CommandManager] " + sender.getName() + "플레이어가 " + label + ' ' + args + " 명령어를 사용했습니다. ");
        if(!(sender instanceof Player)){
            sender.sendMessage("[LapisPlugin] 플레이어만 사용할 수 있는 명령어입니다!");
            this.colorfulConsole.consoleColored(this.colorfulConsole.debug, "[CommandManager] command `"+ label + "` cannot be used by non-player sender.");
            bukkit_logger.info("[LapisPlugin] " + command + " cannot be used by non-player sender.");
        }
        else{
            switch (label){
                case "afk":
                    afk(sender, command, label, args);
                    break;
                case "info":
                    info(sender, command, label, args);
                    break;
            }
        }
        return false;
    }

    protected boolean info(CommandSender sender, Command command, String label, String[] args){
        Player sender_p = ((Player) sender).getPlayer();

        if(sender_p != null) {
            try{
                PluginDescriptionFile pluginDescriptionFile = this.colorfulConsole.pluginDescriptionFile;
                sender_p.sendMessage("============");
                sender_p.sendMessage("플러그인 이름 : " + pluginDescriptionFile.getName());
                sender_p.sendMessage("플러그인 버전 : " + pluginDescriptionFile.getVersion());
                sender_p.sendMessage("플러그인 개발자 : " + pluginDescriptionFile.getAuthors());
                sender_p.sendMessage("플러그인 공식 웹사이트 : " + pluginDescriptionFile.getWebsite());
                sender_p.sendMessage("============");
            }catch (NullPointerException e){
                sender_p.sendMessage("명령어를 실행하는 과정에 오류가 발생했습니다! : " + e.getLocalizedMessage());
            }
            return true;
        }
        else{
            sender.sendMessage("[LapisPlugin] 오류가 발생했습니다! 로그를 확인해주세요...");
            this.colorfulConsole.consoleColored(this.colorfulConsole.error, "[LapisPlugin] Player instance of sender is null.");
            return false;
        }
    }

    protected boolean afk(CommandSender sender, Command command, String label, String[] args){
        Player sender_p = ((Player) sender).getPlayer();

        if(sender_p != null) {
            sender_p.teleport(afk_location);

            if(this.afk_location != null){
                sender_p.teleport(afk_location);
                this.bukkit_logger.log(Level.SEVERE, "[LapisPlugin] Moved AFK-Player to pre-configured afk location.");
            }else{
                sender_p.teleport(sender_p.getServer().getWorlds().get(0).getSpawnLocation());
                this.bukkit_logger.log(Level.SEVERE, "[LapisPlugin] Moved AFK-Player to Main World(0)'s spawn location due to lack of afk-location data.");
            }
            return true;
        }
        else{
            sender.sendMessage("[LapisPlugin] 오류가 발생했습니다! 로그를 확인해주세요...");
            this.bukkit_logger.log(Level.SEVERE, "[LapisPlugin] Player instance of sender is null.");
            return false;
        }
    }
}
