package com.draftBot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listeners extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
      Guild guild = event.getJDA().getGuildById("733318511669149717");
      guild.upsertCommand("draft" , "Your draft to make the Team for the Tournamnet").queue();
    }
}
