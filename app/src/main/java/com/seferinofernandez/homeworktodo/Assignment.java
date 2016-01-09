package com.seferinofernandez.homeworktodo;

/**
 * @author Seferino Fernandez
 *
 */
public class Assignment implements Comparable<Assignment> {
    private String assignName, dueDate, className;

    /**Basic Constructor
     *
     * @param assignName
     * @param dueDate  MM/DD/YYYY
     * @param className
     */
    public Assignment(String assignName, String dueDate, String className) {
        this.assignName = assignName;
        this.dueDate = dueDate;
        this.className = className;
    }

    /**
     * @return the assignName
     */
    public String getAssignName() {
        return assignName;
    }

    /**
     * @param assignName the assignName to set
     */
    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    /**
     * @return the dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    //Creates a string representation of the object. Adds in '&' to be filtered out.
    public String toString() {
        return new StringBuilder().append(getAssignName()).append("&").append(getDueDate()).append("&").append(getClassName()).toString();
    }

    /*
    Used to compare objects by their due dates.
    Needed to be sorted as well.
    */
    public int compareTo(Assignment other) {
        String[] str = dueDate.split("\\/");
        int myMonth = Integer.parseInt(str[0]);
        int myDay = Integer.parseInt(str[1]);
        int myYear = Integer.parseInt(str[2]);

        String[] str2 = other.getDueDate().split("\\/");
        int theirMonth = Integer.parseInt(str2[0]);
        int theirDay = Integer.parseInt(str2[1]);
        int theirYear = Integer.parseInt(str2[2]);

        if(myYear > theirYear) {
            return -1;
        } else if(myYear == theirYear) {
            if(myMonth > theirMonth) {
                return -1;
            } else if (myMonth == theirMonth) {
                if(myDay > theirDay) {
                    return -1;
                } else if(myDay == theirDay) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
        return 1;
    }
}
