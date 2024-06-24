package com.draftBot.Commands;

import com.draftBot.Tracker;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Draft extends ListenerAdapter {

    private final Tracker tracker;
    public Draft(Tracker tracker) {
        this.tracker = tracker;
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {


        String userId = event.getUser().getId();

        if (!event.getName().equals("draft")) return;
        if (tracker.canUseCommand(userId)) {
            // Increment usage count
            tracker.incrementUsage(userId);
            List<String> countries = new ArrayList<>();
            countries.add("Albania");
            countries.add("Austria");
            countries.add("Belgium");
            countries.add("Croatia");
            countries.add("Czechia");
            countries.add("Denmark");
            countries.add("England");
            countries.add("France");
            countries.add("Georgia");
            countries.add("Germany");
            countries.add("Hungary");
            countries.add("Italy");
            countries.add("Netherlands");
            countries.add("Poland");
            countries.add("Portugal");
            countries.add("Romania");
            countries.add("Scotland");
            countries.add("Serbia");
            countries.add("Slovakia");
            countries.add("Slovenia");
            countries.add("Spain");
            countries.add("Switzerland");
            countries.add("Turkiye");
            countries.add("Ukraine");

            List<String> smallerCountries = new ArrayList<>();
            smallerCountries.add("Albania");
            smallerCountries.add("Austria");
            smallerCountries.add("Czechia");
            smallerCountries.add("Denmark");
            smallerCountries.add("Georgia");
            smallerCountries.add("Hungary");
            smallerCountries.add("Romania");
            smallerCountries.add("Serbia");
            smallerCountries.add("Slovakia");
            smallerCountries.add("Slovenia");
            smallerCountries.add("Turkiye");
            smallerCountries.add("Ukraine");


            int totalPlayers = 23;
            Map<String, Integer> playersDistributionMap = assignPlayersToCountries(countries, totalPlayers, smallerCountries);
            Date date = new Date();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Euro Draft For " + event.getMember().getUser().getName());
            for (Map.Entry<String, Integer> entry : playersDistributionMap.entrySet()) {
                if (!entry.getValue().equals(0)) {
                    embedBuilder.addField("No of Players from " + entry.getKey(), String.valueOf(entry.getValue()), false);
                }
            }


            embedBuilder.setColor(Color.BLUE);
            embedBuilder.setFooter("Thanks For Participating " + event.getMember().getUser().getName(), event.getMember().getUser().getEffectiveAvatarUrl());
            embedBuilder.setTimestamp(date.toInstant());
            event.replyEmbeds(embedBuilder.build()).queue();
        }else {
            event.reply("You have reached the limit of 3 drafts.").queue();
        }
    }

    private Map<String, Integer> assignPlayersToCountries(List<String> countries, int totalPlayers ,  List<String> smallerCountries) {

        Random random =  new Random();
        Map<String, Integer> playerDistributionMap = new HashMap<>();

        countries.stream().forEach(country -> playerDistributionMap.put(country,0));
       int playersToAdd;
        while(totalPlayers > 0) {
            String country = countries.get(random.nextInt(countries.size()));
            if(smallerCountries.contains(country)) {
                playersToAdd = Math.min(random.nextInt(1) + 1, totalPlayers);
             } else {
                playersToAdd = Math.min(random.nextInt(3) + 1, totalPlayers);
            }
            int currentPlayers = playerDistributionMap.get(country);
            playerDistributionMap.put(country, currentPlayers + playersToAdd);
            totalPlayers -= playersToAdd;
            playersToAdd=0;

        }
        return playerDistributionMap;
    }
}
