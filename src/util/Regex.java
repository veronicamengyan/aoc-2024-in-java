package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static List<String> findMatches(final String line, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            final List<String> matches = new ArrayList<>();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                matches.add(matcher.group(i));
            }
            return matches;
        } else {
            throw new IllegalArgumentException("No match found");
        }
    }
}
