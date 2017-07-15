package qifrecat;

import lombok.Data;

/**
 * Created by zskucsak on 15/07/2017.
 * Value to be set on transactions
 */

@Data
class MEValue {
    String Payee;
    String Category;
    String Area;
}
