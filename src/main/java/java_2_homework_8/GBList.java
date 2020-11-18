package java_2_homework_8;

public interface GBList extends GBIterable {
    void add(String val);
    Node get(Integer index);
    boolean remove(String val);
    int size();
}
