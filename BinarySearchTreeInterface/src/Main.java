import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner keyboard = new Scanner(System.in);
        Boolean quitMain = false;
        Boolean quitSingleTable = true;
        DecimalFormat df = new DecimalFormat("#.##");

        int mainMenuOption;


        System.out.println("Welcome to The Binary Search Tree Interface application");
        System.out.println();


        while(!quitMain) {


            System.out.println("Enter 1 to test different BST operations using an NFL Player Class");
            System.out.println();
            System.out.println("Enter 2 to generate multiple trees ranging in size from 131,072 to 2,097,152 and display their stats");
            System.out.println();
            System.out.println("Enter 0 to quit");
            System.out.println();
            mainMenuOption = keyboard.nextInt();

            switch(mainMenuOption) {

                case 1:

                    quitSingleTable = false;
                    Tree table = new Tree();

                    int subMenuOption;

                    NFLPlayer tempPlayer;
                    NFLPlayerKey tempKey;
                    int jerseyNumber;
                    String teamName;

                    while(!quitSingleTable) {

                        System.out.println("Enter 1 to create a new table");
                        System.out.println();
                        System.out.println("Enter 2 to insert a new NFL Player");
                        System.out.println();
                        System.out.println("Enter 3 to search for a player");
                        System.out.println();
                        System.out.println("Enter 4 to delete a player");
                        System.out.println();
                        System.out.println("Enter 5 to get the size of the table");
                        System.out.println();
                        System.out.println("Enter 6 to get the height of the table");
                        System.out.println();
                        System.out.println("Enter 7 to get the average level of the table");
                        System.out.println();
                        System.out.println("Enter 8 to display the table as a tree");
                        System.out.println();
                        System.out.println("Enter 9 to display the table with toString");
                        System.out.println();
                        System.out.println("Enter 0 to quit");

                        subMenuOption = keyboard.nextInt();

                        switch (subMenuOption) {

                            case 1:

                                System.out.println("Creating a new table will erase any previous data");
                                System.out.println();
                                System.out.println("Enter 1 to proceed, and anything else to cancel");
                                System.out.println();
                                int delete = keyboard.nextInt();

                                if(delete == 1) {

                                    table = new Tree();

                                    System.out.println("Empty table created");

                                } else {

                                    System.out.println("Cancelled");
                                }

                                System.out.println();
                                break;

                            case 2:

                                System.out.println("Enter the player's jersey number");
                                jerseyNumber = keyboard.nextInt();
                                System.out.println();
                                System.out.println("Enter the player's team name");
                                teamName = keyboard.next();
                                System.out.println();
                                System.out.println("Enter the player's last name");
                                String playerName = keyboard.next();
                                System.out.println();
                                System.out.println("Enter the players salary");
                                double salary = keyboard.nextDouble();

                                tempPlayer = new NFLPlayer(jerseyNumber, teamName, playerName, salary);

                                table.insert(tempPlayer);

                                System.out.println();
                                System.out.println(tempPlayer.toString() + "\nhas been inserted");
                                break;


                            case 3:

                                System.out.println("Enter the jersey number");
                                jerseyNumber = keyboard.nextInt();
                                System.out.println();
                                System.out.println("Enter the team name");
                                teamName = keyboard.next();
                                System.out.println();

                                tempKey = new NFLPlayerKey(jerseyNumber, teamName);

                                System.out.println(table.search(tempKey));
                                break;

                            case 4:

                                System.out.println("Enter the jersey number");
                                jerseyNumber = keyboard.nextInt();
                                System.out.println();
                                System.out.println("Enter the team name");
                                teamName = keyboard.next();
                                System.out.println();

                                tempKey = new NFLPlayerKey(jerseyNumber, teamName);

                                table.delete(tempKey);

                                System.out.println(tempKey + " has been deleted.");
                                break;


                            case 5:

                                System.out.println();
                                System.out.println("Size : " + table.getSize());
                                System.out.println();
                                break;

                            case 6:

                                System.out.println();
                                System.out.println("Height : " + table.getHeight());
                                System.out.println();
                                break;

                            case 7:

                                System.out.println();
                                System.out.println("Average level : " + df.format(table.getAverageLevel()));
                                System.out.println();
                                break;

                            case 8:

                                table.showTree();
                                break;

                            case 9:

                                System.out.println(table.toString());
                                break;

                            case 0:
                                quitSingleTable = true;
                                break;

                            default:
                                System.out.println("Enter a valid option");
                                break;

                        }


                    }

                    break;

                case 2:

                    Random generator = new Random();

                    for(int i = 17; i <= 21; ++i) {

                        for(int j = 1; j <= 5; ++j) {

                            Tree InfinityTree = new Tree();

                            for(int k = 1; k <= Math.pow(2, i); ++k) {

                                KeyedNumber temp = new KeyedNumber(generator.nextInt());

                                InfinityTree.insert(temp);

                            }

                            System.out.println("---------------------");
                            System.out.println();
                            System.out.println("Data size : " + Math.pow(2, i));
                            System.out.println("Height : " + InfinityTree.getHeight());
                            System.out.println("Avg. Lvl : " + df.format(InfinityTree.getAverageLevel()));
                            System.out.println();
                            System.out.println("---------------------");

                        }

                    }

                    break;

                case 0:

                    quitMain = true;
                    break;

                default:

                    System.out.println("Enter a valid option");
                    break;


            }



        }


    }
}

class TNode {

    Keyed data;
    TNode left;
    TNode right;

    public TNode(Keyed _data) {

        data = _data;
        left = null;
        right = null;

    }

}


class Tree {

    private TNode root;


    public Tree() {

        root = null;

    }


    public void invert() {

        this.root = invert(this.root);

    }

    private TNode invert(TNode root) {

        if (root.left != null || root.right != null) {


            TNode tempLeft = root.left;
            root.left = invert(root.right);
            root.right = invert(tempLeft);

        }

        return root;


    }


    public void insert(Keyed newItem) {

        root = insert(newItem, root);


    }

    private TNode insert(Keyed newItem, TNode _root) {

        if(_root == null) {

           _root = new TNode(newItem);

        } else if(newItem.keyComp(_root.data) < 0) {

            _root.left = insert(newItem, _root.left);

        } else if(newItem.keyComp(_root.data) > 0) {

           _root.right = insert(newItem, _root.right);

        }

        return _root;

    }

    public Keyed search(Keyed key) {

        return search(key, root);

    }

    private Keyed search(Keyed key, TNode root) {

        if(root == null) {

            return null;

        }

        if(key.keyComp(root.data) == 0) {

            return root.data;

        } else if(key.keyComp(root.data) < 0) {

            return search(key, root.left);

        } else {

            return search(key, root.right);

        }

    }

    public void delete(Keyed itemToDelete) {

       root = delete(itemToDelete, root);

    }

    private TNode delete(Keyed itemToDelete, TNode _root) {

        if(_root == null) {

            return null;

        }


        if(itemToDelete.keyComp(_root.data) < 0) {

            _root.left = delete(itemToDelete, _root.left);

        } else if(itemToDelete.keyComp(_root.data) > 0) {

            _root.right = delete(itemToDelete, _root.right);

        } else {

            if(_root.left != null && _root.right != null) {

                if(getSize(_root.left) > getSize(_root.right)) {

                    Keyed maximum = findMaximumNode(_root.left).data;
                    delete(maximum, _root);
                    _root.data = maximum;

                } else {

                    Keyed minimum = findMinimumNode(_root.right).data;
                    delete(minimum, _root);
                    _root.data = minimum;

                }


            } else if(_root.left != null) {

                _root = _root.left;

            } else if(_root.right != null) {

                _root = _root.right;

            } else {

                _root = null;

            }

        }

        return _root;

    }

    public double getAverageLevel() {

        return (double) getSumOfLevels() / getSize();

    }

    public int getSize() {

        return getSize(root);

    }

    private int getSize(TNode _root) {
        int size = 0;

        if(_root != null) {

            ++size;
            size += getSize(_root.left);
            size += getSize(_root.right);

        }

        return size;

    }

    private int getSumOfLevels() {

        return getSumOfLevels(root, 1);

    }

    private int getSumOfLevels(TNode _root, int level) {

        if(_root == null) {

            return 0;
        }

        int sum = level;

        sum += getSumOfLevels(_root.left, level + 1);
        sum += getSumOfLevels(_root.right, level + 1);

        return sum;

    }


    public int getHeight() {

        return getHeight(root);

    }

    private int getHeight(TNode _root) {

        if(_root == null) {

            return 0;

        } else {

            int leftHeight = getHeight(_root.left);
            int rightHeight = getHeight(_root.right);

            if(leftHeight > rightHeight) {

                return leftHeight + 1;

            } else {

                return rightHeight + 1;

            }
        }

    }


    private TNode findMinimumNode(TNode _root) {

            if(_root.left != null) {

                _root = findMinimumNode(_root.left);

            }

            return _root;

    }

    private TNode findMaximumNode(TNode _root) {

        if(_root.right != null) {

            _root = findMaximumNode(_root);

        }

        return _root;
    }

    public void showTree() {

        showTree(root, 1);

    }

    private void showTree(TNode _root, int level) {

        if(_root == null) {
            return;
        }

        String tabs = "";

        for(int i = 0; i < (level -1) * 2; ++i) {

            tabs += "\t";

        }

        showTree(_root.right, level + 1);
        System.out.println(tabs + _root.data.toStr() + "\n");
        showTree(_root.left, level + 1);

    }

    public String toString() {

       return toString(root);

    }

    private String toString(TNode _root) {

        if(_root == null) {

            return "";

        }

        String s = "";

        s += toString( _root.left);
        s += _root.data.toStr() + "\n";
        s += toString( _root.right);

        return s;

    }

}


interface Keyed {


    int keyComp(Keyed other);

    String toStr();

}

class KeyedNumber implements Keyed {

    private int key;

    public KeyedNumber(int _key) {

        key = _key;

    }


    @Override
    public int keyComp(Keyed other) {

        if(other instanceof KeyedNumber) {

            KeyedNumber convertedOther = (KeyedNumber) other;

            return key - convertedOther.key;

        }

        return 0;

    }

    @Override
    public String toStr() {

        return "" + key;

    }
}

class NFLPlayerKey implements Keyed {

    private int jerseyNumber;
    private String teamName;

    public NFLPlayerKey(int _jerseyNumber, String _teamName) {

        jerseyNumber = _jerseyNumber;
        teamName = _teamName;

    }

    public int getJerseyNumber() {

        return jerseyNumber;

    }

    public String getTeamName() {

        return teamName;

    }

    @Override
    public int keyComp(Keyed other) {

        if(other instanceof NFLPlayerKey) {

            NFLPlayerKey convertedOther = ((NFLPlayerKey) other);

            int teamNameCompare = teamName.compareTo(convertedOther.teamName);

            if(teamNameCompare == 0) {

                return jerseyNumber - convertedOther.jerseyNumber;

            } else {

                return teamNameCompare;
            }

        }

        return 0;

    }

    @Override
    public String toStr() {

        return jerseyNumber + "  " + teamName.substring(0,3);

    }

    @Override
    public String toString() {

        return "NFLPlayerKey{" +
                "jerseyNumber=" + jerseyNumber +
                ", teamName='" + teamName + '\'' +
                '}';

    }
}

class NFLPlayer extends NFLPlayerKey implements Keyed  {

    private String name;
    private double salary;

    public NFLPlayer(int _jerseyNumber, String _teamName, String _name, double _salary) {

        super(_jerseyNumber, _teamName);
        name = _name;
        salary = _salary;

    }

    public String getName() {

        return name;

    }

    public double getSalary() {

        return salary;

    }

    @Override
    public String toString() {

        return "NFLPlayer{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                "} " + super.toString();

    }
}



