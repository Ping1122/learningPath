/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> serializedList = new ArrayList<>();
        List<TreeNode> current = new ArrayList<>();
        current.add(root);
        while (current.size() != 0) {
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : current) {
                if (node == null) {
                    serializedList.add(null);
                    continue;
                }
                serializedList.add(node.val);                
                next.add(node.left);
                next.add(node.right);
            }
            current = next;
        }
        String result = serializedList.toString().replace(" ","").replace("null","n");
        System.out.println(result);
        return result.substring(1, result.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(",");
        TreeNode root = null;
        if (!values[0].equals("n")) {
            root = new TreeNode(Integer.parseInt(values[0]));
        }
        List<TreeNode> current = new ArrayList<>();
        current.add(root);
        int index = 1;
        while (current.size() != 0) {
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : current) {
                if (node == null) continue;
                TreeNode left = null;
                TreeNode right = null;
                if (!values[index].equals("n")) {
                    left = new TreeNode(Integer.parseInt(values[index]));
                    node.left = left;
                    next.add(left);
                }
                index += 1;
                if (!values[index].equals("n")) {
                    right = new TreeNode(Integer.parseInt(values[index]));
                    node.right = right;
                    next.add(right);
                }
                index += 1;
            }
            current = next;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));