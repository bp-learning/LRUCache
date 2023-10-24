import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private final Map<String, Node> map;
    private final int capacity;

    private Node head = null;
    private Node tail = null;

    public LRUCache(int capacity) {
        this.map = new HashMap<String, Node>();
        this.capacity = capacity;
    }

    public void print() {
        System.out.println("Printing Map");
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        System.out.println("Printing LinkedList \n");
        Node current = head;
        while(current != null) {
            System.out.print(current.getKey() + " --> ");

            current = current.getNext();
        }
        System.out.println("null");
    }

    private void deleteFromList(Node node) {
        if(node.getKey().equals(head.getKey())){
            if(node.getKey().equals(tail.getKey())){
                head = null;
                tail = null;
            }else {
                head = node.getNext();
                node.setNext(null);
                head.setPrev(null);
            }
        } else if(node.getKey().equals(tail.getKey())) {
            tail = node.getPrev();
            tail.setNext(null);
            node.setPrev(null);
        } else {
            Node current = head;
            while(current != null) {
                if(current.getKey().equals(node.getKey())){
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                    current.setNext(null);
                    current.setPrev(null);
                    break;
                }
                current = current.getNext();
            }
        }
    }

    private void setListHead(Node node){
        node.setNext(null);
        node.setPrev(null);

        if(head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
    }

    public String get(String key) {
        if(!map.containsKey(key)){
            return null;
        }

        Node node = map.get(key);

        deleteFromList(node);
        setListHead(node);

        return node.getValue();
    }

    public void put(String key, String value){
        if(map.containsKey((key))){
            Node node = map.get(key);
            node.setValue(value);

            deleteFromList(node);
            setListHead(node);
        } else {
            if(map.size() >= capacity) {
                map.remove(tail.getKey());
                deleteFromList(tail);
            }

            Node node = new Node(key, value);

            map.put(key, node);
            setListHead(node);
        }

    }

}
