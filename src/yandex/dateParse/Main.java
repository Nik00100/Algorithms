package yandex.dateParse;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String inputPeriod = reader.readLine();
        String[] dateRange = reader.readLine().split(" ");
        Period period = new DateFactory().getCurrentPeriod(inputPeriod);
        List<String> dateIntervals = period.getPeriod(dateRange);

        dateIntervals.stream()
                .forEach(dateInterval-> {
                    try {
                        writer.write(dateInterval);
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        reader.close();
        writer.close();
    }

    static class DateFactory {
        public Period getCurrentPeriod(String inputPeriod) {
            Period period = null;
            if (inputPeriod.equals("WEEK")) {
                period = new WeekPeriod();
            } else if (inputPeriod.equals("MONTH")) {
                period = new MonthPeriod();
            } else if (inputPeriod.equals("QUARTER")) {
                period = new QuarterPeriod();
            } else if (inputPeriod.equals("YEAR")) {
                period = new YearPeriod();
            } else if (inputPeriod.equals("REVIEW")) {
                period = new ReviewPeriod();
            }
            return period;
        }
    }

    interface Period {
        List<String> getPeriod(String[] dateRange);
    }

    static class WeekPeriod implements Period {
        @Override
        public List<String> getPeriod(String[] dateRange) {
            List<String> res = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dateRange[0], formatter);
            LocalDate endDate = LocalDate.parse(dateRange[1], formatter);

            StringBuilder sb = new StringBuilder();

            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate)) {
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                boolean flag = month != endDate.getMonthValue();
                int diff = DayOfWeek.SATURDAY.getValue() - currentDate.getDayOfWeek().getValue();
                int endDayOfWeek =currentDate.getDayOfMonth() + diff;


                LocalDate endDateOfCurrentWeek = LocalDate.of(year,month,endDayOfWeek);
                sb.append(currentDate).append(" ").append(endDateOfCurrentWeek);
                res.add(sb.toString());
                sb.delete(0,sb.length());
                if (month == 12 && endDayOfWeek < 25) {
                    currentDate = LocalDate.of(year+1,1,1);
                } else if (endDayOfWeek==currentDate.lengthOfMonth()) {
                    currentDate = LocalDate.of(year,month+1,1);
                }
                currentDate = LocalDate.of(year,month,endDayOfWeek+1);
            }
            return res;
        }
    }

    static class MonthPeriod implements Period {
        @Override
        public List<String> getPeriod(String[] dateRange) {
            List<String> res = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dateRange[0], formatter);
            LocalDate endDate = LocalDate.parse(dateRange[1], formatter);

            StringBuilder sb = new StringBuilder();

            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate)) {
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();
                int endDayOfMonth = currentDate.getMonth().length(startDate.isLeapYear());

                boolean flag = month != endDate.getMonthValue();

                endDayOfMonth = flag ? endDayOfMonth : endDate.getDayOfMonth();

                LocalDate endDateOfCurrentMonth = LocalDate.of(year,month,endDayOfMonth);
                sb.append(currentDate).append(" ").append(endDateOfCurrentMonth);
                res.add(sb.toString());
                sb.delete(0,sb.length());
                if (month == 12) {
                    currentDate = LocalDate.of(year+1,1,1);
                }
                currentDate = LocalDate.of(year,month+1,1);
            }
            return res;
        }
    }

    static class QuarterPeriod implements Period {
        @Override
        public List<String> getPeriod(String[] dateRange) {
            return null;
        }
    }

    static class YearPeriod implements Period {
        @Override
        public List<String> getPeriod(String[] dateRange) {
            List<String> res = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dateRange[0], formatter);
            LocalDate endDate = LocalDate.parse(dateRange[1], formatter);

            StringBuilder sb = new StringBuilder();

            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate)) {
                int year = currentDate.getYear();
                int month = currentDate.getMonthValue();

                boolean flag = currentDate.getYear() != endDate.getYear();

                int endDayOfYear = flag ? 31 : endDate.getDayOfMonth();
                int endMonth =  flag ? 12 : endDate.getMonthValue();

                LocalDate endDateOfCurrentMonth = LocalDate.of(year,endMonth,endDayOfYear);
                sb.append(currentDate).append(" ").append(endDateOfCurrentMonth);
                res.add(sb.toString());
                sb.delete(0,sb.length());
                currentDate = LocalDate.of(year+1,month,1);
            }
            return res;
        }
    }

    static class ReviewPeriod implements Period {
        @Override
        public List<String> getPeriod(String[] dateRange) {
            return null;
        }
    }
}