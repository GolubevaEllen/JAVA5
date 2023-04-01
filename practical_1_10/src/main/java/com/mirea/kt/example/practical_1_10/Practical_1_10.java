/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mirea.kt.example.practical_1_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author user
 */
public class Practical_1_10 {

    public static void main(String[] args) {
        System.out.println("РИБО-04-21 Практическая работа 1.10 Вариант 3 Голуева А.П.");
        
        try { 
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
            System.out.print("Введите путь к файлу: "); 
            String filePath = reader.readLine(); 
            System.out.print("Введите гамму в шестнадцатеричном формате: "); 
            String gammaHex = reader.readLine(); 
            FileInputStream fis = new FileInputStream(filePath); 
            byte[] fileContent = new byte[fis.available()]; 
            fis.read(fileContent); 
            fis.close(); 
            
            int len = filePath.length(); 
            byte[] data = new byte[len / 2]; 
            for (int i = 0; i < len; i += 2) { 
                data[i / 2] = (byte) ((Character.digit(filePath.charAt(i), 16) << 4) 
                                     + Character.digit(filePath.charAt(i+1), 16)); 
            }
            byte[] gamma = data;
            for (int i = 0; i < fileContent.length; i++) { 
                fileContent[i] ^= gamma[i % gamma.length]; 
            }
              
            File outputFile = new File("output.bin"); 
            FileOutputStream fos = new FileOutputStream(outputFile); 
            fos.write(fileContent); 
            fos.close(); 
             
            System.out.println("Сохранено в файл '" + outputFile.getAbsolutePath()); 
        } catch (IOException e) { 
            System.out.println("Ошибка! " + e.getMessage()); 
        } 
    }
}
