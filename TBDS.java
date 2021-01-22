/*
Lindsay Dickson
101160876
 */

//Note: All of your TBDSInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TBDSInterface

import java.util.ArrayList;
import java.util.HashSet;

public class TBDS implements TBDSInterface {
    TBDSNode root;

    //Constructor
    public TBDS() {
        root = new TBDSNode();
    }

    //Indirectly recursive method to meet definition of interface
    public void add(String key, String value) {
        add(root, key, value); // calls recursive method
    }

    //Recursive method
    //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
    /**
     * adds the associated key-value pair to TBDS
     * If the key is already in the TBDS, the value is updated
     * @param current
     * @param curKey
     * @param value
     */
    public void add(TBDSNode current, String curKey, String value) {
        if(curKey.length() ==0){ // base case
            current.setValue(value);
        }else{
            current.getChildren().putIfAbsent(curKey.charAt(0), new TBDSNode()); //used to avoid null pointers / adds a new node to empty nodes
            add(current.getChildren().get(curKey.charAt(0)), curKey.substring(1),value); // recalls recursive method
        }
    }


    //Indirectly recursive method to meet definition of interface
    public String get(String key) {
        return get(root, key); //calls recursive method
    }

    //Recursive method
    //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
    /**
     * Recursive Method
     * Gets the value of the keys assocaited with the given value
     * @param current
     * @param curKey
     * @return String containing the value associated with curKey || if the key doesn't exist, null is returned
     */
    public String get(TBDSNode current, String curKey) {
        if (curKey.length()==0){ //base case
            return current.getValue();
        }
        if (!current.getChildren().containsKey(curKey.charAt(0))){
            return null; //key is not in TBDS
        }
        return get(current.getChildren().get(curKey.charAt(0)),curKey.substring(1)); //Recalls the recursive method
    }

    //Indirectly recursive method to meet definition of interface
    public boolean containsKey(String key) {
        return containsKey(root, key); //calls recursive method
    }

    //Recursive method
    //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
    /**
     *Recursive method
     * Checks if the TBDS contains the given key
     * @param current
     * @param curKey
     * @return true if TBDS contains the given key || otherwise, returns false
     */
    public boolean containsKey(TBDSNode current, String curKey) {
        if(curKey.length() == 0){ //Base case
            if(current.getValue() == null){
                return false; //The key has not been found
            }else{
                return true; //the key has been found
            }
        }
        if (!current.getChildren().containsKey(curKey.charAt(0))){
            return false; //Key has not been found
        }
        return containsKey(current.getChildren().get(curKey.charAt(0)),curKey.substring(1)); //Recalls recursive method
    }

    //Indirectly recursive method to meet definition of interface
    public ArrayList<String> getKeysForPrefix(String prefix) {
        if (findNode(root,prefix)!=null){
            return getSubtreeKeys(findNode(root,prefix), new ArrayList<String>());  //Calls the recursive helper functions getSubtreeKeys and findNode
        }
        return new ArrayList<String>();
    }

    //Recursive helper function to find node that matches a prefix
    //Note: only a suggestion, you may solve the problem in any recursive manner
    /**
     * recursively compares the values looking for matching nodes
     * @param current
     * @param curKey
     * @return current when we've reached the end of the curKey we're looking for || recalls the function when there is a match || if node cannot be found, returns null
     */
    public TBDSNode findNode(TBDSNode current, String curKey) {
        if (curKey.length() == 0){ //base case
            return current;
        }
        if (current.getChildren().containsKey(curKey.charAt(0))){  //if prefix is within tbds
            return findNode(current.getChildren().get(curKey.charAt(0)), curKey.substring(1)); //Recalls recursive method
        }
        //if no matching node
        return null;
    }

    //Recursive helper function to get all keys in a node's subtree
    //Note: only a suggestion, you may solve the problem in any recursive manner
    /**
     * Recursive method
     * Gathers all the string values associated with the nodes below the current node and stores within SubTreeKeys
     * @param current
     * @param subTreeKeys
     * @return ArrayList subTreeKeys containing values of the nodes below the given 'current' node
     */
    public ArrayList<String> getSubtreeKeys(TBDSNode current, ArrayList<String> subTreeKeys) {
        if(current == null){ //Base case
            return subTreeKeys;
        }
        if (current.getValue() != null){
            subTreeKeys.add(current.getValue());
        }
        if (current.getChildren().isEmpty()){ //Base case
            return subTreeKeys;
        }
        for (TBDSNode node : current.getChildren().values()){
            getSubtreeKeys(node, subTreeKeys); //Recalls recursive method
        }
        return subTreeKeys;
    }

    //Indirectly recursive method to meet definition of interface
    public void print() {
        print(root); //calls recursive method
    }

    //Recursive method to print values in tree
    public void print(TBDSNode current) {
        if (current.getValue() != null) {
            System.out.println(current.getValue());
        }
        for (TBDSNode node : current.getChildren().values()) {
            print(node);
        }
    }


    public static void main(String[] args) {
        //You can add some code in here to test out your TBDS initially
        //The TBDSTester includes a more detailed test

    }
}