import org.omg.CORBA.INTERNAL;

import java.util.*;
public class MainMessage {
    public static void main(String[] args) {
        List<Message> received = new ArrayList<>();
        Collections.addAll(received,new Message(1, "Hello!"),
                new Message(2, "发工资了吗？"),
                new Message(2, "发工资了吗？"),
                new Message(3, "去哪吃饭？"),
                new Message(3, "去哪吃饭？"),
                new Message(4, "Bye"));
        List<Message> displayMessages = process(received);
        for (Message message : displayMessages) {
            System.out.println(message.text);
        }
    }

    /**
     * 方法一
     * @param received
     * @return list
     */
    // CodingLog:
    // 1. 在for-each循环中使用list.remove()抛出java.util.ConcurrentModificationException
    // 参考博文：https://blog.csdn.net/qq_35056292/article/details/79751233
    // 2. 使用TreeSed的优点，有序，只需创建一个Comparator,无需修改类中内容覆写equals()和hashCode()
    static List<Message> process(List<Message> received) {
        // TODO: 按sequence去除重复消息
        List<Message> list = new ArrayList<>();
        SortedSet<Message> set = new TreeSet<>(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return Integer.compare(o1.sequence,o2.sequence);
            }
        });
        set.addAll(received);
        list.addAll(set);
        return list;
    }



}

class Message {
    public final int sequence;
    public final String text;
    public Message(int sequence, String text) {
        this.sequence = sequence;
        this.text = text;
    }
}
