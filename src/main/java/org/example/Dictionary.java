package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary<K, V> {
    protected Map<K, V> dictionary;
    protected File file;


    public Dictionary(String pathToDictionary) {
        dictionary = new HashMap<>();
        file = new File(pathToDictionary);
        readerFile();
    }


    public V findByKey(K key){
        if (dictionary.containsKey(key)){
            return dictionary.get(key);
        }
        throw new IllegalArgumentException("Данного значения не существует");
    }

    public V putWord(K key, V value){
        V returnValue = dictionary.put(key, value);
        writeFile();
        return returnValue;
    }

    public V deleteByKey(K key){
        if (dictionary.containsKey(key)){
            V returnValue = dictionary.remove(key);
            writeFile();
            return returnValue;
        }
        throw new IllegalArgumentException("Данного значения не существует :( ");
    }

    private void readerFile(){
        if (!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при попытке создания словаря по пути: " + file.getPath());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String line = reader.readLine().trim();
                if (!line.equals("=")){
                    String[] keyAndValue = line.split("=");
                    dictionary.put((K) keyAndValue[0],(V) keyAndValue[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении словаря из файла");
        }
    }

    protected void writeFile(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : dictionary.entrySet()){
            sb.append(entry.toString());
            sb.append("\n");
        }
        try (FileWriter writer = new FileWriter(file)){
            writer.append(sb);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при выводе словаря в файл");
        }
    }

    public void printToConsole(){
        System.out.println("----------------");
        for(Map.Entry<K,V> entry : dictionary.entrySet()){
            System.out.println(entry);
        }
        System.out.println("----------------");
    }

}
