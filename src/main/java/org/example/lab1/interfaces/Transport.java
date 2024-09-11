package org.example.lab1.interfaces;

public interface Transport {
    void addModel(String modelName, double price);
    void removeModel(String modelName);
    String[] getModelNames();
    double getModelPrice(String modelName);
    void setModelPrice(String modelName, double price);
    double[] getModelPrices();
    int getModelCount();

    String getBrand();
}
