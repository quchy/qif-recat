package qifrecat;

import lombok.NonNull;

import java.util.ArrayList;

/**
 * Created by quchy on 15/07/2017.
 * Storing mapping rules
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

