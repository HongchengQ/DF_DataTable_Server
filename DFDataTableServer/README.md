# 三角洲行动配置表服务

有一款游戏数值只在公告改动它时显示，游戏中却无法查看

### 已实现功能:
- 武器基础属性

### TODO:
- 武器配件 (激光镭射你无敌了)

前端只做演示用，不能确保可以使用全部功能，因为我不会前端，AI写的也是一坨，随时可能出现bug，功能越多bug越多

## 安装与运行

通过源码构建：
-  [Java 25](https://www.oracle.com/java/technologies/downloads/#jdk25-windows)
-  [maven](https://maven.apache.org/download.cgi)

```bash
# 运行应用
mvn compile
mvn spring-boot:run
```

```bash
# 构建jar文件
mvn package
```
输出目录在`/target/`

## 使用说明
启动完后端打开 [http://localhost:8080/index.html](http://localhost:8080/index.html) 即可使用演示界面