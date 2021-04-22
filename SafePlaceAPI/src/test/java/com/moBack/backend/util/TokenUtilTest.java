package com.moBack.backend.util;

import com.moBack.backend.AbstractTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class TokenUtilTest extends AbstractTest {

    @Test
    public void getEmailFromTokenTest() {
        Assert.assertEquals("test@gmail.com",TokenUtil.getEmailFromToken("123242424-test@gmail.com-adfsdffsdf").get());
        Assert.assertEquals(Optional.empty(),TokenUtil.getEmailFromToken("123242424-adfsdffsdf"));
    }

    @Test
    public void getTokenTest() {
        String res = "1616161616-test@gmail.com-slfkjslfsdkjsfld";
        Assert.assertEquals(res,TokenUtil.getToken("Bearer "+res));
        Assert.assertEquals("",TokenUtil.getToken(res));
    }
}
