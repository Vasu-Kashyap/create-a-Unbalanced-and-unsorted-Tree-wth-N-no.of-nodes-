import java.util.*;
import java.io.*;

public class AP {



    // inserted Node : contains track of all the node present
    public static ArrayList<Node> InsertedNode = new ArrayList<Node>();

    public static class Node {

        private int data;
        private int deg;
        private ArrayList<Node> children;

        Node(int data) {
            this.data = data;
            this.deg = 0;
            this.children = new ArrayList<Node>();
        }

    }


    //finds out random parent for the new node
    public static Node getNode(ArrayList<Node> InsertedNode) {
        Node temp_num = null;
        Collections.shuffle(InsertedNode);
        for (int i = 0; i < 1; i++) {
            temp_num =  InsertedNode.get(i);
        }
        return temp_num;
    }

    public static Node constructTree(Node root,int data) {
        Node node = new Node(data);

        //find random parent
        Node suffledparent = getNode(InsertedNode);
        while (suffledparent.deg >= 5) { // if suffledparent that we found already had deg>=5 && find a new one
            suffledparent = getNode(InsertedNode);
        }



        suffledparent.children.add(node); // make new node child of suffled parent
        suffledparent.deg++;  // increase the deg of parent
        InsertedNode.add(node); // add into visited or tracked node


        return root;


    }

    //this will print node and deg in level order format (4,2) - node is 4  and it has 2 children
    static void display(Node root) {
        if (root == null)
            return;
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int n = q.size();

            while (n > 0) {

                Node p = q.removeFirst();
                System.out.print(" " + "(" + p.data + "," + p.deg + ")");

                for (int i = 0; i < p.children.size(); i++)
                    q.addLast(p.children.get(i));
                n--;
            }

            System.out.println();
        }
    }

    // this will get me unique number for the data
    public static int getNumber(ArrayList<Integer> list) {
        int temp_num = 0;
        Collections.shuffle(list);
        for (int i = 0; i < 1; i++) {
            temp_num = (list.get(i));
            list.remove(list.get(i));
        }
        return temp_num;
    }
    public static int height(Node root)
    {
        int height=0;
        for(int i=0;i<root.children.size();i++)
        {
            height = Math.max(height,height(root.children.get(i)));
        }

        return height+1;
    }
    public static void solve() {
        int N;
        Scanner myOb = new Scanner(System.in);
        N = myOb.nextInt();

        ArrayList<Integer> lis = new ArrayList<Integer>();
        for (int i = 1; i <= 1000000; i++) {
            lis.add(i);
        }

        int i=0;

        long start = System.currentTimeMillis();
        Node root = null;

        //base case handled for the first element and made it root
        if(i==0)
        {    int data = getNumber(lis);
            root= new Node(data);

            InsertedNode.add(root);
        }


        i=1;

        while (i < N) {
            int data = getNumber(lis);
            root = constructTree(root,data);
            i++;
        }

        long end = System.currentTimeMillis();


        display(root);


        System.out.println("The process takes " +
                (end - start) + "ms");

        System.out.println("height of tree is "+height(root));
    }

    public static void main(String[] args) {
        solve();

    }

}