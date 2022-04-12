package com.vizier.directory;

import com.vizier.stub.client.VizierStubServerClient;
import com.vizier.stub.client.VizierStubServerClientImpl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import static com.vizier.constants.VizierAgentConstants.*;

public class DirectoryInitializerImpl implements DirectoryInitializer{

    public boolean setUpDirectory() {
        String stubsDirectoryPath = "stubs";
        File file = new File(directoryPath+ File.separator+stubsDirectoryPath);
        return file.mkdirs();
    }

    public boolean createIniFile() {
        try {
            File copied = new File(iniFilePath);
            File original = new File(iniOriginalFilePath);
            FileUtils.copyFile(original, copied);
            /*File myObj = new File(iniFilePath);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
              FileWriter myWriter = new FileWriter(iniFilePath);
              myWriter.write("[mypy]\n\nmypy_path=stubs");
              myWriter.close();
              System.out.println("Successfully wrote to the file.");

            } else {
              System.out.println("File already exists.");
            }*/
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
          }
        return true;
    }

    public boolean getAllStubs(String serverAddress, String extractTo) {
        VizierStubServerClient stubServerClient = new VizierStubServerClientImpl();
        try {
            return stubServerClient.getAllStubs(serverAddress, extractTo);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createTempFile(String filePath) {
        try {
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter(filePath);
                myWriter.write("from stubs.pycell.client import vizierdb #Do not delete this line");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

} 