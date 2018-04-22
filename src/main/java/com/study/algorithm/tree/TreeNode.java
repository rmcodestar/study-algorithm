package com.study.algorithm.tree;

/**
 * Created by rmcodestar on 2018. 4. 22..
 */
public class TreeNode<T> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;

    TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    TreeNode(T value) {
        this.value = value;
    }

    T getValue() {
        return this.value;
    }


    public TreeNode<T> getLeft() {
        return this.left;
    }


    public TreeNode<T> getRight() {
        return this.right;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
