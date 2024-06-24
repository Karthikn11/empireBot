package com.draftBot;

import com.draftBot.Commands.Draft;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        Tracker tracker = new Tracker();
        String specifiedChannel = "834274579861733386";
        JDA jda = JDABuilder.createDefault(Config.get("TOKEN")).enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableIntents(GatewayIntent.GUILD_MODERATION)
                .build();
        jda.addEventListener(new Listeners(tracker, specifiedChannel));
        jda.addEventListener(new Draft(tracker));


    }
}
