# RandomCodeGenerator

生成固定14位的随机code，有base64、base62两种encoder可以选择。

Generates a fixed 14-bit random code, with base 64 and base 62 encoders to choose from.

```java
RandomCodeGenerator codeGenerator = RandomCodeGenerator.createWithBa62(false);
String code = codeGenerator.generate();
```

