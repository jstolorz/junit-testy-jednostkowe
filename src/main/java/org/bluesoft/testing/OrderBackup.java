package org.bluesoft.testing;

import java.io.*;

public class OrderBackup {
   private Writer writer;

    Writer getWriter() {
        return writer;
    }

    void createFile() throws FileNotFoundException {
        File file = new File("orderBackup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter((outputStreamWriter));
    }

    void backupOrder(Order order) throws IOException {
        writer.append(order.toString());
    }

    void closeWriter() throws IOException {
        writer.close();
    }
}
