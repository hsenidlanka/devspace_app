package hsenid.web;

public class SendStringBuilds {

    public static String sendString(String... toConcat){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < toConcat.length; ++i){
            stringBuilder.append(toConcat[i]);
        }
        return stringBuilder.toString();
    }
}
