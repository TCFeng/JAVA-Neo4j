
package feng.icd.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BasicVO implements Serializable {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
