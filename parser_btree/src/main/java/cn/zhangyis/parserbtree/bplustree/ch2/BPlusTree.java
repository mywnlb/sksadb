package cn.zhangyis.parserbtree.bplustree.ch2;

public class BPlusTree {
    int m;


    public class Node {
        InternalNode parent;
    }

    private class InternalNode extends Node {
        int maxDegree;
        int minDegree;
        int degree;
        InternalNode leftSibling;
        InternalNode rightSibling;
        Integer[] keys;
        Node[] childPointers;

        private InternalNode(int m,Integer[] keys){
            this.maxDegree = m;
            this.minDegree = (int) Math.ceil(m/2.0);
            this. degree = 0;
            this.keys = keys;
            this.childPointers = new Node[this.maxDegree-1];
        }

        private InternalNode(int m,Integer[] keys,Node[] pointers){
            this.maxDegree = m;
            this.minDegree = (int) Math.ceil(m/2.0);
            this.keys = keys;
            this.childPointers = pointers;
        }
    }
}
