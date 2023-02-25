package cn.xuyang520;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class RandomCodeGeneratorTest {


    @Test
    public void testGenerateWithBase62AndMultipleTimes() {
        RandomCodeGenerator codeGenerator = RandomCodeGenerator.createWithBa62(false);
        int size = 1000000;
        HashSet<String> codes = new HashSet<>(size * 3 / 4 + 1);
        for (int i = 0; i < size; i++) {
            String code = codeGenerator.generate();
            codes.add(code);
        }
        Assert.assertEquals(codes.size(), size);
    }


    @Test
    public void testGenerateWithBase64AndMultipleTimes() {
        RandomCodeGenerator codeGenerator = RandomCodeGenerator.createWithBa64();
        int size = 1000000;
        HashSet<String> codes = new HashSet<>(size * 3 / 4 + 1);
        for (int i = 0; i < size; i++) {
            String code = codeGenerator.generate();
            codes.add(code);
        }
        Assert.assertEquals(codes.size(), size);
    }

}