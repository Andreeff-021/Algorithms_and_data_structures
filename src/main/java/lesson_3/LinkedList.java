package lesson_3;

public class LinkedList {
    private Node head;
    private Node tail;

    public Node findNode(int value) {
        Node node = head;
        while (node.nextNode != null) {
            node = node.nextNode;
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }

    public void addLast(int value) {
        Node node = new Node();
        node.value = value;
        tail.nextNode = node;
        node.previousNode = tail;
        tail = node;
    }

    public void add(int value, Node previousNode) {
        Node node = new Node();
        node.value = value;
        Node nextNode = previousNode.nextNode;
        previousNode.nextNode = node;
        node.previousNode = previousNode;
        node.nextNode = nextNode;
        nextNode.previousNode = node;
    }

    public void revert() {
        Node node = head;
        Node temp = head;
        head = tail;
        tail = temp;
        while (node.nextNode != null){
            temp = node.nextNode;
            node.nextNode = node.previousNode;
            node.previousNode = temp;
            node = node.previousNode;
        }
    }

    //сортировка выбором
    public  void sort() {
        Node node = head;
        while (node.nextNode != null) {
            Node minPositionNode = node;
            Node innerNode = node.nextNode;
            while (innerNode != null) {
                if (innerNode.value < minPositionNode.value) {
                    minPositionNode = innerNode;
                }
                innerNode = innerNode.nextNode;
            }
            if (minPositionNode != node) {
                swap(node, minPositionNode);

                //обновляем ссылки на head и tail если необходимо
                if (minPositionNode.previousNode == null) {
                    head = minPositionNode;
                }
                if (node.nextNode == null) {
                    tail = node;
                }

                //сдвигаем
                node = minPositionNode.nextNode;
            } else {
                node = node.nextNode;
            }
        }
    }

    private void swap(Node node1, Node node2) {
        //меняем объекты местами, меняя ссыдки на соседние элементы
        Node temp = node1.previousNode;
        node1.previousNode = node2.previousNode;
        node2.previousNode = temp;
        temp = node1.nextNode;
        node1.nextNode = node2.nextNode;
        node2.nextNode = temp;

        //корректируем ссылки соседних элементовна корректные после обмена
        if (node2.previousNode != null) {
            node2.previousNode.nextNode = node2;
        }
        if (node2.nextNode != null) {
            node2.nextNode.previousNode = node2;
        }
        if (node1.previousNode != null) {
            node1.previousNode.nextNode = node1;
        }
        if (node1.nextNode != null) {
            node1.nextNode.previousNode = node1;
        }
    }

    public void push(int value) {
        Node node = new Node();
        node.value = value;
        node.nextNode = head;
        head = node;
    }

    public int pull() {
        if (head == null) {
            throw new IllegalArgumentException("Stack is empty");
        }
        int result = head.value;
        head = head.nextNode;
        return result;
    }

    class Node {
        private int value;
        private Node nextNode;
        private Node previousNode;
    }
}
