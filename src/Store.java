import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

public class Store {
    static Set<Integer> keySet = new HashSet<>(10);
    static HashMap<Integer, NoteBook> storeMap = new HashMap<>(10);
    static HashMap<Integer, NoteBook> getNoteBookMap() {

        NoteBook nb01 = new NoteBook();
        nb01.id = 1;
        nb01.manufacturer = "Acer";
        nb01.size = 13;
        nb01.processor = "Intel Core i3";
        nb01.ram = 4;
        nb01.hdd = 512;
        nb01.os = "Windows 8";
        nb01.color = "blue";
        nb01.price = 18000;

        NoteBook nb02 = new NoteBook();
        nb02.id = 2;
        nb02.manufacturer = "Acer";
        nb02.size = 15;
        nb02.processor = "Intel Core i5";
        nb02.ram = 8;
        nb02.hdd = 1024;
        nb02.os = "Windows 10";
        nb02.color = "black";
        nb02.price = 23000;

        NoteBook nb03 = new NoteBook();
        nb03.id = 3;
        nb03.manufacturer = "Acer";
        nb03.size = 17;
        nb03.processor = "Intel Core i7";
        nb03.ram = 16;
        nb03.hdd = 2000;
        nb03.os = "without OS";
        nb03.color = "white";
        nb03.price = 31000;

        NoteBook nb04 = new NoteBook();
        nb04.id = 4;
        nb04.manufacturer = "Acer";
        nb04.size = 17;
        nb04.processor = "Intel Core i7";
        nb04.ram = 16;
        nb04.hdd = 2000;
        nb04.os = "Windows 10";
        nb04.color = "black";
        nb04.price = 42000;

        NoteBook nb05 = new NoteBook();
        nb05.id = 5;
        nb05.manufacturer = "ASUS";
        nb05.size = 14;
        nb05.processor = "Intel Core i9";
        nb05.ram = 32;
        nb05.hdd = 1024;
        nb05.os = "Linux";
        nb05.color = "black";
        nb05.price = 45000;

        storeMap.put(keyStore(), nb01);
        storeMap.put(keyStore(), nb02);
        storeMap.put(keyStore(), nb03);
        storeMap.put(keyStore(), nb04);
        storeMap.put(keyStore(), nb05);

        return storeMap;
    }

    static Integer keyStore() {
        if (keySet.isEmpty()) {
            keySet.add(0);
        }
        Integer keyNum = Collections.max(keySet) + 1;
        keySet.add(keyNum);
        return keyNum;
    }

    static HashMap<Integer, NoteBook> getCustomerMap(HashMap<Integer, NoteBook> storeMap,
            HashMap<Integer, String> custom_filter_map) {
        HashMap<Integer, NoteBook> customerMap = new HashMap<>(0);
        for (Entry<Integer, NoteBook> entry : storeMap.entrySet()) {
            customerMap.put(entry.getKey(), entry.getValue());
        }
        // customerMap = (HashMap<Integer, NoteBook>) storeMap.clone();
        for (Entry<Integer, String> entry_f : custom_filter_map.entrySet()) {
            for (Entry<Integer, NoteBook> entry_m : storeMap.entrySet()) {
                switch (entry_f.getKey()) {
                    case 1:
                        if (!entry_m.getValue().manufacturer.equalsIgnoreCase(entry_f.getValue())
                                && !entry_f.getValue().equals("")) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 2:
                        if (entry_m.getValue().size < tryInt(entry_f.getValue())) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 3:
                        if (!entry_m.getValue().processor.equalsIgnoreCase(entry_f.getValue())
                                && !entry_f.getValue().equals("")) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 4:
                        if (entry_m.getValue().ram < tryInt(entry_f.getValue())) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 5:
                        if (entry_m.getValue().hdd < tryInt(entry_f.getValue())) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 6:
                        if (!entry_m.getValue().os.equalsIgnoreCase(entry_f.getValue())
                                && !entry_f.getValue().equals("")) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 7:
                        if (!entry_m.getValue().color.equalsIgnoreCase(entry_f.getValue())
                                && !entry_f.getValue().equals("")) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    case 8:
                        if (entry_m.getValue().price < tryInt(entry_f.getValue())) {
                            customerMap.remove(entry_m.getKey());
                        }
                        break;
                    default:
                }
            }
        }
        return customerMap;
    }

    public static Integer tryInt(String string) {
        Integer integer = 0;
        try {
            integer = Integer.parseInt(string);
        } catch (Exception e) {
        }
        return integer;
    }
}
