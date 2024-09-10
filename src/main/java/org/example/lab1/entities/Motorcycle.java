package org.example.lab1.entities;

import org.example.lab1.exeptions.ModelPriceOutOfBoundsException;
import org.example.lab1.exeptions.NoSuchModelNameException;
import org.example.lab1.interfaces.Transport;

public class Motorcycle implements Transport {
    private static class Model {
        String name;
        double price;
        Model prev;
        Model next;

        Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private final Model head;
    private int size;
    private long lastModified;

    public Motorcycle() {
        this.head = new Model(null, 0);
        this.head.next = this.head;
        this.head.prev = this.head;
        this.size = 0;
        this.lastModified = System.currentTimeMillis();
    }

    public void addModel(String modelName, double price) {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной");
        }
        Model newModel = new Model(modelName, price);
        if (size == 0) {
            head.next = newModel;
            newModel.next = head;
            newModel.prev = head;
            head.prev = newModel;
        } else {
            Model last = head.prev;
            last.next = newModel;
            newModel.prev = last;
            newModel.next = head;
            head.prev = newModel;
        }
        size++;
        lastModified = System.currentTimeMillis();
    }

    public void removeModel(String modelName) {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                lastModified = System.currentTimeMillis();
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Название модели не существует");
    }

    public String[] getModelNames() {
        String[] names = new String[size];
        Model current = head.next;
        int i = 0;
        while (current != head) {
            names[i++] = current.name;
            current = current.next;
        }
        return names;
    }

    public double getModelPrice(String modelName) {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                return current.price;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Название модели не существует");
    }

    public void setModelPrice(String modelName, double price) {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной");
        }
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                current.price = price;
                lastModified = System.currentTimeMillis();
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Название модели не существует");
    }

    public double[] getModelPrices() {
        double[] prices = new double[size];
        Model current = head.next;
        int i = 0;
        while (current != head) {
            prices[i++] = current.price;
            current = current.next;
        }
        return prices;
    }

    public int getModelCount() {
        return size;
    }

    public long getLastModified() {
        return lastModified;
    }
}
