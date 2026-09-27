package ru.academits.repetskiy.binary_tree;

import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>> {
    private int size;
    private TreeNode<E> root;

    private static class TreeNode<E> {
        private TreeNode<E> leftChild;
        private TreeNode<E> rightChild;
        private E element;

        TreeNode(TreeNode<E> leftChild, TreeNode<E> rightChild, E element) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.element = element;
        }
    }

    public BinaryTree() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(E e) {
        if (root == null) {
            root = new TreeNode<E>(null, null, e);
        } else {
            TreeNode<E> currentElement = root;

            while (true) {
                int comparison = e.compareTo(currentElement.element);

                if (comparison < 0) {
                    if (currentElement.leftChild != null) {
                        currentElement = currentElement.leftChild;
                    } else {
                        currentElement.leftChild = new TreeNode<E>(null, null, e);
                        break;
                    }
                } else if (comparison > 0) {
                    if (currentElement.rightChild != null) {
                        currentElement = currentElement.rightChild;
                    } else {
                        currentElement.rightChild = new TreeNode<E>(null, null, e);
                        break;
                    }
                } else {
                    return;
                }
            }
        }

        size++;
    }

    public boolean contains(E e) {
        if (e == null) {
            throw new NullPointerException("Значение не должно быть null!");
        }

        TreeNode<E> currentElement = root;

        while (currentElement != null) {
            int comparison = e.compareTo(currentElement.element);

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                if (currentElement.leftChild == null) {
                    return false;
                }
                currentElement = currentElement.leftChild;
            } else {
                if (currentElement.rightChild == null) {
                    return false;
                }

                currentElement = currentElement.rightChild;
            }
        }

        return false;
    }

    private int countChildren(TreeNode<E> parent) {
        if (parent.rightChild != null && parent.leftChild != null) {
            return 2;
        }

        if (parent.leftChild != null || parent.rightChild != null) {
            return 1;
        }

        return 0;
    }

    private void removeNodeWithOneChild(TreeNode<E> currentElement, TreeNode<E> parentElement) {
        TreeNode<E> childElement;

        if (currentElement.rightChild != null) {
            childElement = currentElement.rightChild;
        } else {
            childElement = currentElement.leftChild;
        }

        if (parentElement == null) {
            root = childElement;
        } else if (parentElement.rightChild == currentElement) {
            parentElement.rightChild = childElement;
        } else {
            parentElement.leftChild = childElement;
        }
    }

    private void removeNodeWithoutChildren(TreeNode<E> currentElement, TreeNode<E> parentElement) {
        if (parentElement == null) {
            root = null;
        } else if (parentElement.rightChild == currentElement) {
            parentElement.rightChild = null;
        } else {
            parentElement.leftChild = null;
        }
    }

    private void removeNodeWithTwoChildren(TreeNode<E> currentElement) {
        TreeNode<E> receiverElement = currentElement.rightChild;
        TreeNode<E> parentOfReceiver = currentElement;

        while (receiverElement.leftChild != null) {
            parentOfReceiver = receiverElement;
            receiverElement = receiverElement.leftChild;
        }

        currentElement.element = receiverElement.element;

        if (parentOfReceiver.leftChild == receiverElement) {
            parentOfReceiver.leftChild = receiverElement.rightChild;
        } else {
            parentOfReceiver.rightChild = receiverElement.rightChild;
        }
    }

    public void remove(E e) {
        if (e == null) {
            throw new NullPointerException("Удаляемое значение не должно быть null!");
        }

        TreeNode<E> currentElement = root;
        TreeNode<E> parentElement = null;

        while (currentElement != null) {
            int comparison = e.compareTo(currentElement.element);

            if (comparison == 0) {
                if (countChildren(currentElement) == 2) {
                    removeNodeWithTwoChildren(currentElement);
                } else if (countChildren(currentElement) == 1) {
                    removeNodeWithOneChild(currentElement, parentElement);
                } else {
                    removeNodeWithoutChildren(currentElement, parentElement);
                }

                size--;
                return;

            } else if (comparison < 0) {
                parentElement = currentElement;
                currentElement = currentElement.leftChild;
            } else {
                parentElement = currentElement;
                currentElement = currentElement.rightChild;
            }
        }
    }

    public ArrayList<E> traversalDFS() {
        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<E> treeElements = new ArrayList<>();
        ArrayList<TreeNode<E>> stackElements = new ArrayList<>();
        TreeNode<E> currentElement = root;

        while (currentElement != null || !stackElements.isEmpty()) {
            while (currentElement != null) {
                stackElements.add(currentElement);
                currentElement = currentElement.leftChild;
            }
            currentElement = stackElements.removeLast();
            treeElements.add(currentElement.element);

            currentElement = currentElement.rightChild;
        }

        return treeElements;
    }

    public ArrayList<E> traversalBFS() {
        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<E> treeElements = new ArrayList<>();
        ArrayList<TreeNode<E>> queueElements = new ArrayList<>();
        queueElements.add(root);

        while (!queueElements.isEmpty()) {
            TreeNode<E> currentElement = queueElements.removeFirst();
            treeElements.add(currentElement.element);

            if (currentElement.leftChild != null) {
                queueElements.add(currentElement.leftChild);
            }

            if (currentElement.rightChild != null) {
                queueElements.add(currentElement.rightChild);
            }
        }

        return treeElements;
    }
}
