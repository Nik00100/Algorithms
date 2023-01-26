package ya_int.taxi;

import java.time.*;
import java.time.format.DateTimeFormatter;




public class test {
    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / 3600;
        long minutes = ((seconds % 3600) / 60);
        long secs = (seconds % 60);

        return new long[]{Math.abs(hours), Math.abs(minutes), Math.abs(secs)};
    }

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy D H:mm");

        LocalDateTime dt1 = LocalDateTime.parse("1000 50 7:25",formatter);

        // Prints the date
        System.out.println("Date 1: " + dt1);

        // Parses the date
        LocalDateTime dt2 = LocalDateTime.parse("1000 14 21:30",formatter);

        // Prints the date
        System.out.println("Date 2: " + dt2);

        // Compares the date
        System.out.println("After comparison: " + dt2.compareTo(dt1));


        Period period = getPeriod(dt2,dt1);
        long time[] = getTime(dt2,dt1);

        System.out.println(period.getYears() + " years " +
                period.getMonths() + " months " +
                period.getDays() + " days " +
                time[0] + " hours " +
                time[1] + " minutes " +
                time[2] + " seconds.");

        /*LocalDateTime date = LocalDateTime.now();
        String text = date.format(formatter);
        System.out.println(text);
        LocalDateTime parsedDate = LocalDateTime.parse("1000 50 7:25", formatter);
        System.out.println(parsedDate);*/
    }
}
