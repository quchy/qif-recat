package qifrecat;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quchy on 15/07/2017.
 * Stroing mapping rules
 */
public class QIFMap {

    @NonNull
    Map<QIFMapEntry.MEKey, QIFMapEntry.MEValue> Entries;

    public QIFMap() {
        Entries = new HashMap<>();
    }


    void put(QIFMapEntry qifMapEntry) {
        Entries.put(qifMapEntry.getKey(), qifMapEntry.getValue());
    }

    QIFMapEntry.MEValue findValue(String memo){
        return null;
    }
}
