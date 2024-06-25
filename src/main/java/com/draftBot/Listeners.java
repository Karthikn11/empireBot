package com.draftBot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listeners extends ListenerAdapter {

    private final Tracker tracker;
    private String specifiedChannel;
    public Listeners(Tracker tracker , String specifiedChannel) {
        this.tracker = tracker;
        this.specifiedChannel = specifiedChannel;
    }

    @Override
    public void onReady(ReadyEvent event) {
      Guild guild = event.getJDA().getGuildById("733318511669149717");
      guild.upsertCommand("draft" , "Your draft to make the Team for the Tournamnet").queue();
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        TextChannel channel = event.getChannel().asTextChannel();

        if(channel.getId().equals(specifiedChannel)) {
            if (message.getMentions().getUsers().isEmpty()) {
                return;
            }
            Member mentionedMember = message.getMember();
            Category category = channel.getParentCategory();
            if (category == null) {
                event.getChannel().sendMessage("This channel is not in a category, unable to create a new channel.").queue();
                return;
            }
            String newChannelName = mentionedMember.getUser().getName() + "-channel";
            category.createTextChannel(newChannelName).queue(newChannel -> {
                newChannel.sendMessage(mentionedMember.getAsMention() + ", this is your draft channel!").queue();
            });
        }else {
            return;
        }
    }
}
