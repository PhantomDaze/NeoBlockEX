# 添加新方块和物品

这个项目现在是数据驱动的。通常情况下，新增一个方块或物品只需要改一处 Java 列表、放一张贴图，然后重新生成资源。

## 新增方块

1. 打开 [`src/main/java/io/github/PhantomDaze/cynblockex/ModBlock.java`](../src/main/java/io/github/PhantomDaze/cynblockex/ModBlock.java)。
2. 在 `DEFINITIONS` 里加一条记录。

   示例：
   ```java
   new BlockDefinition("my_block", Blocks.OBSIDIAN)
   ```

3. 放入方块贴图：
   ```text
   src/main/resources/assets/cynblockex/textures/block/my_block.png
   ```

4. 如果你想要自定义翻译，修改 [`src/client/java/io/github/PhantomDaze/cynblockex/datagen/BlockEXLanguageProvider.java`](../src/client/java/io/github/PhantomDaze/cynblockex/datagen/BlockEXLanguageProvider.java)。
   - 在 `translateBlock(...)` 里给新名字加一个 `case`
   - 不加的话，生成的语言文件会回退成原始注册名

5. 运行：
   ```bash
   ./gradlew runDatagen
   ```

6. 再启动游戏或重新执行 `build`

## 新增物品

1. 打开 [`src/main/java/io/github/PhantomDaze/cynblockex/ModItem.java`](../src/main/java/io/github/PhantomDaze/cynblockex/ModItem.java)。
2. 在 `DEFINITIONS` 里加一条记录。

   示例：
   ```java
   new ItemDefinition("my_item")
   ```

3. 放入物品贴图：
   ```text
   src/main/resources/assets/cynblockex/textures/item/my_item.png
   ```

4. 如果你想要自定义翻译，修改 `translateItem(...)`。

5. 运行：
   ```bash
   ./gradlew runDatagen
   ```

## 添加特性

如果你只是想给“新增内容”加一些通用属性，可以直接改注册方法，不用改 `DEFINITIONS` 的结构。

### 方块特性

方块的属性通常在 [`ModBlock.java`](../src/main/java/io/github/PhantomDaze/cynblockex/ModBlock.java) 的 `register(String name, Block baseBlock)` 里处理。

适合放在这里的内容包括：

- 硬度和爆炸抗性
- 是否需要正确工具挖掘
- 发光效果
- 声音类型
- 摩擦、跳跃、速度之类的通用方块参数

如果所有新方块都要同一套特性，直接在这里改 `BlockBehaviour.Properties` 就够了。

如果某一个方块需要特殊行为，比如自定义交互、随机刻更新、方块实体、特殊渲染，就需要把该条目扩展成自定义工厂，而不是只用现在这种“名字 + 基础方块”的写法。

### 物品特性

物品的属性通常在 [`ModItem.java`](../src/main/java/io/github/PhantomDaze/cynblockex/ModItem.java) 的 `register(String name)` 里处理。

适合放在这里的内容包括：

- 最大堆叠数
- 耐久
- 稀有度
- 食物属性
- 火焰免疫
- 使用动画和使用时长

如果所有新物品都要同样特性，直接在这里改 `Item.Properties` 就够了。

如果某一个物品需要特殊行为，比如右键使用、持续使用、攻击效果、附魔行为，就需要把该条目扩展成自定义 `Item` 子类。

### 什么时候要改注册结构

当前注册结构是“简化版”，更适合：

- 多个条目共享同一种基础行为
- 新增内容主要是贴图和翻译
- 需要快速批量添加条目

如果你后面要给少数条目加专属逻辑，建议把 `DEFINITIONS` 升级成支持：

- 自定义 `Block` / `Item` 工厂
- 自定义 `BlockBehaviour.Properties`
- 自定义 `Item.Properties`

这样普通条目继续保持简洁，特殊条目再单独配置。

## 重要规则

- 不要手改 `src/main/generated` 里的文件，除非你很清楚自己在做什么。
- 注册名、生成出来的模型名、贴图名必须一致。
- 方块生成的模型会去找 `textures/block/<name>.png`
- 物品生成的模型会去找 `textures/item/<name>.png`
- 新方块会自动进入创造模式物品栏，因为 `ModBlock.all()` 会统一加入
- 新物品会自动进入创造模式物品栏，因为 `ModItem.all()` 会统一加入

## 当前结构

- 方块注册：[`ModBlock.java`](../src/main/java/io/github/PhantomDaze/cynblockex/ModBlock.java)
- 物品注册：[`ModItem.java`](../src/main/java/io/github/PhantomDaze/cynblockex/ModItem.java)
- 模型生成：[`BlockEXModelProvider.java`](../src/client/java/io/github/PhantomDaze/cynblockex/datagen/BlockEXModelProvider.java)
- 语言生成：[`BlockEXLanguageProvider.java`](../src/client/java/io/github/PhantomDaze/cynblockex/datagen/BlockEXLanguageProvider.java)
- 生成资源：[`src/main/generated`](../src/main/generated)
