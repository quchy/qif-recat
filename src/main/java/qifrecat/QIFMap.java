package qifrecat;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by quchy on 15/07/2017.
 * Stroing mapping rules
 */
public class QIFMap {

    @NonNull
    ArrayList<QIFMapEntry.MEKey> KeyEntries;
    ArrayList<QIFMapEntry.MEValue> ValueEntries;

    public QIFMap() {
        KeyEntries = new ArrayList<>();
        ValueEntries = new ArrayList<>();
    }

    void put(QIFMapEntry qifMapEntry) {
        KeyEntries.add(qifMapEntry.getKey());
        ValueEntries.add(qifMapEntry.getValue());
    }

    QIFMapEntry.MEValue findValue(String memo){
        for(int i=0; KeyEntries.size() != i; i++) {
            if ((memo.contains(KeyEntries.get(i).getKey1())) && (memo.contains(KeyEntries.get(i).getKey2()))) {
                return ValueEntries.get(i);
            }
        }
        return null;
    }
}

