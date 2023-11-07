import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        String pathMapMoscow = "C:\\Users\\user\\Desktop\\СЭШКИНА\\Programmer" +
                "\\SkillBox\\ExceptionsDebuggingAndTesting\\FilseAndNetwork\\data\\map.json";
        String pathStationsInfo = "C:\\Users\\user\\Desktop\\СЭШКИНА\\Programmer" +
                "\\SkillBox\\ExceptionsDebuggingAndTesting\\FilseAndNetwork\\data\\stations.json";

        JsonMoscowMap mapMoscow = new JsonMoscowMap();
        JsonStationsInfo stationsInfo = new JsonStationsInfo();

        JSONObject objMapMoscow = mapMoscow.getMainObject();
        JSONObject objStationsInfo = stationsInfo.getMainObject();

        JsonWriter.writer(objMapMoscow, pathMapMoscow);
        JsonWriter.writer(objStationsInfo, pathStationsInfo);
    }
}
