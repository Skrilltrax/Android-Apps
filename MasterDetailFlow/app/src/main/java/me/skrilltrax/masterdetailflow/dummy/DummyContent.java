package me.skrilltrax.masterdetailflow.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        addItem(new DummyItem("1", "Github", "https://github.com/Skrilltrax"));
        addItem(new DummyItem("2", "Instagram", "https://instagram.com/Skrilltrax"));
        addItem(new DummyItem("3", "Twitter", "https://twitter.com/Skrilltrax"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class DummyItem {
        public final String id;
        public final String title;
        public final String url;

        public DummyItem(String id, String title, String url) {
            this.id = id;
            this.title = title;
            this.url = url;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
