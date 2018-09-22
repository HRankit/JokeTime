package com.udacity.jokeprovider;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ProvideJokes {

    private String[] title = {
            "What’s the best thing about Switzerland?\n\n\nI don’t know, but the flag is a big plus.",
            "This is a Joke. Laugh!!! \uD83E\uDD23",
            "I invented a new word!\n\n\nPlagiarism!",
            "Did you hear about the mathematician who’s afraid of negative numbers?\n\n\nHe’ll stop at nothing to avoid them. ",
            "Why do we tell actors to “break a leg?”\n\n\nBecause every play has a cast.",
            "Helvetica and Times New Roman walk into a bar.\n\n\n“Get out of here!” shouts the bartender. “We don’t serve your type.”",
            "Yesterday I saw a guy spill all his Scrabble letters on the road. I asked him, “What’s the word on the street?”\n\n\nOnce my dog ate all the Scrabble tiles. For days he kept leaving little messages around the house.",
            "Knock! Knock!n\nWho’s there?\nControl Freak.\nCon…\nOK, now you say, “Control Freak who?” ",
            "Hear about the new restaurant called Karma?\n\n\nThere’s no menu: You get what you deserve.",


    };

    private List<String> list = Arrays.asList(title);

    public String getJoke() {
        int number = getRandomNumber(list.size() - 1);
        return list.get(number);
    }

    private int getRandomNumber(int max) {
        int min = 0;
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}
