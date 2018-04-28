package com.study.algorithm.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by rmcodestar on 2018. 4. 22..
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class TreeNodeTraversalTest {
    private static TreeNode<String> root;

    private class VisibleStack<E> extends Stack<E> {
        @Override
        public E pop() {
            System.out.print(MessageFormat.format("[{0}] -> ", super.peek()));
            return super.pop();
        }
    }

    public class VisibleQueue<E> extends LinkedList<E> {
        @Override
        public E poll() {
            System.out.print(MessageFormat.format("[{0}] -> ", super.peekFirst()));
            return super.poll();
        }
    }

    @BeforeClass
    public static void makeTree() {
        TreeNode<String> d = new TreeNode<>("D", new TreeNode<>("H"), new TreeNode<>("I"));
        TreeNode<String> e = new TreeNode<>("E", new TreeNode<>("J"), new TreeNode<>("K"));
        TreeNode<String> f = new TreeNode<>("F", new TreeNode<>("L"), null);
        TreeNode<String> g = new TreeNode<>("G");
        TreeNode<String> b = new TreeNode<>("B", d, e);
        TreeNode<String> c = new TreeNode<>("C", f, g);

        root = new TreeNode<>("A", b, c);
    }

    /**
     * DLR
     */
    @Test
    public void preorder_using_stack() {
        Stack<TreeNode<String>> stack = new VisibleStack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<String> current = stack.pop();  //visited

            TreeNode<String> left = current.getLeft();
            TreeNode<String> right = current.getRight();

            if (Objects.nonNull(right)) {
                stack.push(right);
            }

            if (Objects.nonNull(left)) {
                stack.push(left);
            }

        }

    }

    @Test
    public void preorder_recursive() {
        preorder(root);
    }

    private void preorder(TreeNode<String> startNode) {
        if (Objects.isNull(startNode)) {
            return;
        }

        System.out.print(MessageFormat.format("[{0}] -> ", startNode.getValue()));
        preorder(startNode.getLeft());
        preorder(startNode.getRight());
    }

    /**
     * LDR
     */
    @Test
    public void inorder_usring_stack() {
        Stack<TreeNode<String>> stack = new VisibleStack<>();

        TreeNode<String> current = root;

        while (true) {
            while (Objects.nonNull(current)) {
                stack.push(current);
                current = current.getLeft();
            }

            if (stack.isEmpty()) {
                break;
            }

            current = stack.pop();  //visited
            current = current.getRight();
        }
    }

    @Test
    public void inorder_recursive() {
        inorder(root);
    }

    private void inorder(TreeNode<String> startNode) {
        if (Objects.isNull(startNode)) {
            return;
        }

        inorder(startNode.getLeft());
        System.out.print(MessageFormat.format("[{0}] -> ", startNode.getValue()));
        inorder(startNode.getRight());
    }

    /**
     * LRD
     */
    @Test
    public void postorder_using_two_stack() {
        Stack<TreeNode<String>> stack = new Stack<>();
        Stack<TreeNode<String>> secondStack = new VisibleStack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<String> current = stack.pop();
            secondStack.push(current);

            TreeNode<String> left = current.getLeft();
            TreeNode<String> right = current.getRight();

            if (Objects.nonNull(left)) {
                stack.push(left);
            }

            if (Objects.nonNull(right)) {
                stack.push(right);
            }
        }

        while (!secondStack.isEmpty()) {
            secondStack.pop();  //visited
        }

    }

    @Test
    public void postorder_recursive() {
        postorder(root);
    }

    private void postorder(TreeNode<String> startNode) {
        if (Objects.isNull(startNode)) {
            return;
        }

        postorder(startNode.getLeft());
        postorder(startNode.getRight());
        System.out.print(MessageFormat.format("[{0}] -> ", startNode.getValue()));
    }


    @Test
    public void levelorder() {
        Queue<TreeNode<String>> targetQueue = new VisibleQueue<>();

        targetQueue.add(root);

        while (!targetQueue.isEmpty()) {
            TreeNode<String> current = targetQueue.poll();  //visited

            if (Objects.nonNull(current.getLeft())) {
                targetQueue.add(current.getLeft());
            }

            if (Objects.nonNull(current.getRight())) {
                targetQueue.add(current.getRight());
            }
        }

    }

}