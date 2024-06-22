package com.draftBot;

import com.draftBot.Commands.Draft;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("MTI1Mzc1Njg2NjgwMzEzODYyMQ.GonQst.4Uu-PtDZfVs0b8h_zuGTTeB5kFRL7XCHQsMm-g").enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        jda.addEventListener(new Listeners());
        jda.addEventListener(new Draft());
    }
}
