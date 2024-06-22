package com.draftBot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Draft extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("draft")) return ;
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

        int totalPlayers = 24;
        Map<String, Integer> playersDistributionMap = assignPlayersToCountries(countries, totalPlayers);
        Date date = new Date();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Euro Draft For " + event.getMember().getUser().getName());
        for(Map.Entry<String, Integer> entry: playersDistributionMap.entrySet()) {
           if(!entry.getValue().equals(0)) {
               embedBuilder.addField("No of Players from " + entry.getKey(), String.valueOf(entry.getValue()), false);
           }
        }
        embedBuilder.setColor(Color.BLUE);
        embedBuilder.setFooter("Thanks For Participating " + event.getMember().getUser().getName());
        embedBuilder.setTimestamp(date.toInstant());
        event.replyEmbeds(embedBuilder.build()).queue();
    }

    private Map<String, Integer> assignPlayersToCountries(List<String> countries, int totalPlayers) {

        Random random =  new Random();
        Map<String, Integer> playerDistributionMap = new HashMap<>();

        countries.stream().forEach(country -> playerDistributionMap.put(country,0));

        while(totalPlayers > 0) {
            String country = countries.get(random.nextInt(countries.size()));
            int playersToAdd = Math.min(random.nextInt(3) + 1, totalPlayers);
            int currentPlayers = playerDistributionMap.get(country);
            playerDistributionMap.put(country, currentPlayers + playersToAdd);
            totalPlayers -= playersToAdd;
        }
        return playerDistributionMap;
    }
}
