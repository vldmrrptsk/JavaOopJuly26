package ru.academits.repetskiy.main;

import ru.academits.repetskiy.binary_tree.BinaryTree;

public class Main {
    public static void main(String[] args){
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(10);
        tree.insert(10);
        tree.insert(6);
        tree.insert(12);
        tree.insert(100);
        tree.insert(300);
        tree.insert(0);

        System.out.println("Размер дерева: " + tree.size());
        System.out.println("Есть ли элемент в дереве? " + tree.contains(0));

        tree.remove(1000);
        System.out.println("Элементы дерева, проход в глубину: " + tree.traversalDFS());
        System.out.println("Элементы дерева, проход в ширину: " + tree.traversalBFS());

        tree.remove(10);
        System.out.println("Элементы дерева, проход в глубину: " + tree.traversalDFS());
        System.out.println("Элементы дерева, проход в ширину: " + tree.traversalBFS());


        System.out.println();
        BinaryTree<String> stringTree = new BinaryTree<>();
        stringTree.insert("Binary");
        stringTree.insert("Tree");
        stringTree.insert("Elements");
        stringTree.insert("Left");
        stringTree.insert("Right");

        System.out.println("Размер дерева: " + stringTree.size());
        System.out.println("Есть ли элемент в дереве? " + stringTree.contains("Hello"));
        System.out.println("Дерево пустое? "  + stringTree.isEmpty());

        System.out.println("Элементы дерева, проход в глубину: " + stringTree.traversalDFS());
        System.out.println("Элементы дерева, проход в ширину: " + stringTree.traversalBFS());


    }
}
