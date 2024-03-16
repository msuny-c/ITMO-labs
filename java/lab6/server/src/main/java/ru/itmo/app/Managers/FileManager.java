package ru.itmo.app.Managers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.itmo.app.Exceptions.EmptyFileException;
import ru.itmo.app.Exceptions.IllegalPathException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileManager {
    private final String ENV_VAR;
    public FileManager(String env_var) {
        ENV_VAR = env_var;
    }

    private File searchFile() throws IllegalPathException {
        var path = System.getenv(ENV_VAR);
        if (path == null || path.isEmpty()) throw new IllegalPathException();
        return new File(path);
    }

    public CollectionManager load() throws IllegalPathException, IOException, EmptyFileException {
        XmlMapper mapper = new XmlMapper();
        Scanner reader = new Scanner(searchFile());
        StringBuilder xmlString = new StringBuilder();
        while (reader.hasNext()) xmlString.append(reader.nextLine());
        if (xmlString.isEmpty()) throw new EmptyFileException();
        reader.close();
        return mapper.readValue(xmlString.toString(), CollectionManager.class);
    }

    /**
     * Saves specified CollectionManager object to a file.
     * @param collectionManager object to save.
     * @throws IOException if error while serialization process.
     * @throws IllegalPathException if path is not correct.
     */
    public void save(CollectionManager collectionManager) throws IOException, IllegalPathException {
        XmlMapper mapper = new XmlMapper();
        FileWriter fileWriter = new FileWriter(searchFile());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xmlString = mapper.writeValueAsString(collectionManager);
        fileWriter.write(xmlString);
        fileWriter.close();
    }

    /**
     * @return absolute path of the file.
     * @throws FileNotFoundException if file not found.
     * @throws IllegalPathException if path is not correct.
     */
    public String getAbsolutePath() throws FileNotFoundException, IllegalPathException {
        return searchFile().getAbsolutePath();
    }
}
