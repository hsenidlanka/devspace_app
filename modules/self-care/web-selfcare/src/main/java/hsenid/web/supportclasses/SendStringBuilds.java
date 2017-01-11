package hsenid.web.supportclasses;

public class SendStringBuilds {

    public static String sendString(String... toConcat){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < toConcat.length; ++i){
            stringBuilder.append(toConcat[i]);
        }
        return stringBuilder.toString();
    }

    private static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static String getValueOrDefault(String value, String defaultValue) {
        return isNotNullOrEmpty(value) ? value : defaultValue;
    }
}
