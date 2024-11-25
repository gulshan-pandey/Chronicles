package com.app.Chronicles.constants;

public interface Placeholders  {

//    Interface variables are implicitly public, static, and final, making them accessible from any class.

    public static final String WEATHER_API_KEY = "<KEY>";

    public static final String CITY = "<CITY>";

    public static final String QUOTE_API = "http://api.quotable.io/quotes/random";

    public static final String MISTRAL_URL = "https://api.mistral.ai/v1/chat/completions";

    public static String AiPrompt = "Please analyze these personal journal entries to create an emotional wellness snapshot. For each entry, consider:\n" +
            "- Mood rating (1-10 scale)\n" +
            "- Key emotions expressed\n" +
            "- Recurring themes or triggers\n" +
            "- Language patterns and tone\n" +
            "\n" +
            "Provide a confidential summary (strictly less than 100 words) including:\n" +
            "1. Primary emotional state and its frequency\n" +
            "2. Week-over-week mood trends\n" +
            "3. Notable patterns in triggers/situations\n" +
            "4. Potential areas for emotional growth\n" +
            "\n" +
            "also in the end mention Note: These insights are private and personalized for your reflection only. Your privacy and confidentiality are paramount.\n" +
            "\n" + "Journal Entries:\n";



}
