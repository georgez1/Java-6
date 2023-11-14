import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, NoteBook> storeMap = Store.getNoteBookMap();
        System.out.println("Магазин ноутбуков.\n\nВ наличии:");
        System.out.println(storeMap.toString());
        HashMap<Integer, HashSet<String>> crit_map = criteriaMap(storeMap);
        HashMap<Integer, String> custom_filter_map = getCustomFilter(crit_map);
        System.out.println("\nВаши параметры поиска: \n" + custom_filter_map);
        HashMap<Integer, NoteBook> customerMap = Store.getCustomerMap(storeMap, custom_filter_map);
        System.out.println("\nРезультаты поиска:");
        System.out.println(customerMap.toString());
    }

    public static void printNoteCriteria() {

        System.out.println("\nКритерии фильтра:\n" +
                "0 - закончить выбор фильтров и вывести список товаров;\n" +
                "1 - производитель;\n" +
                "2 - диагональ экрана;\n" +
                "3 - процессор;\n" +
                "4 - объем оперативной памяти;\n" +
                "5 - объем ЖД;\n" +
                "6 - операционная система;\n" +
                "7 - цвет;\n" +
                "8 - цена;\n");
    }

    public static HashMap<Integer, HashSet<String>> criteriaMap(HashMap<Integer, NoteBook> storeMap) {
        HashMap<Integer, HashSet<String>> crit_map = new HashMap<>(8);
        HashSet<String> manuf_Set = new HashSet<>(0);
        HashSet<String> size_Set = new HashSet<>(0);
        HashSet<String> proc_Set = new HashSet<>(0);
        HashSet<String> ram_Set = new HashSet<>(0);
        HashSet<String> hdd_Set = new HashSet<>(0);
        HashSet<String> os_Set = new HashSet<>(0);
        HashSet<String> color_Set = new HashSet<>(0);
        HashSet<String> price_Set = new HashSet<>(0);
        for (int i = 1; i < storeMap.size() + 1; i++) {
            manuf_Set.add(storeMap.get(i).manufacturer);
            size_Set.add(Integer.toString(storeMap.get(i).size));
            proc_Set.add(storeMap.get(i).processor);
            ram_Set.add(Integer.toString(storeMap.get(i).ram));
            hdd_Set.add(Integer.toString(storeMap.get(i).hdd));
            os_Set.add(storeMap.get(i).os);
            color_Set.add(storeMap.get(i).color);
            price_Set.add(Integer.toString(storeMap.get(i).price));
            crit_map.put(1, manuf_Set);
            crit_map.put(2, size_Set);
            crit_map.put(3, proc_Set);
            crit_map.put(4, ram_Set);
            crit_map.put(5, hdd_Set);
            crit_map.put(6, os_Set);
            crit_map.put(7, color_Set);
            crit_map.put(8, price_Set);
        }
        return crit_map;
    }

    public static HashMap<Integer, String> getCustomFilter(HashMap<Integer, HashSet<String>> crit_map) {
        HashMap<Integer, String> custom_filter_map = new HashMap<>(0);
        try (Scanner sc = new Scanner(System.in, "cp866")) {
            String str = "";
            printNoteCriteria();
            while (!str.equalsIgnoreCase("0")) {
                System.out.print("Введите номер критерия: ");
                str = sc.nextLine();
                int criteria_key = 0;
                try {
                    criteria_key = Integer.parseInt(str);
                } catch (Exception e) {
                }
                if (criteria_key > 0 && criteria_key < 9) {
                    System.out.println("В наличии: ");
                    System.out.println(crit_map.get(criteria_key));
                    System.out.println("Введите наименование: ");
                    str = sc.nextLine();
                    custom_filter_map.put(criteria_key, str.trim());
                }
            }
        }
        return custom_filter_map;
    }

}
