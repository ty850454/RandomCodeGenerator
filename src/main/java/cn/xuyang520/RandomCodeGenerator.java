package cn.xuyang520;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * 生成固定14位随机code
 *
 * base64较快，但存在-_符号，执行百万次400毫秒
 * base62较慢，只有大小写以及数字，执行百万次1200毫秒
 *
 * @author xuyang
 */
public class RandomCodeGenerator {

    private final Encoder encoder;
    private final SecureRandom ng = new SecureRandom();

    private RandomCodeGenerator(Encoder encoder) {
        this.encoder = encoder;
    }

    public static RandomCodeGenerator createWithBa64() {
        return new RandomCodeGenerator(new Base64Encoder());
    }

    /**
     * 可以生成14位随意code的code生成器，
     *
     * @param fillGap 不足14位时是否补位
     * @return code生成器
     */
    public static RandomCodeGenerator createWithBa62(boolean fillGap) {
        return new RandomCodeGenerator(new Base62Encoder(fillGap));
    }

    public String generate() {
        byte[] randomBytes = new byte[10];
        ng.nextBytes(randomBytes);
        return encoder.encoder(randomBytes);
    }

    private interface Encoder {
        String encoder(byte[] bytes);
    }

    private static class Base64Encoder implements Encoder {

        private static final Base64.Encoder ENCODER = Base64.getUrlEncoder().withoutPadding();

        @Override
        public String encoder(byte[] bytes) {
            return ENCODER.encodeToString(bytes);
        }
    }

    private static class Base62Encoder implements Encoder {

        private static final Base62 ENCODER = Base62.createEncoder(14);
        private static final Random RANDOM = new Random();
        private final boolean fillGap;

        private Base62Encoder(boolean fillGap) {
            this.fillGap = fillGap;
        }

        @Override
        public String encoder(byte[] bytes) {
            String code = new String(ENCODER.encode(bytes));
            if (fillGap && code.length() == 13) {
                code += (char) Base62.CharacterSets.GMP[RANDOM.nextInt(51)];
            }
            return code;
        }
    }


}
