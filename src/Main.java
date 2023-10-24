public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.put("A", "Arthur");
        cache.put("B", "Bob");
        cache.put("C","Carter");
        cache.put("D","Derek");

        cache.print();

        cache.put("E", "Ernest");
        cache.put("F", "Frank");
//        cache.put("D","Don");

        cache.print();
        System.out.println(cache.get("D"));

        cache.print();
        System.out.println(cache.get("E"));
        System.out.println(cache.get("E"));
        cache.print();
    }
}