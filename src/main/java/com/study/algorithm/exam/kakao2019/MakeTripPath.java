package com.study.algorithm.exam.kakao2019;

import java.util.*;
import java.util.stream.Collectors;

public class MakeTripPath {
    public class TreeNode {
        public int index;
        public Point point;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int index, Point point) {
            this.index = index;
            this.point = point;
        }
    }

    public class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Comparator<TreeNode> heightDesc = new Comparator<TreeNode>() {
        @Override
        public int compare(TreeNode t1, TreeNode t2) {
            return Integer.compare(t2.point.y, t1.point.y);
        }
    };

    public int[][] solution(int[][] nodeinfo) {
        List<TreeNode> treeNodes = new ArrayList<>();

        for (int row = 0; row < nodeinfo.length; row++) {
            int x = nodeinfo[row][0];
            int y = nodeinfo[row][1];

            treeNodes.add(new TreeNode(row + 1, new Point(x, y)));
        }

        if (treeNodes.size() == 1) {
            return new int[][]{{1}, {1}};
        }

        treeNodes.sort(heightDesc);
        TreeNode root = treeNodes.get(0);
        treeNodes.remove(0);

        makeTree(root, treeNodes.stream().filter((point) -> point.point.x < root.point.x).collect(Collectors.toList())
                , treeNodes.stream().filter((point) -> point.point.x > root.point.x).collect(Collectors.toList()));

        List<Integer> preorderd = preorder(root);
        List<Integer> postordered = postorder(root);

        int[][] result = new int[2][nodeinfo.length];

        for (int index = 0; index < preorderd.size(); index++) {
            result[0][index] = preorderd.get(index);
        }


        for (int index = 0; index < postordered.size(); index++) {
            result[1][index] = postordered.get(index);
        }

        return result;
    }

    public void makeTree(TreeNode current, List<TreeNode> lefts, List<TreeNode> rights) {
        if (lefts.size() > 0) {
            lefts.sort(heightDesc);
        }

        if (rights.size() > 0) {
            rights.sort(heightDesc);
        }

        current.left = (lefts.size() > 0) ? lefts.get(0) : null;
        current.right = (rights.size() > 0) ? rights.get(0) : null;

        if (Objects.nonNull(current.left)) {
            final int x = current.left.point.x;
            makeTree(current.left, lefts.stream().filter((point) -> point.point.x < x).collect(Collectors.toList())
                    , lefts.stream().filter((point) -> point.point.x > x).collect(Collectors.toList()));
        }

        if (Objects.nonNull(current.right)) {
            final int x = current.right.point.x;
            makeTree(current.right, rights.stream().filter((point) -> point.point.x < x).collect(Collectors.toList())
                    , rights.stream().filter((point) -> point.point.x > x).collect(Collectors.toList()));
        }

    }


    private List<Integer> preorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            result.add(stack.peek().index);

            TreeNode current = stack.pop();  //visited

            TreeNode left = current.left;
            TreeNode right = current.right;

            if (Objects.nonNull(right)) {
                stack.push(right);
            }

            if (Objects.nonNull(left)) {
                stack.push(left);
            }

        }

        return result;
    }


    private List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> secondStack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            secondStack.push(current);

            TreeNode left = current.left;
            TreeNode right = current.right;

            if (Objects.nonNull(left)) {
                stack.push(left);
            }

            if (Objects.nonNull(right)) {
                stack.push(right);
            }
        }

        while (!secondStack.isEmpty()) {
            result.add(secondStack.peek().index);
            secondStack.pop();  //visited
        }

        return result;
    }

}
