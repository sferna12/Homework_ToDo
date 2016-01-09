package com.seferinofernandez.homeworktodo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Seferino Fernandez
 *
 */
public class AssignList {
    ArrayList<Assignment> myList = new ArrayList<Assignment>();

    //Empty Constructor
    public AssignList(){}

    /*
    Constructor that takes in an ArrayList<Strings>
    Used to write and read files.
    */
    public AssignList(ArrayList<String> stringList) {
        for (String item: stringList) {
            String[] str = item.split("\\&");
            //This makes the program work...
            if(str.length >= 3) {
                myList.add(new Assignment(str[0],str[1],str[2]));
            }
        }
    }

    public boolean add(Assignment homework) {
        myList.add(homework);
        reorderList();
        return true;
    }
    public boolean remove(String aName) {
        for(Assignment homework : myList)
            if(aName.equals(homework.getAssignName())) {
                return myList.remove(homework);
            }
        return true;
    }
    public Assignment get(String aName) {
        int count = 0;
        for(Assignment homework : myList)
            if(aName.equals(homework.getAssignName())) {
                return myList.get(count);
            }
            else
                count += 1;
        return null;
    }
    public String[] getNames() {
        String[] nameList = new String[myList.size()];
        for(int i = 0; i < myList.size(); i++)
            nameList[i] = myList.get(i).getAssignName();
        return nameList;
    }
    public void printAssign() {
        String[] newList = getNames();
        System.out.print("Printing assignments: ");
        for (String aNewList : newList) {
            System.out.print(aNewList + ", ");
        }
    }

    public void setMyList(ArrayList<Assignment> myList) {
        this.myList = myList;
    }

    public ArrayList<Assignment> getMyList() {
        return myList;
    }
    public ArrayList<String> getStringList() {
        ArrayList<String> stringList = new ArrayList<String>();
        for(int i = 0; i < myList.size(); i++) {
            stringList.add(myList.get(i).toString());
        }
        return stringList;
    }

    private void reorderList() {
        Collections.sort(myList);
        Collections.reverse(myList);
    }
}