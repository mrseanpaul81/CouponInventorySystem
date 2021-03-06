package com.jeannius.cs401.project;

import com.jeannius.cs401.project.myInterfaces.ListInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class RefSortedList<T extends Comparable<T>> extends RefUnsortedList<T> implements ListInterface<T> {


    public RefSortedList() {
        super();
    }


    @Override
    public void add(T element) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SListNode<T> previousLocation, location;
        T currentElement;
//        System.out.println("addddinnngggg ttoooooo sooooorrrrteeedddd!!!!");

        location = firstNode;
        previousLocation = null;


        while (location != null) {
            currentElement = location.getElement();
            int comp=0;
            System.out.printf("\n\n\nElement Class: %s\n\n\n",element.getClass().toString());

            if(element instanceof Coupon){
                Method method = Coupon.class.getMethod(((Coupon) element).getMethodNameToIterate(),null);

                Object objNew = method.invoke(element);
                Object objCurrent = method.invoke(currentElement);

                if(objCurrent instanceof String) comp = ((String)objCurrent).compareTo((String)objNew);
                else if(objCurrent instanceof Integer) comp = Integer.compare((Integer)objCurrent,(Integer) objNew);
                System.out.printf("\n\nObjNew: %s\tObjCurrent: %s\tcomp: %d\n\n", String.valueOf(objNew), String.valueOf(objCurrent), comp);

            }
            else {
                comp = currentElement.compareTo(element);
                System.out.printf("\n\n\nNot coupon!\n\n\n");
            }

            if (comp < 0) {
                previousLocation = location;
                location = location.getNext();
            } else break;
        }


        SListNode<T> newNode = new SListNode<T>(element, null);
        if (previousLocation == null) {
            newNode.setNext(firstNode);
            firstNode = newNode;
            currentPosition = firstNode;
        } else {
            newNode.setNext(location);
            previousLocation.setNext(newNode);
            currentPosition = firstNode;
        }
        numberOfElements++;
        notifyListeners();
        if (firstNode==null)System.out.println("First NODE NULL");
        if(currentPosition==null) System.out.println("Current node null!!!");
//        showStructure();

    }



}
