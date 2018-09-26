package com.udacity.jokeprovider;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ProvideJokes {

    private final String[] title = {
            "What’s the best thing about Switzerland?\n\n\nI don’t know, but the flag is a big plus.",
            "This is a Joke. Laugh!!! \uD83E\uDD23",
            "I invented a new word!\n\n\nPlagiarism!",
            "Did you hear about the mathematician who’s afraid of negative numbers?\n\n\nHe’ll stop at nothing to avoid them. ",
            "Why do we tell actors to “break a leg?”\n\n\nBecause every play has a cast.",
            "Helvetica and Times New Roman walk into a bar.\n\n\n“Get out of here!” shouts the bartender. “We don’t serve your type.”",
            "Yesterday I saw a guy spill all his Scrabble letters on the road. I asked him, “What’s the word on the street?”\n\n\nOnce my dog ate all the Scrabble tiles. For days he kept leaving little messages around the house.",
            "Knock! Knock!\nWho’s there?\nControl Freak.\nCon…\nOK, now you say, “Control Freak who?” ",
            "Hear about the new restaurant called Karma?\n\n\nThere’s no menu: You get what you deserve.",
            "A bear walks into a bar and says, “Give me a whiskey and … cola.”\n\n\n“Why the big pause?” asks the bartender. The bear shrugged. “I’m not sure; I was born with them.”",
            "Did you hear about the actor who fell through the floorboards?\n\n\nHe was just going through a stage. ",
            "Yo momma is so poor for Christmas she got a box, put two sticks on it, spun it and said son here's your Xbox 360",
            "Yo momma so fat, she subscribed to pewdiepie's YouTube channel and tried to eat it.",
            "Client to designer: “It doesn’t really look purple. It looks more like a mixture of red and blue.”",
            "Yo momma so stupid she wouldn't buy a gameboy because she was a girl",
            "Yo mamma so stupid she thinks menopause is a button on a VCR",
            "Yo momma so stupid that her exchange particle is a \"moron\"",
            "I was in a couple’s home trying to fix their Internet connection.",
            "Yo momma's a convenient proof that the universe is still expanding exponentially.",
            "Yo momma so fat, her vibrator needs a gps....",
            "Yo momma's like a converging lens - she's wider in the middle than she is on either end.",
            "Yo momma so promiscuous that electrons have a positive charge when they're around her",
            "Yo momma so old that she goes on carbon dates.",
            "Yo momma so fat when she registered for MySpace there was no space left.",
            "Q: Why was there a bug in the computer? A: Because it was looking for a byte to eat?",
            "Yo mamma so stupid she bought a solar-powered flashlight!",
            "Mom: What do IDK, LY & TTYL mean? Son: I don’t know, love you, talk to you later. Mom: OK, I will ask your sister.",
            "Yo momma is so fat she needs a GPS to find her belly button",
            "Yo mamma so stupid I told her to do the robot.....and now R2D2 has AIDS",
            "Yo mamma so fat, when she auditioned for Star Trek she bent the space time continuum",
            "Yo momma so fat that a recursive function computing her weight causes a stack overflow.",
            "Yo momma so fat Shutterfly doesn't have the technology to print a picture of her.",
            "Yo momma so ugly its just best to forego the “V” in MVC with her.",
            "Yo momma so fat that the long double numeric variable type in C++ is insufficient to express her weight.",
            "Yo momma so fat when she sat on my iPad she turned it into a Flat Screen",
            "Yo momma so fat, that she gets free WiFi. Because she's already world wide",
            "Yo momma so dumb that she went to the dentist and asked for a bluetooth.",
            "Yo mamma so stupid its 2016 and she still has no clue how to use a computer or a car",
            "Yo momma so fat that her only friend on Facebook is McDonald's.",
            "Yo momma so fat that NASA shot a rocket into her ass looking for water.",
            "Yo momma is so stupid she returned her smartphone because she thought it was dumb.",
            "Scene: Me using the Siri app on my iPhone. Me: Siri, call my wife. Siri: Samantha McLaughlin is not in your contacts. Me: Samantha Gibbs is my wife. Siri: I’ve added Samantha Gibbs as your wife. Me: Call my wife. Siri: Which wife?",
            "Daughter: I got an A in Chemistry. Mom: WTF! Daughter: Mom, what do you think WTF means? Mom: Well That’s Fantastic.",
            "Yo momma so slow and dumb that she can be emulated on a 286.",
            "Q: What is a computer virus? A: A terminal illness!",
            "Q: Why did the computer keep sneezing? A: It had a virus!",
            "I put my phone on airplane mode, but it sure ain't flyin'.",
            "Once upon a time, a computer programmer drowned at sea. Many were on the beach and heard him cry out, “F1! F1!”, but no one understood.",
            "I decided to make my password \"incorrect\" because if I type it in wrong, my computer will remind me, \"Your password is incorrect.\"",
            "Yo momma’s so big that she has a gravitational pull equal to that of the sun.",
            "Yo momma so stupid she bought tickets to Xbox Live",
            "Yo momma so fat that IEEE is working on a wifi protocol so people can get the signals to reach users on opposite sides of her. It's called 802.11 Draft Fat Momma",
            "Mom: Your great-aunt just passed away. LOL. Son: Why is that funny? Mom: It’s not funny, David! What do you mean? Son: Mom, LOL means Laughing Out Loud. Mom: I thought it meant Lots of Love. I have to call everyone back.",
            "Yo momma so ugly, when she played Minecraft all the enderman teleported away to another server.",
            "Yo momma's dumber than an augmented rat.",
            "Yo momma so fat that THX can't even surround her.",
            "Q: What do you get when you cross a computer and a life guard? A: A screensaver!",
            "Q: What did the computer do at lunchtime? A: Had a byte!",
            "Yo momma so fat even Bill Gates couldn't pay for her liposuction!",
            "Yo momma so fat and stupid, she tears apart computers looking for cookies.",
            "Yo momma so fat NASA has to orbit a satellite around her!",
            "Yo momma so ugly an uber driver would rather get 1-star review than pick her up.",
            "Yo momma conforms to Planck's law - the greater the frequency with which she screws, the more energetic she gets.",
            "I can still remember a time when I knew more than my phone.",
            "Yo momma so stupid she opened up her phone and it said Sprint and she started running.",
            "Yo mamma is so fat that when she sat on my 3DS she turned it into a 2DS.",
            "Q: What does a baby computer call his father? A: Data!",
            "Yo mamma so stupid that she bought curtains for her computer just because it had Windows",
            "Yo momma is so slutty, she is trending on Tinder.",
            "Yo momma so mean she can't use the internet because of cyber bullying laws.",
            "Give a man a fish, and he’ll Instagram it; teach a man to fish, and he’ll still Instagram it.",
            "Yo momma so ugly, Xbox Kinect rejected her.",
            "Yo momma so fat that scientists track her position by observing anomalies in Pluto's orbit.",
            "Yo momma so fat that she doesn't just have a low center of gravity, she has an elliptical orbit.",
            "I Renamed my iPod The Titanic, so when I plug it in, it says, “The Titanic is syncing.”",
            "Yo momma so fat, when she went to the theaters everybody yelled, \"Look King-Kong in 3-D!\"",
            "Yo momma so stupid she took the computer to the doctor because it had a virus",
            "Yo mamma so fat that when she took a selfie, Facebook, Twitter and Instagram crashed.",
            "Yo momma so fat she uses Google Earth to take a selfie.",
            "Yo mamma so fat she has the weight loss commercial for a ringtone",
            "Yo momma so fat I called her and got a stack overflow.",
            "Yo momma so fat, Bloatware is her clothing line.",
            "Yo mamma is so stupid not even Google could translate her.",
            "Yo momma so fat, her fingers are too fat for the iPad.",
            "Yo momma so fat she blocks the WiFi signal",
            "Yo momma is so stupid she thought she had to go to the dentists to get Bluetooth",
            "Q: Why was the computer cold? A: It left it's Windows open!",
            "Yo momma so big that doctors use scuba divers as nanobots to clean her arteries.",
            "If we were to code yo momma in a C++ function she would look like this: double mom (double fat){ mom(fat);return mom;}; //your mom is recursively fat.",
            "Yo momma is so fat, when she sat on an iPod, she made the iPad!",
            "Yo momma so fat that she expresses her weight in scientific notation.",
            "Yo momma so fat that she and the great wall of China are used as reference points when astronauts look back at the Earth",
            "Yo momma so ugly that most Snapchat filters make her better looking.",
            "The mass of \"Yo momma at rest is approximately equal to that of a neutron star traveling at (1-(10^-1000))c.",
            "Q: What did the spider do on the computer? A: Made a website!",
            "Yo Mamma is so fat she needs Google Earth to see her ass.",
            "Yo Mamma so dumb, she thought Spotify was a stain remover!",
            "Before LinkedIn, I didn’t know any strangers.",
            "Instagram is just Twitter for people who go outside.",
            "I put so much more effort into naming my first Wi-Fi than my first child.",
            "Q: Where do all the cool mice live? A: In their mousepads",
            "Yo momma so short she needs a backpack to carry her iPhone.",
            "Q: Why did the computer squeak? A: Because someone stepped on it's mouse!",
            "Yo mamma so ugly when she walks into a bank, they turn off the surveillance cameras",
            "Q: What do you get when you cross a computer with an elephant? A: Lots of memory!",
            "Yo momma so fat that China uses her to block the internet.",
            " The husband called out to his wife in the other room for the computer password. “Start with a capital S, then 123,” she shouted back. We tried S123 several times, but it didn’t work. So we called the wife in. As she input the password, she muttered, “I really don’t know what’s so difficult about typing Start123.” ",
            "I’m employed at a computer security company and have a colleague whose name is M. Alware. His e-mail address is malware@company.com.",
            "Yo momma is so poor she created a gmail account just so she can eat the spam.",
            "Yo momma so ugly when she used the kinect, Microsoft renamed it the dis connect .",
            "Yo momma's such a ho that even the noble gases are attracted to her.",
            "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"",
            "Q: How many prolog programmers does it take to change a lightbulb? A: Yes.",
            "Why do Java developers wear glasses? Because they can't C#",
            "I wanted to grow my own food but I could not get bacon seeds anywhere.",
            "After many years of studying at a university, I’ve finally become a PhD…\nor Pizza Hut Deliveryman as people call it.",
            "The 21st century: Deleting history is often more important than making it.",
            "Do you know why women aren’t allowed in space? \n" +
                    "To avoid scenarios like: \"Houston, we have a problem!\" \n" +
                    "\"What is the problem?\" \n" +
                    "\"Yeah, great, pretend like you don’t know what I’m talking about!"

    };

    private final List<String> list = Arrays.asList(title);

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
