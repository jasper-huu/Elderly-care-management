package com.zzyl;

import com.zzyl.generator.util.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;

public class VelocityTest {
    public static void main(String[] args) throws IOException {
        // 1.初始化模板引擎
        VelocityInitializer.initVelocity();

        // 2.创建velocity上下文对象
        VelocityContext context = new VelocityContext();
        context.put("message", "加油少年~~~");

        // 3.获取模板对象
        Template template = Velocity.getTemplate("vms/index.html.vm", "utf-8");

        // 4.准备一个文件字符输出流
        FileWriter fileWriter = new FileWriter("zzyl-generator\\src\\main\\resources\\index.html");

        // 5.合并模板和数据模型
        template.merge(context, fileWriter);

        // 注意：关流
        fileWriter.close();
    }
}
