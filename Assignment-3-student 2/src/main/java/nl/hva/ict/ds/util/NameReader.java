package nl.hva.ict.ds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameReader {
    private final String[] names;

    public NameReader(String fileName) {
        try (Scanner input = new Scanner(getClass().getResourceAsStream(fileName))) {
            List<String> names = new ArrayList<>();
            while (input.hasNextLine()) {
                names.add(input.nextLine());
            }
            this.names = names.toArray(new String[names.size()]);
        }
    }

    public String[] getNames() {
        return names.clone();
    }
}
