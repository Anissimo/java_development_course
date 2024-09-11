package org.example.lab1.utilities;

import org.example.lab1.entities.Car;
import org.example.lab1.exeptions.DuplicateModelNameException;
import org.example.lab1.interfaces.Transport;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class TransportUtils {
    public static double getAveragePrice(Transport transport) {
        double[] prices = transport.getModelPrices();
        if (prices.length == 0) return 0;
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static void printAllModels(Transport transport) {
        String[] models = transport.getModelNames();
        for (String model : models) {
            System.out.println(model);
        }
    }

    public static void printAllPrices(Transport transport) {
        double[] prices = transport.getModelPrices();
        for (double price : prices) {
            System.out.println(price);
        }
    }

//    вторая лаба уже
    public static void outputTransportVehicle(Transport vehicle, OutputStream out) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(out);
        dataOut.writeInt(vehicle.getModelCount());
        for (String modelName : vehicle.getModelNames()) {
            byte[] modelNameBytes = modelName.getBytes();
            dataOut.writeInt(modelNameBytes.length);
            dataOut.write(modelNameBytes);
            dataOut.writeDouble(vehicle.getModelPrice(modelName));
        }
    }

    public static Transport inputTransportVehicle(InputStream in) throws IOException, DuplicateModelNameException {
        DataInputStream dataIn = new DataInputStream(in);
        String brand = dataIn.readUTF();
        int modelCount = dataIn.readInt();
        Transport vehicle = new Car(brand, modelCount);
        for (int i = 0; i < modelCount; i++) {
            int nameLength = dataIn.readInt();
            byte[] nameBytes = new byte[nameLength];
            dataIn.readFully(nameBytes);
            String modelName = new String(nameBytes);
            double price = dataIn.readDouble();
            vehicle.addModel(modelName, price);
        }
        return vehicle;
    }

    
    public static void writeTransportVehicle(Transport vehicle, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(vehicle.getBrand());
        writer.println(vehicle.getModelCount());
        for (String modelName : vehicle.getModelNames()) {
            writer.println(modelName);
            writer.println(vehicle.getModelPrice(modelName));
        }
    }

    public static Transport readTransportVehicle(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader reader = new BufferedReader(in);
        String brand = reader.readLine();
        int modelCount = Integer.parseInt(reader.readLine());
        Transport vehicle = new Car(brand, modelCount);
        for (int i = 0; i < modelCount; i++) {
            String modelName = reader.readLine();
            double price = Double.parseDouble(reader.readLine());
            vehicle.addModel(modelName, price);
        }
        return vehicle;
    }

}
