package org.example.lab1;

import org.example.lab1.exeptions.DuplicateModelNameException;
import org.example.lab1.exeptions.ModelPriceOutOfBoundsException;
import org.example.lab1.exeptions.NoSuchModelNameException;

import java.util.Arrays;

public class Car {
    private String brand;
    private Model[] models;
    private int modelCount;

    public Car(String brand, int capacity) {
        this.brand = brand;
        this.models = new Model[capacity];
        this.modelCount = 0;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void addModel(String modelName, double price) {
        if (modelCount == models.length) {
            models = Arrays.copyOf(models, models.length + 1);
        }
        for (Model model : models) {
            if (model != null && model.getName().equals(modelName)) {
                throw new DuplicateModelNameException("Model name already exists.");
            }
        }
        models[modelCount++] = new Model(modelName, price);
    }

    public void removeModel(String modelName) {
        int indexToRemove = -1;
        for (int i = 0; i < modelCount; i++) {
            if (models[i].getName().equals(modelName)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1) {
            throw new NoSuchModelNameException("Model name does not exist.");
        }
        for (int i = indexToRemove; i < modelCount - 1; i++) {
            models[i] = models[i + 1];
        }
        models[--modelCount] = null;
    }

    public String[] getModelNames() {
        String[] names = new String[modelCount];
        for (int i = 0; i < modelCount; i++) {
            names[i] = models[i].getName();
        }
        return names;
    }

    public double getModelPrice(String modelName) {
        for (Model model : models) {
            if (model != null && model.getName().equals(modelName)) {
                return model.getPrice();
            }
        }
        throw new NoSuchModelNameException("Model name does not exist.");
    }

    public void setModelPrice(String modelName, double price) {
        for (Model model : models) {
            if (model != null && model.getName().equals(modelName)) {
                if (price < 0) {
                    throw new ModelPriceOutOfBoundsException("Price cannot be negative.");
                }
                model.setPrice(price);
                return;
            }
        }
        throw new NoSuchModelNameException("Model name does not exist.");
    }

    public double[] getModelPrices() {
        double[] prices = new double[modelCount];
        for (int i = 0; i < modelCount; i++) {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }

    public int getModelCount() {
        return modelCount;
    }

    private class Model {
        private String name;
        private double price;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}

