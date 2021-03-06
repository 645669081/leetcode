package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author:w_liangwei
 * date:2021/1/18
 * Description:
 *
 * 解法1：还是采用原来的解法，只是将偶数层进行反转
 * 解法2：对奇数层和偶数层进行分别加入结果集，从左到右的层节点也按照先左节点再右节点入队，从右到左的层节点也按照先右节点再左节点入队，
 * 不然会发生节点间有序，但是节点内部的自己节点却发生了反转。奇数层子节点加入到对头，偶数层子节点加入到队尾，处理奇数层时去队头取，偶数层到队尾取。相当于奇数层和偶数层有约定一样
 * 都把对方说需要的节点放到了约定的地方
 *
 */
public class LevelOrder3 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);

        treeNode1.setLiftRight(treeNode2, treeNode3);
        treeNode3.setLiftRight(treeNode4, treeNode5);



//        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//
//        treeNode1.setLiftRight(treeNode2, treeNode3);
//        treeNode2.left = treeNode4;
//        treeNode3.right = treeNode5;

        List<List<Integer>> levelOrder = levelOrder(treeNode1);
        System.out.println(levelOrder);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //直接将偶数层进行反转
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> sameLayerElementList = new ArrayList<>();
            //通过使用for代替while，去除了临时list变量的使用，使新加入的下一层节点对本层遍历没有影响
            //为了使size不随着节点添加而变动，遍历节点个数采用扣减的形式，但并不是倒序遍历，因为我们还是从队列的头部弹出的元素，即i只会被最初的queueSize赋值一次，不会发生变化
            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                sameLayerElementList.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            //还是按照从左往右打印，只是分为奇数和偶数行，碰到偶数行就将当前行的结果反转
            //如果结果集现在有奇数个集合，那么说明这次的结果就是偶数行，所以要反转
            if (res.size() % 2 == 1) {
                Collections.reverse(sameLayerElementList);
            }
            res.add(sameLayerElementList);
        }
        return res;
    }
}
