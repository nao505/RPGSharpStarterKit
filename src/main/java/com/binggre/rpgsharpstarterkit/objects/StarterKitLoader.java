package com.binggre.rpgsharpstarterkit.objects;

import java.util.HashMap;
import java.util.Map;

public class StarterKitLoader {

    private static final Map<String, StarterKit> starterKit = new HashMap<>();

    public static Map<String, StarterKit> getMap() {
        return starterKit;
    }

    public static void put(StarterKit starterKit) {
        System.out.println("ㅇㅇ");
        getMap().put(starterKit.getJobName(), starterKit);
    }

    public static StarterKit get(String jobName) {
        return getMap().get(jobName);
    }

    public static void remove(String jobName) {
        getMap().remove(jobName);
    }

    public static void remove(StarterKit starterKit) {
        remove(starterKit.getJobName());
    }

    public static boolean containsKey(String jobName) {
        return getMap().containsKey(jobName);
    }
}
