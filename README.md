# BaseApp

    ⚠️ 本仓库为 Android 项目模板，请勿直接开发业务

    架构层级：
    app
    ↓
    feature
    ↓
    IProvider (接口，负责模块间通信，若APP体量大，应该更改setting.xml，一个模块对应一个IProvider，如feature-home-api)
    ↓
    basicLib (core / network / utils / ARouter / coil / ui)

    ✅ 允许：
    
    上层依赖下层
    
    依赖接口，不依赖实现

    UI 组件允许依赖基础能力模块（如图片加载、资源管理），但禁止依赖任何上层模块。
    
    ❌ 禁止：
    
    下层反向依赖上层
    
    feature 之间直接互相依赖

## 一、项目定位

BaseApp 是一个 **Android 项目基础模板工程**，用于快速启动新业务项目。

该模板已完成：

* 多 Module 工程结构
* 
* 基础库 / 业务库 / Feature 库拆分
* 网络层、工具层、通用配置初始化
* 常见工程配置（Gradle、依赖管理、基础封装）

* 不包含任何具体业务逻辑，仅作为新项目的起点。

  ⚠️ 注意：请勿直接在 BaseApp 上开发业务，请先复制一份作为新项目。

## 二、目录结构说明

```text
BaseApp
├── app                # 壳工程（Application / Launcher Activity）
├── basicLib           # 基础公共库（工具类、通用 UI、通用 strings 等）
├── featureLib         # Feature 级模块（可按需拆分）
├── IProvider          # 模块通信 / 接口定义层
└── Gradle Scripts     # 统一构建配置
```

### 模块职责原则

* **app**：只做壳与组装，不承载业务
* **basicLib**：唯一公共能力下沉点
* **feature / business**：只放自身业务代码与资源
* **无 UI 模块**：禁止持有 res 资源

---

## 三、新项目创建流程（10 分钟启动）

### Step 1：复制模板工程

* 拷贝整个 BaseApp 目录
* 重命名工程目录，例如： BaseApp → UChat

---

### Step 2：解绑模板 Git（如果尚未解绑）

直接删除.git文件夹 确保新项目是一个全新的 Git 仓库

然后重新初始化：

```bash
git init
git add .
git commit -m "init project"
```

---

### Step 3：修改工程名

编辑 `settings.gradle`：

```gradle
rootProject.name = "UChat"
```

---

### Step 4：修改 applicationId

`app/build.gradle`：

```gradle
defaultConfig {
    applicationId "com.xxx.uchat"
}
```

> 建议规则：`com.公司名.项目名`

---

### Step 5：重命名包名（非常重要）

在 Android Studio 中：

```
Android 视图 → app → kotlin+java → com.xxx.baseapp
```

* 右键包名
* `Refactor → Rename → Rename package`
* 修改为新包名（如 `com.xxx.uchat`）
* 选择 **All Directories**

---

### Step 6：修改 App 显示名称

`res/values/strings.xml`：

```xml
<string name="app_name">UChat</string>
```

---

### Step 7：检查关键配置项

请确认以下内容已同步修改：

* AndroidManifest.xml

    * `namespace`
    * `android:name`（Application）
* FileProvider authorities
* DeepLink / Scheme（如有）
* Proguard / consumer-rules

可全局搜索旧包名进行确认。

---

### Step 8：裁剪无用模块（可选）

根据项目实际情况：

* 删除暂不需要的 feature / business module
* 同步移除 `settings.gradle` 与依赖声明

---

### Step 9：清理资源

推荐做法：

* `app`：仅保留 `app_name`
* `basicLib`：承载所有通用 strings / colors / dimens
* 业务模块：仅保留自身文案
* 无 UI 模块：删除 `res/` 目录

---

### Step 10：构建验证

```text
Build → Clean Project
Build → Rebuild Project
```

确保：

* 可正常编译
* 可正常启动

---

## 四、资源使用规范（强制）

### strings.xml 规范

* 通用文案：`basicLib`
* 业务文案：所属 feature
* 禁止跨模块引用业务文案

---

## 五、模板使用约定

* BaseApp 仅维护 **工程能力**，不维护业务
* 新能力优先考虑是否可下沉到 basicLib
* 不允许在 app 模块直接写业务逻辑

---

## 六、维护说明

* 建议对 BaseApp 单独维护版本（Tag）
* 新项目统一从指定 Tag 创建

示例：

```text
baseapp-template-v1.0
```

---

## 七、FAQ

### Q：可以直接在 BaseApp 上开发吗？

不建议。BaseApp 的职责是模板，而不是业务工程。

### Q：后续如何升级模板？

* 在 BaseApp 仓库中升级
* 新项目按需同步

---

## 八、总结

> BaseApp 的价值在于：
> 每一个新项目，都从一个“已经想清楚结构”的起点开始。

## 九、用此模板项目开始开发

1. Click "Use this template" on GitHub
2. Create a new repository
3. Clone the new repository
4. Update:
  - settings.gradle -> rootProject.name
  - app/build.gradle -> applicationId
  - AndroidManifest.xml -> strings.xml -> app_name
  - rename package name (All Directories) (optional)
  - Sync Gradle and start development
