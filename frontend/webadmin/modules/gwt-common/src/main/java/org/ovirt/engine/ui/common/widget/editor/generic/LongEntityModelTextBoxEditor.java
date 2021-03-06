package org.ovirt.engine.ui.common.widget.editor.generic;

import java.math.BigInteger;
import java.util.Arrays;

import org.ovirt.engine.ui.common.widget.parser.generic.ToLongEntityParser;
import org.ovirt.engine.ui.uicompat.ConstantsManager;

public class LongEntityModelTextBoxEditor extends NumberEntityModelTextBoxEditor<Long> {

    public LongEntityModelTextBoxEditor() {
        super(new ToStringEntityModelRenderer<Long>(), new ToLongEntityParser());
    }

    @Override
    protected void handleInvalidState() {
        //Be sure to call super.handleInvalidstate to make sure the editor valid state is properly updated.
        super.handleInvalidState();
        //Even though this is a long, the validator will return the integer message, so that is the one we are using here.
        markAsInvalid(Arrays.asList(ConstantsManager.getInstance().getConstants().thisFieldMustContainIntegerNumberInvalidReason()));
    }

    @Override
    protected boolean isNumberType(String text) {
        if (text.length() <= 20) {
            BigInteger value = new BigInteger(text);
            if (value.compareTo(new BigInteger("" + Long.MIN_VALUE)) >= 0
                    && value.compareTo(new BigInteger("" + Long.MAX_VALUE)) <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
