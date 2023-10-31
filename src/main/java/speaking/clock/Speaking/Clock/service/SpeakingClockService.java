package speaking.clock.Speaking.Clock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SpeakingClockService {

    private static final String[] ONES = {
            "twelve ", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine ", "ten ",
            "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ",
            "eighteen ", "nineteen "
    };
    private static final String[] TENS = {
            "'o ", "", "twenty ", "thirty ", "forty ", "fifty "
    };
    private int hours = 0;
    private int minutes = 0;

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    /** format current time and pass to validateTime() */
    public String getCurrentTime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String [] hours = dtf.format(now).toString().split(":");
        this.hours = Integer.parseInt(hours[0]);
        this.minutes = Integer.parseInt(hours[1]);
        return this.convertToWords(this.hours,this.minutes);
    }

    /** validate time */
    public String validateTime(String hour) {

        String[] hours = new String[2];
        try {
            hours = hour.trim().split(":");
        } catch (NumberFormatException e) {
            log.info("Invalid 'time' input");
            log.error(e.getLocalizedMessage());
            log.debug("input time is : {}", hours.toString());
            throw new NumberFormatException("Please enter a valid 24 hour format time");
        }

        try {
            int h=Integer.parseInt(hours[0]);
            int m=Integer.parseInt(hours[1]);
            if(h > 24 || h<0)
                throw new RuntimeException("Please enter a valid 24 hour format time");
            else if (m>59 || m<0)
                throw new RuntimeException("Please enter a valid 24 hour format time");
            else {
                this.hours = h;
                this.minutes = m;
            }
        } catch (NumberFormatException e) {
            log.info("Invalid 'time' input");
            log.error(e.getLocalizedMessage());
            log.debug("input time is : {}", hours.toString());
            throw new NumberFormatException("Please enter a valid 24 hour format time");
        }
        log.info("time validated ");
        return this.convertToWords(this.hours, this.getMinutes());
    }

    /** convert the time into words */
    private String convertToWords(int hour, int minutes) {

        StringBuilder result = new StringBuilder();

        if (this.getMinutes() == 0) {

            if (this.getHours() == 12) {
                return result.append("It's Midday").toString();
            }

            if (this.getHours() == 24) {
                return result.append("It's Midnight").toString();
            }

            result.append("It's ").append(ONES[hour % 12]);

        } else if (minutes % 10 == 0) {
            result.append("It's ").append(ONES[hour % 12]).append(TENS[minutes / 10]);
        } else if (minutes < 10 || minutes > 20) {
            result.append("It's ").append(ONES[hour % 12]).append(TENS[minutes / 10]).append(ONES[minutes % 10]);
        } else {
            result.append("It's ").append(ONES[hour % 12]).append(ONES[minutes]);
        }
        log.info("time in words : {}",result);
        return result.toString();
    }
}