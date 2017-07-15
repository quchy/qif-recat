package qifrecat;

import lombok.Data;
import lombok.Getter;

/**
 * Created by zskucsak on 15/07/2017.
 * Single Map entry
 */


@Data
class QIFMapEntry {

    @Data
    class MEKey {

        String Key1;
        String Key2;
    }

    @Data
    class MEValue {
        String Payee;
        String Category;
        String Area;
    }

    @Getter
    MEKey key;
    @Getter
    MEValue value;
}
