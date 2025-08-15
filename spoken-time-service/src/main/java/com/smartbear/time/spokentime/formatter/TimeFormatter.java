package com.smartbear.time.spokentime.formatter;


public class TimeFormatter {

    private static final String[] numbers = {
            "zero","one","two","three","four","five","six","seven","eight","nine","ten",
            "eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen",
            "eighteen","nineteen","twenty","twenty-one","twenty-two","twenty-three",
            "twenty-four","twenty-five","twenty-six","twenty-seven","twenty-eight","twenty-nine"
    };

    public static String toBritishSpokenTime(int hour, int minute) {
        if(hour == 0 && minute == 0) return "midnight";
        if(hour == 12 && minute == 0) return "noon";

        String hourWord = numbers[hour % 12 == 0 ? 12 : hour % 12];

        if(minute == 0) return hourWord + " o'clock";
        else if(minute == 15) return "quarter past " + hourWord;
        else if(minute == 30) return "half past " + hourWord;
        else if(minute == 45) return "quarter to " + numbers[(hour+1)%12==0?12:(hour+1)%12];
        else if(minute < 30) return numbers[minute] + " past " + hourWord;
        else return numbers[60-minute] + " to " + numbers[(hour+1)%12==0?12:(hour+1)%12];
    }
}
